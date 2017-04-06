package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyPlanBO;
import com.bjike.goddess.attainment.dto.SurveyPlanDTO;
import com.bjike.goddess.attainment.entity.SurveyPlan;
import com.bjike.goddess.attainment.to.SurveyPlanAuditTO;
import com.bjike.goddess.attainment.to.SurveyPlanTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研计划业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:41 ]
 * @Description: [ 调研计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyPlanSer extends Ser<SurveyPlan, SurveyPlanDTO> {

    default SurveyPlanBO save(SurveyPlanTO to) throws SerException {
        return null;
    }

    default SurveyPlanBO update(SurveyPlanTO to) throws SerException {
        return null;
    }

    default SurveyPlanBO delete(String id) throws SerException {
        return null;
    }

    default SurveyPlanBO audit(SurveyPlanAuditTO to) throws SerException {
        return null;
    }

    default List<SurveyPlanBO> findByDemand(String demand_id) throws SerException {
        return null;
    }

}