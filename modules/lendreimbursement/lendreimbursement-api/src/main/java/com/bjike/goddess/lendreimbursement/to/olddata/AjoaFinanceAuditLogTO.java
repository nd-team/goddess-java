package com.bjike.goddess.lendreimbursement.to.olddata;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 老系统的财务审核日志
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 11:43 ]
 * @Description: [ 老系统的财务审核日志 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AjoaFinanceAuditLogTO extends BaseTO {

    /**
     * 审核人员职位名称
     */
    private String positionName;

    /**
     * 审核人姓名
     */
    private String auditorName;

    /**
     * 审核意见
     */
    private String auditorIdea;

    /**
     * 审核的三种状态
     */
    private int auditState;

    /**
     * 审核时间
     */
    private String auditTime;

    /**
     * 引用报销的id
     */
    private String auditId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private int status;


    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getAuditorIdea() {
        return auditorIdea;
    }

    public void setAuditorIdea(String auditorIdea) {
        this.auditorIdea = auditorIdea;
    }

    public int getAuditState() {
        return auditState;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}