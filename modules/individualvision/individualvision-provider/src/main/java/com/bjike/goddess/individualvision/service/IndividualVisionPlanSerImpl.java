package com.bjike.goddess.individualvision.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.individualvision.bo.IndividualVisionPlanBO;
import com.bjike.goddess.individualvision.dto.IndividualVisionPlanDTO;
import com.bjike.goddess.individualvision.entity.IndividualVisionPlan;
import com.bjike.goddess.individualvision.to.IndividualVisionPlanTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 个人愿景计划业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:26 ]
 * @Description: [ 个人愿景计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "individualvisionSerCache")
@Service
public class IndividualVisionPlanSerImpl extends ServiceImpl<IndividualVisionPlan, IndividualVisionPlanDTO> implements IndividualVisionPlanSer {
    @Override
    public Long countIndividualVisionPlan(IndividualVisionPlanDTO individualVisionPlanDTO) throws SerException {
        Long count = super.count(individualVisionPlanDTO);
        return count;
    }

    @Override
    public IndividualVisionPlanBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        IndividualVisionPlan individualVisionPlan = super.findById(id);
        return BeanTransform.copyProperties(individualVisionPlan,IndividualVisionPlanBO.class);
    }

    @Override
    public List<IndividualVisionPlanBO> findListIndividualVisionPlan(IndividualVisionPlanDTO individualVisionPlanDTO) throws SerException {
        List<IndividualVisionPlan> individualVisionPlans = super.findByCis(individualVisionPlanDTO, true);
        return BeanTransform.copyProperties(individualVisionPlans, IndividualVisionPlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IndividualVisionPlanBO insertIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        IndividualVisionPlan individualVisionPlan = BeanTransform.copyProperties(individualVisionPlanTO, IndividualVisionPlan.class);
        individualVisionPlan.setCreateTime(LocalDateTime.now());
        super.save(individualVisionPlan);
        return BeanTransform.copyProperties(individualVisionPlan, IndividualVisionPlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IndividualVisionPlanBO editIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        if(StringUtils.isBlank(individualVisionPlanTO.getId())){
            throw new SerException("id不能为空");
        }
        IndividualVisionPlan individualVisionPlan = super.findById(individualVisionPlanTO.getId());
        BeanTransform.copyProperties(individualVisionPlanTO,individualVisionPlan,true);
        individualVisionPlan.setModifyTime(LocalDateTime.now());
        super.update(individualVisionPlan);
        return BeanTransform.copyProperties(individualVisionPlan,IndividualVisionPlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeIndividualVisionPlan(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public IndividualVisionPlanBO auditIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        IndividualVisionPlan individualVisionPlan = BeanTransform.copyProperties(individualVisionPlanTO, IndividualVisionPlan.class, true);
        individualVisionPlan.setPositionsStatus(individualVisionPlanTO.getPositionsStatus());
        super.update(individualVisionPlan);

        IndividualVisionPlanBO individualVisionPlanBO = BeanTransform.copyProperties(individualVisionPlan, IndividualVisionPlanBO.class);
        return individualVisionPlanBO;
    }
}