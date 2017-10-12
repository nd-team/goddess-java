package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 常用摘要
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:19 ]
 * @Description: [ 常用摘要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_usecommonly")
public class UseCommonly extends BaseEntity {

    /**
     * 提现
     */
    @Column(name = "withdrawal", columnDefinition = "VARCHAR(255)   COMMENT '提现'")
    private String withdrawal;

    /**
     * 存现
     */
    @Column(name = "existential", columnDefinition = "VARCHAR(255)   COMMENT '存现'")
    private String existential;

    /**
     * 计提本月工资
     */
    @Column(name = "provisionMonthSalary",  columnDefinition = "VARCHAR(255)   COMMENT '计提本月工资'")
    private String provisionMonthSalary;

    /**
     * 本月服务收入
     */
    @Column(name = "monthServiceIncome", columnDefinition = "VARCHAR(255)   COMMENT '本月服务收入'")
    private String monthServiceIncome;

    /**
     * 收到货款
     */
    @Column(name = "receivePayment",columnDefinition = "VARCHAR(255)   COMMENT '收到货款'")
    private String receivePayment;

    /**
     * 支付货款
     */
    @Column(name = "payment",  columnDefinition = "VARCHAR(255)   COMMENT '支付货款'")
    private String payment;

    /**
     * 支付本月社保
     */
    @Column(name = "payMonthSocialSecurity", columnDefinition = "VARCHAR(255)   COMMENT '支付本月社保'")
    private String payMonthSocialSecurity;

    /**
     * 支付办公费
     */
    @Column(name = "payOffice", columnDefinition = "VARCHAR(255)   COMMENT '支付办公费'")
    private String payOffice;

    /**
     * 报销差旅费
     */
    @Column(name = "reimburseBusinessTravel",  columnDefinition = "VARCHAR(255)   COMMENT '报销差旅费'")
    private String reimburseBusinessTravel;

    /**
     * 支付交通费
     */
    @Column(name = "payTransportation", columnDefinition = "VARCHAR(255)   COMMENT '支付交通费'")
    private String payTransportation;

    /**
     * 支付业务招待费
     */
    @Column(name = "payEntertainment", columnDefinition = "VARCHAR(255)   COMMENT '支付业务招待费'")
    private String payEntertainment;

    /**
     * 支付本月税金
     */
    @Column(name = "payMonthTaxes", columnDefinition = "VARCHAR(255)   COMMENT '支付本月税金'")
    private String payMonthTaxes;

    /**
     * 计提本月税金及附加
     */
    @Column(name = "provisionTaxesAttach", columnDefinition = "VARCHAR(255)   COMMENT '计提本月税金及附加'")
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