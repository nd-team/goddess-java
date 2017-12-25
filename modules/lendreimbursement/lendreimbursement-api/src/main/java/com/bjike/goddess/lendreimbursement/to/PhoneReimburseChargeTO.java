package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 报销负责人审核
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销负责人审核 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneReimburseChargeTO extends BaseTO {
    public interface TestChargeAudit {
    }


    /**
     * 审核情况(通过/不通过)
     */
    @NotBlank(groups = {PhoneReimburseChargeTO.TestChargeAudit.class }, message = "审核情况不能为空(通过/不通过)")
    private String chargerAuditStatus;

    /**
     * 审核意见
     */
    private String auditAdvice;



    public String getChargerAuditStatus() {
        return chargerAuditStatus;
    }

    public void setChargerAuditStatus(String chargerAuditStatus) {
        this.chargerAuditStatus = chargerAuditStatus;
    }

    public String getAuditAdvice() {
        return auditAdvice;
    }

    public void setAuditAdvice(String auditAdvice) {
        this.auditAdvice = auditAdvice;
    }
}