package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 报销去付款和预计付款
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销去付款和预计付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneReimbursePayTO extends BaseTO {
    public interface TestPay {
    }
    public interface TestPrePay {
    }


    /**
     * 是否预计付款
     */
    @NotNull(groups = {PhoneReimbursePayTO.TestPay.class,PhoneReimbursePayTO.TestPrePay.class}, message = "是否预计付款不能为空")
    private Boolean payOrPre;

    /**
     * 付款来源(付款方式)
     */
    @NotBlank(groups = {PhoneReimbursePayTO.TestPay.class}, message = "付款来源不能为空")
    private String payOrigin;


    /**
     * 付款说明(收到发票情况/支付计划)
     */
    private String receiveTicketCon;


    /**
     * 报销金额
     */
    @NotNull(groups = {PhoneReimbursePayTO.TestPay.class,PhoneReimbursePayTO.TestPrePay.class}, message = "报销金额不能为空")
    private Double reimMoney;

    /**
     * 预计付款时间
     */
    @NotBlank(groups = {PhoneReimbursePayTO.TestPrePay.class}, message = "预计付款时间(年月日)不能为空")
    private String budgetPayTime;

    public Boolean getPayOrPre() {
        return payOrPre;
    }

    public void setPayOrPre(Boolean payOrPre) {
        this.payOrPre = payOrPre;
    }

    public String getPayOrigin() {
        return payOrigin;
    }

    public void setPayOrigin(String payOrigin) {
        this.payOrigin = payOrigin;
    }

    public String getReceiveTicketCon() {
        return receiveTicketCon;
    }

    public void setReceiveTicketCon(String receiveTicketCon) {
        this.receiveTicketCon = receiveTicketCon;
    }

    public Double getReimMoney() {
        return reimMoney;
    }

    public void setReimMoney(Double reimMoney) {
        this.reimMoney = reimMoney;
    }

    public String getBudgetPayTime() {
        return budgetPayTime;
    }

    public void setBudgetPayTime(String budgetPayTime) {
        this.budgetPayTime = budgetPayTime;
    }
}