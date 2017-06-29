package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAuditLogBO;
import com.bjike.goddess.lendreimbursement.entity.ReimburseAuditLog;
import com.bjike.goddess.lendreimbursement.dto.ReimburseAuditLogDTO;
import com.bjike.goddess.lendreimbursement.to.GuidePermissionTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseAuditLogTO;

import java.util.List;

/**
 * 报销审核日志表业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:48 ]
 * @Description: [ 报销审核日志表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReimburseAuditLogSer extends Ser<ReimburseAuditLog, ReimburseAuditLogDTO> {

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
     * 申请报销列表总条数
     */
    default Long countReimburseAuditLog(ReimburseAuditLogDTO reimburseAuditLogDTO) throws SerException {
        return null;
    }

    /**
     * 申请报销列表
     *
     * @return class ReimburseAuditLogBO
     */
    default List<ReimburseAuditLogBO> listReimburseAuditLog(ReimburseAuditLogDTO reimburseAuditLogDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param reimburseAuditLogTO 申请报销信息
     * @return class ReimburseAuditLogBO
     */
    default ReimburseAuditLogBO addReimburseAuditLog(ReimburseAuditLogTO reimburseAuditLogTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param reimburseAuditLogTO 申请报销信息
     * @return class ReimburseAuditLogBO
     */
    default ReimburseAuditLogBO editReimburseAuditLog(ReimburseAuditLogTO reimburseAuditLogTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteReimburseAuditLog(String id) throws SerException {
        return;
    }

    ;

    /**
     * 根据报销id查报销审核
     *
     * @return class ReimburseAuditLogBO
     */
    default List<ReimburseAuditLogBO> listReimburseAuditLogByRid(String reimburseId) throws SerException {
        return null;
    }



}