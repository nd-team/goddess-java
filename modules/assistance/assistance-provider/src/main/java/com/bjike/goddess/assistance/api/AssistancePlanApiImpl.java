package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.AssistancePlanBO;
import com.bjike.goddess.assistance.dto.AssistancePlanDTO;
import com.bjike.goddess.assistance.service.AssistancePlanSer;
import com.bjike.goddess.assistance.to.AssistancePlanTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 补助方案业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:27 ]
 * @Description: [ 补助方案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("assistancePlanApiImpl")
public class AssistancePlanApiImpl implements AssistancePlanAPI {

    @Autowired
    private AssistancePlanSer assistancePlanSer;

    @Override
    public Long countAssistancePlan(AssistancePlanDTO assistancePlanDTO) throws SerException {
        return assistancePlanSer.countAssistancePlan( assistancePlanDTO);
    }

    @Override
    public AssistancePlanBO getOneById(String id) throws SerException {
        return assistancePlanSer.getOneById(id);
    }

    @Override
    public List<AssistancePlanBO> listAssistancePlan(AssistancePlanDTO assistancePlanDTO) throws SerException {
        return assistancePlanSer.listAssistancePlan(assistancePlanDTO);
    }

    @Override
    public AssistancePlanBO addAssistancePlan(AssistancePlanTO assistancePlanTO) throws SerException {
        return assistancePlanSer.addAssistancePlan(assistancePlanTO);
    }

    @Override
    public AssistancePlanBO editAssistancePlan(AssistancePlanTO assistancePlanTO) throws SerException {
        return assistancePlanSer.editAssistancePlan(assistancePlanTO);
    }

    @Override
    public void deleteAssistancePlan(String id) throws SerException {
        assistancePlanSer.deleteAssistancePlan(id);
    }

    @Override
    public AssistancePlanBO auditAssistancePlan(AssistancePlanTO assistancePlanTO) throws SerException {
        return assistancePlanSer.auditAssistancePlan(assistancePlanTO);
    }

    @Override
    public List<AssistancePlanBO> listPlanNum() throws SerException {
        return assistancePlanSer.listPlanNum();
    }

    @Override
    public List<AssistancePlanBO> getPlanByNum(AssistancePlanDTO assistancePlanDTO) throws SerException {
        return assistancePlanSer.getPlanByNum(assistancePlanDTO);
    }
}