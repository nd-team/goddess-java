package com.bjike.goddess.financeinit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 常用摘要业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:19 ]
 * @Description: [ 常用摘要业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class UseCommonlyBO extends BaseBO {

    /**
     * 提现
     */
    private String withdrawal;

    /**
     * 存现
     */
    private String existential;

    /**
     * 计提本月工资
     */
    private String provisionMonthSalary;

    /**
     * 本月服务收入
     */
    private String monthServiceIncome;

    /**
     * 收到货款
     */
    private String receivePayment;

    /**
     * 支付货款
     */
    private String payment;

    /**
     * 支付本月社保
     */
    private String payMonthSocialSecurity;

    /**
     * 支付办公费
     */
    private String payOffice;

    /**
     * 报销差旅费
     */
    private String reimburseBusinessTravel;

    /**
     * 支付交通费
     */
    private String payTransportation;

    /**
     * 支付业务招待费
     */
    private String payEntertainment;

    /**
     * 支付本月税金
     */
    private String payMonthTaxes;

    /**
     * 计提本月税金及附加
     */
    private String provisionTaxesAttach;


    public String getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getExistential() {
        return existential;
    }

    public void setExistential(String existential) {
        this.existential = existential;
    }

    public String getProvisionMonthSalary() {
        return provisionMonthSalary;
    }

    public void setProvisionMonthSalary(String provisionMonthSalary) {
        this.provisionMonthSalary = provisionMonthSalary;
    }

    public String getMonthServiceIncome() {
        return monthServiceIncome;
    }

    public void setMonthServiceIncome(String monthServiceIncome) {
        this.monthServiceIncome = monthServiceIncome;
    }

    public String getReceivePayment() {
        return receivePayment;
    }

    public void setReceivePayment(String receivePayment) {
        this.receivePayment = receivePayment;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPayMonthSocialSecurity() {
        return payMonthSocialSecurity;
    }

    public void setPayMonthSocialSecurity(String payMonthSocialSecurity) {
        this.payMonthSocialSecurity = payMonthSocialSecurity;
    }

    public String getPayOffice() {
        return payOffice;
    }

    public void setPayOffice(String payOffice) {
        this.payOffice = payOffice;
    }

    public String getReimburseBusinessTravel() {
        return reimburseBusinessTravel;
    }

    public void setReimburseBusinessTravel(String reimburseBusinessTravel) {
        this.reimburseBusinessTravel = reimburseBusinessTravel;
    }

    public String getPayTransportation() {
        return payTransportation;
    }

    public void setPayTransportation(String payTransportation) {
        this.payTransportation = payTransportation;
    }

    public String getPayEntertainment() {
        return payEntertainment;
    }

    public void setPayEntertainment(String payEntertainment) {
        this.payEntertainment = payEntertainment;
    }

    public String getPayMonthTaxes() {
        return payMonthTaxes;
    }

    public void setPayMonthTaxes(String payMonthTaxes) {
        this.payMonthTaxes = payMonthTaxes;
    }

    public String getProvisionTaxesAttach() {
        return provisionTaxesAttach;
    }

    public void setProvisionTaxesAttach(String provisionTaxesAttach) {
        this.provisionTaxesAttach = provisionTaxesAttach;
    }
}