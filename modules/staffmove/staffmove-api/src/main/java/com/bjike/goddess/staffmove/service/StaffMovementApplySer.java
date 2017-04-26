package com.bjike.goddess.staffmove.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffmove.bo.StaffMovementApplyBO;
import com.bjike.goddess.staffmove.entity.StaffMovementApply;
import com.bjike.goddess.staffmove.dto.StaffMovementApplyDTO;
import com.bjike.goddess.staffmove.to.StaffMovementApplyTO;

import java.util.List;

/**
 * 人员调动申请业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-22 04:40 ]
 * @Description: [ 人员调动申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffMovementApplySer extends Ser<StaffMovementApply, StaffMovementApplyDTO> {

    /**
     * 人员调动申请列表总条数
     */
    default Long countStaffMovementApply(StaffMovementApplyDTO staffMovementApplyDTO) throws SerException {
        return null;
    }

    /**
     * 人员调动申请
     *
     * @param staffMovementApplyDTO 人员调动申请dto
     * @return class StaffMovementApplyBO
     * @throws SerException
     */
    default List<StaffMovementApplyBO> findListStaffMovementApply(StaffMovementApplyDTO staffMovementApplyDTO) throws SerException {
        return null;
    }

    /**
     * 添加人员调动申请
     *
     * @param staffMovementApplyTO 人员调动申请数据to
     * @return class StaffMovementApplyBO
     * @throws SerException
     */
    default StaffMovementApplyBO insertStaffMovementApply(StaffMovementApplyTO staffMovementApplyTO) throws SerException {
        return null;
    }

    /**
     * 编辑人员调动申请
     *
     * @param staffMovementApplyTO 人员调动申请数据to
     * @return class StaffMovementApplyBO
     * @throws SerException
     */
    default StaffMovementApplyBO editStaffMovementApply(StaffMovementApplyTO staffMovementApplyTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除人员调动申请
     *
     * @param id
     * @throws SerException
     */
    default void removeStaffMovementApply(String id) throws SerException {

    }

    /**
     * 审核
     *
     * @return class StaffMovementApplyBO
     */
    default StaffMovementApplyBO auditStaffMovementApply(StaffMovementApplyTO staffMovementApplyTO) throws SerException {
        return null;
    }

}