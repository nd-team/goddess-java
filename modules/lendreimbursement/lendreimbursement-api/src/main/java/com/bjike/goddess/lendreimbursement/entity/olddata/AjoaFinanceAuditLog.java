package com.bjike.goddess.lendreimbursement.entity.olddata;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 老系统的财务审核日志
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 11:43 ]
 * @Description: [ 老系统的财务审核日志 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "aj_oa_finance_auditLogs")
public class AjoaFinanceAuditLog extends BaseEntity {

    /**
     * 审核人员职位名称
     */
    @Column(name = "positionName",  columnDefinition = "VARCHAR(255)   COMMENT '审核人员职位名称'")
    private String positionName;

    /**
     * 审核人姓名
     */
    @Column(name = "auditorName",  columnDefinition = "VARCHAR(255)   COMMENT '审核人姓名'")
    private String auditorName;

    /**
     * 审核意见
     */
    @Column(name = "auditorIdea",  columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String auditorIdea;

    /**
     * 审核的三种状态NONE(0),未处理-ALLOWED(1)通过-DENIED(2)//拒绝
     */
    @Column(name = "auditState",  columnDefinition = "INT(2)   COMMENT '审核的三种状态'")
    private int auditState;

    /**
     * 审核时间
     */
    @Column(name = "auditTime",  columnDefinition = "DATETIME   COMMENT '审核时间'")
    private LocalDateTime auditTime;

    /**
     * 引用报销的id
     */
    @Column(name = "auditId",  columnDefinition = "VARCHAR(255)   COMMENT '引用报销的id'")
    private String auditId;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 状态
     */
    @Column(name = "status",  columnDefinition = "INT(2)   COMMENT '状态'")
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

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
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