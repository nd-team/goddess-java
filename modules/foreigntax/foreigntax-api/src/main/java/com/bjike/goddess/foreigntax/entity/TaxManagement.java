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
    @Column(name = "company",  columnDefinition = "VARCHAR(255)   COMMENT '公司'")
    private String company;

    /**
     * 国地税标志
     */
    @Column(name = "landTaxMark", columnDefinition = "VARCHAR(255)   COMMENT '国地税标志'")
    private String landTaxMark;
    /**
     * 税款所属期起
     */
    @Column(name = "taxStart", columnDefinition = "DATE   COMMENT '税款所属期起'")
    private LocalDate taxStart;
    /**
     * 税款所属期止
     */
    @Column(name = "taxEnd", columnDefinition = "DATE   COMMENT '税款所属期止'")
    private LocalDate taxEnd;

    /**
     * 税种品名
     */
    @Column(name = "taxType", columnDefinition = "VARCHAR(255)   COMMENT '税种品名'")
    private String taxType;

    /**
     * 税率(%)
     */
    @Column(name = "rate", columnDefinition = "DECIMAL(10,2)   COMMENT '税率(%)'")
    private Double rate;

    /**
     * 税金
     */
    @Column(name = "tax", columnDefinition = "DECIMAL(10,2)   COMMENT '税金'")
    private Double tax;

    /**
     * 申报期限
     */
    @Column(name = "deadlineFor", columnDefinition = "DATE   COMMENT '申报期限'")
    private LocalDate deadlineFor;
    /**
     * 申报日期
     */
    @Column(name = "deadlineDate", columnDefinition = "DATE   COMMENT '申报日期'")
    private LocalDate deadlineDate;

    /**
     * 缴税状态
     */
    @Column(name = "paymentStatus", columnDefinition = "TINYINT(2)   COMMENT '缴费状态'")
    private PaymentStatus paymentStatus;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLandTaxMark() {
        return landTaxMark;
    }

    public void setLandTaxMark(String landTaxMark) {
        this.landTaxMark = landTaxMark;
    }

    public LocalDate getTaxStart() {
        return taxStart;
    }

    public void setTaxStart(LocalDate taxStart) {
        this.taxStart = taxStart;
    }

    public LocalDate getTaxEnd() {
        return taxEnd;
    }

    public void setTaxEnd(LocalDate taxEnd) {
        this.taxEnd = taxEnd;
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

    public LocalDate getDeadlineFor() {
        return deadlineFor;
    }

    public void setDeadlineFor(LocalDate deadlineFor) {
        this.deadlineFor = deadlineFor;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}