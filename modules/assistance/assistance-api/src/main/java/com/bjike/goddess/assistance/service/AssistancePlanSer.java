package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistancePlanBO;
import com.bjike.goddess.assistance.to.*;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.assistance.entity.AssistancePlan;
import com.bjike.goddess.assistance.dto.AssistancePlanDTO;

import java.util.List;

/**
 * 补助方案业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:27 ]
 * @Description: [ 补助方案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AssistancePlanSer extends Ser<AssistancePlan, AssistancePlanDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 补助方案列表总条数
     *
     */
    default Long countAssistancePlan(AssistancePlanDTO assistancePlanDTO) throws SerException {
        return null;
    }


    /**
     * 一个补助方案
     *
     * @return class AssistancePlanBO
     */
    default AssistancePlanBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 补助方案列表
     * @return class AssistancePlanBO
     */
    default List<AssistancePlanBO> listAssistancePlan(AssistancePlanDTO assistancePlanDTO) throws SerException {return null;}
    /**
     *  添加
     * @param assistancePlanTO 补助方案信息
     * @return class AssistancePlanBO
     */
    default AssistancePlanBO addAssistancePlan(AssistancePlanTO assistancePlanTO) throws SerException { return null;}

    /**
     *  编辑
     * @param assistancePlanTO 补助方案信息
     * @return class AssistancePlanBO
     */
    default AssistancePlanBO editAssistancePlan(AssistancePlanTO assistancePlanTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteAssistancePlan(String id ) throws SerException {return;};



    /**
     * 审核
     *
     * @param assistancePlanTO 补助方案信息
     * @return class AssistancePlanBO
     */
    default AssistancePlanBO auditAssistancePlan(ResourceAuditPlanTO assistancePlanTO) throws SerException {
        return null;
    }
    /**
     * 财务审核
     *
     * @param assistancePlanTO 补助方案信息
     * @return class AssistancePlanBO
     */
    default AssistancePlanBO finaceAuditAssistancePlan(FinaceAuditPlanTO assistancePlanTO) throws SerException {
        return null;
    }

    /**
     * 总经办审核
     *
     * @param assistancePlanTO 补助方案信息
     * @return class AssistancePlanBO
     */
    default AssistancePlanBO manageAuditAssistancePlan(ManageAuditPlanTO assistancePlanTO) throws SerException {
        return null;
    }

    /**
     * 获取补助方案序号
     *
     * @return class AssistancePlanBO
     */
    default List<AssistancePlanBO> listPlanNum( ) throws SerException {
        return null;
    }
    /**
     * 根据序号获取补助方案
     *
     * @return class AssistancePlanBO
     */
    default List<AssistancePlanBO> getPlanByNum(AssistancePlanDTO assistancePlanDTO) throws SerException {
        return null;
    }


}