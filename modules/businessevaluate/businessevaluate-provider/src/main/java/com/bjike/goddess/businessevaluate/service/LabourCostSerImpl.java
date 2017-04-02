package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.LabourCostBO;
import com.bjike.goddess.businessevaluate.dto.LabourCostDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.LabourCost;
import com.bjike.goddess.businessevaluate.to.LabourCostTO;
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
 * 劳动成本业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 09:16 ]
 * @Description: [ 劳动成本业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class LabourCostSerImpl extends ServiceImpl<LabourCost, LabourCostDTO> implements LabourCostSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public LabourCostBO insertModel(LabourCostTO to) throws SerException {

        LabourCost model = BeanTransform.copyProperties(to, LabourCost.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, LabourCostBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public LabourCostBO updateModel(LabourCostTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            LabourCost model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, LabourCostBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<LabourCostBO> pageList(LabourCostDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<LabourCost> list = super.findByPage(dto);
        List<LabourCostBO> boList = BeanTransform.copyProperties(list, LabourCostBO.class);
        //设置项目信息
        for (LabourCostBO bo : boList) {
            EvaluateProjectInfo info = evaluateProjectInfoSer.findById(bo.getProjectInfoId());
            if (info != null) {
                bo.setArea(info.getArea());
                bo.setProject(info.getProject());
                bo.setStartTime(info.getStartTime().toString());
                bo.setEndTime(info.getEndTime().toString());
            }
        }
        return boList;
    }
}