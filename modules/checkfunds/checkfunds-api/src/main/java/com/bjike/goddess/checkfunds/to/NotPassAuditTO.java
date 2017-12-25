package com.bjike.goddess.checkfunds.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
 * 审批不通过记录
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:23 ]
 * @Description: [ 审批不通过记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NotPassAuditTO extends BaseTO {

    /**
     * 核对状态
     */
    private String auditStatus;

    /**
     * 银行对账信息
     */
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

