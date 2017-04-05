package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.HandlePlanStageBO;
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

    /**
     * 保存
     *
     * @param to 资质办理计划阶段传输对象
     * @return
     * @throws SerException
     */
    default HandlePlanStageBO save(HandlePlanStageTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 资质办理计划阶段传输对象
     * @return
     * @throws SerException
     */
    default HandlePlanStageBO update(HandlePlanStageTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 资质办理计划阶段id
     * @return
     * @throws SerException
     */
    default HandlePlanStageBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据办理计划id集合查询计划阶段
     *
     * @param plan_ids
     * @return
     * @throws SerException
     */
    default List<HandlePlanStageBO> findByPlanIds(String[] plan_ids) throws SerException {
        return null;
    }

    /**
     * 根据办理计划id查询计划阶段
     *
     * @param plan_id 办理计划ID
     * @return
     * @throws SerException
     */
    default List<HandlePlanStageBO> findByPlan(String plan_id) throws SerException {
        return null;
    }

    /**
     * 根据资质办理ID查询计划阶段
     *
     * @param handle_id 资质办理ID
     * @return
     * @throws SerException
     */
    default List<HandlePlanStageBO> findByHandle(String handle_id) throws SerException {
        return null;
    }
}