package com.bjike.goddess.accruedtax.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 应交税金
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:17 ]
 * @Description: [ 应交税金 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "accruedtax_paytax")
public class PayTax extends BaseEntity {

    /**
     * 公司
     */
    @Column(name = "company", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司'")
    private String company;

    /**
     * 日期
     */
    @Column(name = "taxDate", nullable = false, columnDefinition = "DATE  COMMENT '日期'")
    private LocalDate taxDate;

    /**
     * 税种
     */
    @Column(name = "taxType",  columnDefinition = "VARCHAR(255)   COMMENT '税种'")
    private String taxType;

    /**
     * 税率
     */
    @Column(name = "taxRate",  columnDefinition = "DECIMAL(10,2)   COMMENT '税率'")
    private Double taxRate;

    /**
     * 目标税金
     */
    @Column(name = "targetTax",  columnDefinition = "DECIMAL(10,2)   COMMENT '目标税金'")
    private Double targetTax;

    /**
     * 计划税金
     */
    @Column(name = "planTax",  columnDefinition = "DECIMAL(10,2)   COMMENT '计划税金'")
    private Double planTax;

    /**
     * 实际税金
     */
    @Column(name = "actualTax",  columnDefinition = "DECIMAL(10,2)   COMMENT '实际税金'")
    private Double actualTax;

    /**
     * 比率
     */
    @Column(name = "rate",  columnDefinition = "DECIMAL(10,2)   COMMENT '比率'")
    private Double rate;

    /**
     * 差额
     */
    @Column(name = "balance",  columnDefinition = "DECIMAL(10,2)   COMMENT '差额'")
    private Double balance;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(LocalDate taxDate) {
        this.taxDate = taxDate;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTargetTax() {
        return targetTax;
    }

    public void setTargetTax(Double targetTax) {
        this.targetTax = targetTax;
    }

    public Double getPlanTax() {
        return planTax;
    }

    public void setPlanTax(Double planTax) {
        this.planTax = planTax;
    }

    public Double getActualTax() {
        return actualTax;
    }

    public void setActualTax(Double actualTax) {
        this.actualTax = actualTax;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}