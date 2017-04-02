package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.HandlePlanStageBO;
import com.bjike.goddess.qualifications.service.HandlePlanStageSer;
import com.bjike.goddess.qualifications.to.HandlePlanStageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资质办理计划阶段划分业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:55 ]
 * @Description: [ 资质办理计划阶段划分业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("handlePlanStageApiImpl")
public class HandlePlanStageApiImpl implements HandlePlanStageAPI {

    @Autowired
    private HandlePlanStageSer handlePlanStageSer;

    @Override
    public HandlePlanStageBO save(HandlePlanStageTO to) throws SerException {
        return handlePlanStageSer.save(to);
    }

    @Override
    public HandlePlanStageBO update(HandlePlanStageTO to) throws SerException {
        return handlePlanStageSer.update(to);
    }

    @Override
    public HandlePlanStageBO delete(String id) throws SerException {
        return handlePlanStageSer.delete(id);
    }

    @Override
    public List<HandlePlanStageBO> findByPlanIds(String[] plan_ids) throws SerException {
        return handlePlanStageSer.findByPlanIds(plan_ids);
    }

    @Override
    public List<HandlePlanStageBO> findByPlan(String plan_id) throws SerException {
        return handlePlanStageSer.findByPlan(plan_id);
    }

    @Override
    public List<HandlePlanStageBO> findByHandle(String handle_id) throws SerException {
        return handlePlanStageSer.findByHandle(handle_id);
    }
}