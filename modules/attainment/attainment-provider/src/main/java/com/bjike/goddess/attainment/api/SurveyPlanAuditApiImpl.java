package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyPlanAuditBO;
import com.bjike.goddess.attainment.service.SurveyPlanAuditSer;
import com.bjike.goddess.attainment.to.SurveyPlanAuditTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调研计划审核记录业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:47 ]
 * @Description: [ 调研计划审核记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("surveyPlanAuditApiImpl")
public class SurveyPlanAuditApiImpl implements SurveyPlanAuditAPI {

    @Autowired
    private SurveyPlanAuditSer surveyPlanAuditSer;

    @Override
    public SurveyPlanAuditBO update(SurveyPlanAuditTO to) throws SerException {
        return surveyPlanAuditSer.update(to);
    }

    @Override
    public SurveyPlanAuditBO findByUserPlan(String plan_id, String auditor) throws SerException {
        return surveyPlanAuditSer.findByUserPlan(plan_id, auditor);
    }

    @Override
    public SurveyPlanAuditBO delete(String id) throws SerException {
        return surveyPlanAuditSer.delete(id);
    }

    @Override
    public List<SurveyPlanAuditBO> findByPlan(String plan_id) throws SerException {
        return surveyPlanAuditSer.findByPlan(plan_id);
    }
}