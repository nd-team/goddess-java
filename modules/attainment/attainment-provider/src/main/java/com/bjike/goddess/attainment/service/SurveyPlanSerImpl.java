package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyPlanBO;
import com.bjike.goddess.attainment.dto.SurveyPlanDTO;
import com.bjike.goddess.attainment.entity.SurveyDemand;
import com.bjike.goddess.attainment.entity.SurveyPlan;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 调研计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:41 ]
 * @Description: [ 调研计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyPlanSerImpl extends ServiceImpl<SurveyPlan, SurveyPlanDTO> implements SurveyPlanSer {

    private SurveyPlanBO transformBO(SurveyPlan entity) throws SerException {
        SurveyPlanBO bo = BeanTransform.copyProperties(entity, SurveyPlanBO.class);
        SurveyDemand demand = entity.getDemand();
        bo.setDemand_id(demand.getId());
        bo.setDemandName(demand.getDemand().getType());
        bo.setPurpose(demand.getPurpose());
        bo.setScope(demand.getScope());
        bo.setTypeName(demand.getType().getType());
        bo.setUsername(demand.getUsername());
        bo.isRegular(demand.getType().isRegular());

        return bo;
    }

}