package com.bjike.goddess.attendance.to.overtime;

import com.bjike.goddess.attendance.enums.AuditStatus;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 加班审核
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班审核 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverWorkAuditTO extends BaseTO {

    public  interface TESTAddAndEdit{}


    /**
     * 审核意见
     */
    private String auditAdvice;

    /**
     * 审核状态
     */
    @NotNull(groups = {OverWorkAuditTO.TESTAddAndEdit.class} , message = "审核状态不能为空")
    private AuditStatus auditStatus;




    public String getAuditAdvice() {
        return auditAdvice;
    }

    public void setAuditAdvice(String auditAdvice) {
        this.auditAdvice = auditAdvice;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }
}