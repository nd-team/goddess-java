package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.DemandCostBO;
import com.bjike.goddess.businessevaluate.dto.DemandCostDTO;
import com.bjike.goddess.businessevaluate.entity.DemandCost;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.to.DemandCostTO;
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
 * 需求成本业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 11:06 ]
 * @Description: [ 需求成本业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class DemandCostSerImpl extends ServiceImpl<DemandCost, DemandCostDTO> implements DemandCostSer {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DemandCostBO addModel(DemandCostTO to) throws SerException {

        DemandCost model = BeanTransform.copyProperties(to, DemandCost.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, DemandCostBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DemandCostBO editModel(DemandCostTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            DemandCost model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, DemandCostBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<DemandCostBO> pageList(DemandCostDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<DemandCost> list = super.findByPage(dto);
        List<DemandCostBO> boList = BeanTransform.copyProperties(list, DemandCostBO.class);
        //设置项目信息
        for (DemandCostBO bo : boList) {
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