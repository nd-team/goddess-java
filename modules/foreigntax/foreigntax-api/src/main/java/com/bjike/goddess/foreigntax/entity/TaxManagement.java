package com.bjike.goddess.foreigntax.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.foreigntax.enums.PaymentStatus;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 税金管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "foreigntax_taxmanagement")
public class TaxManagement extends BaseEntity {

    /**
     * 公司
     */
    @Column(name = "company", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司'")
    private String company;

    /**
     * 所属月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属月份'")
    private String month;

    /**
     * 税种
     */
    @Column(name = "taxType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '税种'")
    private String taxType;

    /**
     * 税率(%)
     */
    @Column(name = "rate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税率(%)'")
    private Double rate;

    /**
     * 税金
     */
    @Column(name = "tax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税金'")
    private Double tax;

    /**
     * 缴税状态
     */
    @Column(name = "paymentStatus", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '缴费状态'")
    private PaymentStatus paymentStatus;

    /**
     * 付款日期
     */
    @Column(name = "paymentDate", nullable = false, columnDefinition = "DATE   COMMENT '付款日期'")
    private LocalDate paymentDate;

    /**
     * 付款单位
     */
    @Column(name = "paymentUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '付款单位'")
    private String paymentUnit;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentUnit() {
        return paymentUnit;
    }

    public void setPaymentUnit(String paymentUnit) {
        this.paymentUnit = paymentUnit;
    }
}