package com.bjike.goddess.accruedtax.vo;

/**
 * 应交税金表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:17 ]
 * @Description: [ 应交税金表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PayTaxVO {

    /**
     * id
     */
    private String id;
    /**
     * 公司
     */
    private String company;

    /**
     * 日期
     */
    private String taxDate;

    /**
     * 税种
     */
    private String taxType;

    /**
     * 税率
     */
    private Double taxRate;

    /**
     * 目标税金
     */
    private Double targetTax;

    /**
     * 计划税金
     */
    private Double planTax;

    /**
     * 实际税金
     */
    private Double actualTax;

    /**
     * 比率
     */
    private Double rate;

    /**
     * 差额
     */
    private Double balance;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(String taxDate) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}