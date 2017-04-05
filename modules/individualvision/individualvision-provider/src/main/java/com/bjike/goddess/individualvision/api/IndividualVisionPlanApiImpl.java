package com.bjike.goddess.individualvision.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.individualvision.bo.IndividualVisionPlanBO;
import com.bjike.goddess.individualvision.dto.IndividualVisionPlanDTO;
import com.bjike.goddess.individualvision.entity.IndividualVisionPlan;
import com.bjike.goddess.individualvision.service.IndividualVisionPlanSer;
import com.bjike.goddess.individualvision.to.IndividualVisionPlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 个人愿景计划业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:26 ]
 * @Description: [ 个人愿景计划业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("individualVisionPlanApiImpl")
public class IndividualVisionPlanApiImpl implements IndividualVisionPlanAPI {
    @Autowired
    private IndividualVisionPlanSer individualVisionPlanSer;
    @Override
    public List<IndividualVisionPlanBO> findListIndividualVisionPlan(IndividualVisionPlanDTO individualVisionPlanDTO) throws SerException {
        return individualVisionPlanSer.findListIndividualVisionPlan(individualVisionPlanDTO);
    }

    @Override
    public IndividualVisionPlanBO insertIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        return individualVisionPlanSer.insertIndividualVisionPlan(individualVisionPlanTO);
    }

    @Override
    public IndividualVisionPlanBO editIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        return individualVisionPlanSer.editIndividualVisionPlan(individualVisionPlanTO);
    }

    @Override
    public void removeIndividualVisionPlan(String id) throws SerException {
        individualVisionPlanSer.removeIndividualVisionPlan(id);
    }
    @Override
    public IndividualVisionPlanBO auditIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        return individualVisionPlanSer.auditIndividualVisionPlan(individualVisionPlanTO);
    }
}