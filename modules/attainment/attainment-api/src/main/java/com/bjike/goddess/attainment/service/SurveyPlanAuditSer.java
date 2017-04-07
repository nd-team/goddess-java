package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyPlanAuditBO;
import com.bjike.goddess.attainment.dto.SurveyPlanAuditDTO;
import com.bjike.goddess.attainment.entity.SurveyPlanAudit;
import com.bjike.goddess.attainment.to.SurveyPlanAuditTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研计划审核记录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:47 ]
 * @Description: [ 调研计划审核记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyPlanAuditSer extends Ser<SurveyPlanAudit, SurveyPlanAuditDTO> {

    default SurveyPlanAuditBO update(SurveyPlanAuditTO to) throws SerException {
        return null;
    }

    default SurveyPlanAuditBO findByUserPlan(String plan_id, String auditor) throws SerException {
        return null;
    }

    default SurveyPlanAuditBO delete(String id) throws SerException {
        return null;
    }

    default List<SurveyPlanAuditBO> findByPlan(String plan_id) throws SerException {
        return null;
    }

}