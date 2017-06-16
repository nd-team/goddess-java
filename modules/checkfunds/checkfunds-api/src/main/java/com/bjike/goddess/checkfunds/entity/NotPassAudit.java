package com.bjike.goddess.checkfunds.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 审批不通过记录
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:23 ]
 * @Description: [ 审批不通过记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "checkfunds_notpassaudit")
public class NotPassAudit extends BaseEntity {

    /**
     * 核对状态
     */
    @Column(name = "auditStatus", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '核对状态'")
    private String auditStatus;

    /**
     * 银行对账信息
     */
    @Column(name = "bankReconciliation_id", unique = true, nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '核对状态'")
    private String bankReconciliationId;

    public String getBankReconciliationId() {
        return bankReconciliationId;
    }

    public void setBankReconciliationId(String bankReconciliationId) {
        this.bankReconciliationId = bankReconciliationId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}