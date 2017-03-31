package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.HandlePlanStageBO;
import com.bjike.goddess.qualifications.bo.QualificationsHandlePlanBO;
import com.bjike.goddess.qualifications.dto.HandlePlanStageDTO;
import com.bjike.goddess.qualifications.entity.HandlePlanStage;
import com.bjike.goddess.qualifications.to.HandlePlanStageTO;

import java.util.List;

/**
 * 资质办理计划阶段划分业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:55 ]
 * @Description: [ 资质办理计划阶段划分业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HandlePlanStageSer extends Ser<HandlePlanStage, HandlePlanStageDTO> {

    default HandlePlanStageBO save(HandlePlanStageTO to) throws SerException {
        return null;
    }

    default HandlePlanStageBO update(HandlePlanStageTO to) throws SerException {
        return null;
    }

    default HandlePlanStageBO delete(String id) throws SerException {
        return null;
    }

    default List<HandlePlanStageBO> findByPlanIds(String[] plan_ids) throws SerException{
        return null;
    }

    default List<HandlePlanStageBO> findByPlan(String plan_id) throws SerException{
        return null;
    }

    default List<QualificationsHandlePlanBO> findByHandle(String handle_id) throws SerException {
        return null;
    }
}