package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 报销分析人分析和申请冻结
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销分析人分析和申请冻结 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneReimburseAnalisisTO extends BaseTO {
    public interface TestAnalysis {
    }


    /**
     * 审核情况(通过/不通过)
     * 审核情况(已分析/申请冻结)
     */
    @NotBlank(groups = {PhoneReimburseAnalisisTO.TestAnalysis.class }, message = "审核情况不能为空(已分析/申请冻结)")
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