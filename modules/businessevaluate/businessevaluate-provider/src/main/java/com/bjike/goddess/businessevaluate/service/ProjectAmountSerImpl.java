package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.ProjectAmountBO;
import com.bjike.goddess.businessevaluate.bo.ProjectAmountInfoBO;
import com.bjike.goddess.businessevaluate.dto.ProjectAmountDTO;
import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.ProjectAmount;
import com.bjike.goddess.businessevaluate.entity.ProjectCost;
import com.bjike.goddess.businessevaluate.to.ProjectAmountTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目金额业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 项目金额业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class ProjectAmountSerImpl extends ServiceImpl<ProjectAmount, ProjectAmountDTO> implements ProjectAmountSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;
    @Autowired
    private ProjectCostSer projectCostSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectAmountInfoBO findInfoById(String id) throws SerException {
        ProjectAmountInfoBO bo = new ProjectAmountInfoBO();
        EvaluateProjectInfo info = evaluateProjectInfoSer.findById(id);
        bo.setCost(info.getCost());
        bo.setFee(info.getManageCost());
        bo.setTaxes(info.getTaxes());
        //查询设置费用
        ProjectCostDTO projectCostDTO = new ProjectCostDTO();
        projectCostDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        ProjectCost projectCost = projectCostSer.findOne(projectCostDTO);
        Double fee = projectCost.getServiceCost() + projectCost.getEntertainCost() + projectCost.getCommission();
        bo.setFee(fee);
        //利润 = 项目总金额 - 成本 -管理费 -税金 - 费用
        Double profit = info.getTotalAmount() - info.getCost() - info.getManageCost() - info.getTaxes() - fee;
        bo.setProfit(profit);
        return bo;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectAmountBO insertModel(ProjectAmountTO to) throws SerException {
        ProjectAmount model = BeanTransform.copyProperties(to, ProjectAmount.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ProjectAmountBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectAmountBO updateModel(ProjectAmountTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            ProjectAmount model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, ProjectAmountBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectAmountBO> pageList(ProjectAmountDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<ProjectAmount> list = super.findByPage(dto);
        List<ProjectAmountBO> boList = BeanTransform.copyProperties(list, ProjectAmountBO.class);
        //设置项目信息
        if (boList != null && !boList.isEmpty()) {
            for (ProjectAmountBO bo : boList) {
                EvaluateProjectInfo info = evaluateProjectInfoSer.findById(bo.getProjectInfoId());
                if (info != null) {
                    bo.setArea(info.getArea());
                    bo.setProject(info.getProject());
                    bo.setStartTime(info.getStartTime().toString());
                    bo.setEndTime(info.getEndTime().toString());
                }
            }
        }
        return boList;
    }

}