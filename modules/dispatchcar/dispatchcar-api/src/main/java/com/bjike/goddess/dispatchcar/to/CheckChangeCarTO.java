package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
* 薪资测算结果
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-25 11:24 ]
* @Description:	[ 薪资测算结果 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CheckChangeCarTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 问题类型
     */
    private String  problemType;

    /**
     * 问题描述
     */
    private String  problemDes;

    /**
     * 账务模块意见
     */
    private String accountModuleIdea;

    /**
     * 收票人
     */
    private String receiver;

    /**
     * 收到发票日期
     */
    private String receiveDate;

    /**
     * 收到发票情况
     */
    private String receiveReceipts;

    /**
     * 是否收到票据
     */
    @NotBlank(groups = {CheckChangeCarTO.TestAdd.class},message = "是否收到票据")
    private Boolean ifCorrect;

    /**
     * 财务核对意见
     */
    @NotBlank(groups = {CheckChangeCarTO.TestAdd.class},message = "核对意见不能为空")
    private String auditSugg;

    /**
     * 财务核对是否通过
     */
    @NotBlank(groups = {CheckChangeCarTO.TestAdd.class},message = "财务核对是否通过")
    private Boolean ifFinancePass;


    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDes() {
        return problemDes;
    }

    public void setProblemDes(String problemDes) {
        this.problemDes = problemDes;
    }

    public String getAuditSugg() {
        return auditSugg;
    }

    public void setAuditSugg(String auditSugg) {
        this.auditSugg = auditSugg;
    }

    public String getAccountModuleIdea() {
        return accountModuleIdea;
    }

    public void setAccountModuleIdea(String accountModuleIdea) {
        this.accountModuleIdea = accountModuleIdea;
    }

    public Boolean getIfCorrect() {
        return ifCorrect;
    }

    public void setIfCorrect(Boolean ifCorrect) {
        this.ifCorrect = ifCorrect;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveReceipts() {
        return receiveReceipts;
    }

    public void setReceiveReceipts(String receiveReceipts) {
        this.receiveReceipts = receiveReceipts;
    }

    public Boolean getIfFinancePass() {
        return ifFinancePass;
    }

    public void setIfFinancePass(Boolean ifFinancePass) {
        this.ifFinancePass = ifFinancePass;
    }
}