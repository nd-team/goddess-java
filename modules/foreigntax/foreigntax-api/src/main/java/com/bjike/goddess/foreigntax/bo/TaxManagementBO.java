package com.bjike.goddess.foreigntax.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.foreigntax.enums.PaymentStatus;

import javax.persistence.Column;

/**
 * 税金管理业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaxManagementBO extends BaseBO {

    /**
     * 公司
     */
    private String company;

    /**
     * 国地税标志
     */
    private String landTaxMark;
    /**
     * 税款所属期起
     */
    private String taxStart;
    /**
     * 税款所属期止
     */
    private String taxEnd;

    /**
     * 税种品名
     */
    private String taxType;

    /**
     * 税率(%)
     */
    private Double rate;

    /**
     * 税金
     */
    private Double tax;

    /**
     * 申报期限
     */
    private String deadlineFor;
    /**
     * 申报日期
     */
    private String deadlineDate;

    /**
     * 缴税状态
     */
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

    public String getTaxStart() {
        return taxStart;
    }

    public void setTaxStart(String taxStart) {
        this.taxStart = taxStart;
    }

    public String getTaxEnd() {
        return taxEnd;
    }

    public void setTaxEnd(String taxEnd) {
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

    public String getDeadlineFor() {
        return deadlineFor;
    }

    public void setDeadlineFor(String deadlineFor) {
        this.deadlineFor = deadlineFor;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}