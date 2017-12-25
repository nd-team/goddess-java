package com.bjike.goddess.foreigntax.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.foreigntax.enums.PaymentStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 税金管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaxManagementTO extends BaseTO {

    /**
     * 公司
     */
    @NotBlank(message = "公司不能为空", groups = {ADD.class, EDIT.class})
    private String company;

    /**
     * 国地税标志
     */
    @NotBlank(message = "国地税标志不能为空", groups = {ADD.class})
    private String landTaxMark;
    /**
     * 税款所属期起
     */
    @NotBlank(message = "税款所属期起不能为空", groups = {ADD.class})
    private String taxStart;
    /**
     * 税款所属期止
     */
    @NotBlank(message = "税款所属期止不能为空", groups = {ADD.class})
    private String taxEnd;

    /**
     * 税种品名
     */
    @NotBlank(message = "税种品名不能为空", groups = {ADD.class})
    private String taxType;

    /**
     * 税率(%)
     */
    @NotNull(message = "税率(%)不能为空", groups = {ADD.class})
    private Double rate;

    /**
     * 税金
     */
    @NotNull(message = "税金不能为空", groups = {ADD.class, EDIT.class})
    private Double tax;

    /**
     * 申报期限
     */
    @NotBlank(message = "申报期限不能为空", groups = {ADD.class})
    private String deadlineFor;
    /**
     * 申报日期
     */
    @NotBlank(message = "申报日期不能为空", groups = {ADD.class, EDIT.class})
    private String deadlineDate;

    /**
     * 缴税状态
     */
    @NotNull(message = "缴税状态不能为空", groups = {ADD.class, EDIT.class})
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