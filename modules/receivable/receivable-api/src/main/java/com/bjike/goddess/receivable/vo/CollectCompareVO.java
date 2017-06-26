package com.bjike.goddess.receivable.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-6-20.
 */
public class CollectCompareVO{
    /**
     * 派工单价
     */
    private Double taskPrice;
    /**
     * 已派工量
     */
    private Double pactSize;
    /**
     * 到账金额
     */
    private Double accountMoney;
    /**
     * 到账金额差额
     */
    private Double accountMoneyMinusMoney;
    /**
     * 到账金额增长率
     */
    private Double accountMoneyIncrease;
    /**
     * 到账金额百分比
     */
    private Double accountMoneyPercentage;
    /**
     * 管理费
     */
    private Double managementFee;
    /**
     * 管理费差额
     */
    private Double managementFeeMinusMoney;
    /**
     * 管理费增长率
     */
    private Double managementFeeIncrease;
    /**
     * 管理费百分比
     */
    private Double managementFeePercentage;
    /**
     * 税金
     */
    private Double taxes;
    /**
     * 税金差额
     */
    private Double taxesMinusMoney;
    /**
     * 税金增长率
     */
    private Double taxesIncrease;
    /**
     * 税金百分比
     */
    private Double taxesPercentage;
    /**
     * 税后金额
     */
    private Double afterTax;
    /**
     * 税后金额差额
     */
    private Double afterTaxMinusMoney;
    /**
     * 税后金额增长率
     */
    private Double afterTaxIncrease;
    /**
     * 税后金额百分比
     */
    private Double afterTaxPercentage;

    public Double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Double taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Double getPactSize() {
        return pactSize;
    }

    public void setPactSize(Double pactSize) {
        this.pactSize = pactSize;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getAccountMoneyMinusMoney() {
        return accountMoneyMinusMoney;
    }

    public void setAccountMoneyMinusMoney(Double accountMoneyMinusMoney) {
        this.accountMoneyMinusMoney = accountMoneyMinusMoney;
    }

    public Double getAccountMoneyIncrease() {
        return accountMoneyIncrease;
    }

    public void setAccountMoneyIncrease(Double accountMoneyIncrease) {
        this.accountMoneyIncrease = accountMoneyIncrease;
    }

    public Double getAccountMoneyPercentage() {
        return accountMoneyPercentage;
    }

    public void setAccountMoneyPercentage(Double accountMoneyPercentage) {
        this.accountMoneyPercentage = accountMoneyPercentage;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public Double getManagementFeeMinusMoney() {
        return managementFeeMinusMoney;
    }

    public void setManagementFeeMinusMoney(Double managementFeeMinusMoney) {
        this.managementFeeMinusMoney = managementFeeMinusMoney;
    }

    public Double getManagementFeeIncrease() {
        return managementFeeIncrease;
    }

    public void setManagementFeeIncrease(Double managementFeeIncrease) {
        this.managementFeeIncrease = managementFeeIncrease;
    }

    public Double getManagementFeePercentage() {
        return managementFeePercentage;
    }

    public void setManagementFeePercentage(Double managementFeePercentage) {
        this.managementFeePercentage = managementFeePercentage;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getTaxesMinusMoney() {
        return taxesMinusMoney;
    }

    public void setTaxesMinusMoney(Double taxesMinusMoney) {
        this.taxesMinusMoney = taxesMinusMoney;
    }

    public Double getTaxesIncrease() {
        return taxesIncrease;
    }

    public void setTaxesIncrease(Double taxesIncrease) {
        this.taxesIncrease = taxesIncrease;
    }

    public Double getTaxesPercentage() {
        return taxesPercentage;
    }

    public void setTaxesPercentage(Double taxesPercentage) {
        this.taxesPercentage = taxesPercentage;
    }

    public Double getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(Double afterTax) {
        this.afterTax = afterTax;
    }

    public Double getAfterTaxMinusMoney() {
        return afterTaxMinusMoney;
    }

    public void setAfterTaxMinusMoney(Double afterTaxMinusMoney) {
        this.afterTaxMinusMoney = afterTaxMinusMoney;
    }

    public Double getAfterTaxIncrease() {
        return afterTaxIncrease;
    }

    public void setAfterTaxIncrease(Double afterTaxIncrease) {
        this.afterTaxIncrease = afterTaxIncrease;
    }

    public Double getAfterTaxPercentage() {
        return afterTaxPercentage;
    }

    public void setAfterTaxPercentage(Double afterTaxPercentage) {
        this.afterTaxPercentage = afterTaxPercentage;
    }
}
