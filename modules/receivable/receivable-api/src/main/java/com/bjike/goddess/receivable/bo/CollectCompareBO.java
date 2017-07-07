package com.bjike.goddess.receivable.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.receivable.enums.CompareStatus;

/**
 * Created by ike on 17-6-20.
 */
public class CollectCompareBO extends BaseBO {
    /**
     * 条件
     */
    private String groupField;
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
    private Double phaseAccount;
    /**
     * 到账金额增长率
     */
    private Double rateAccount;
    /**
     * 到账金额百分比
     */
    private Double percentAccount;
    /**
     * 管理费
     */
    private Double managementFee;
    /**
     * 管理费差额
     */
    private Double phaseFee;
    /**
     * 管理费增长率
     */
    private Double rateFee;
    /**
     * 管理费百分比
     */
    private Double percentFee;
    /**
     * 税金
     */
    private Double taxes;
    /**
     * 税金差额
     */
    private Double phaseTaxes;
    /**
     * 税金增长率
     */
    private Double rateTaxes;
    /**
     * 税金百分比
     */
    private Double percentTaxes;
    /**
     * 税后金额
     */
    private Double afterTax;
    /**
     * 税后金额差额
     */
    private Double phaseAfterTax;
    /**
     * 税后金额增长率
     */
    private Double rateAfterTax;
    /**
     * 税后金额百分比
     */
    private Double percentAfterTax;

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }


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

    public Double getPhaseAccount() {
        return phaseAccount;
    }

    public void setPhaseAccount(Double phaseAccount) {
        this.phaseAccount = phaseAccount;
    }

    public Double getRateAccount() {
        return rateAccount;
    }

    public void setRateAccount(Double rateAccount) {
        this.rateAccount = rateAccount;
    }

    public Double getPercentAccount() {
        return percentAccount;
    }

    public void setPercentAccount(Double percentAccount) {
        this.percentAccount = percentAccount;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public Double getPhaseFee() {
        return phaseFee;
    }

    public void setPhaseFee(Double phaseFee) {
        this.phaseFee = phaseFee;
    }

    public Double getRateFee() {
        return rateFee;
    }

    public void setRateFee(Double rateFee) {
        this.rateFee = rateFee;
    }

    public Double getPercentFee() {
        return percentFee;
    }

    public void setPercentFee(Double percentFee) {
        this.percentFee = percentFee;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getPhaseTaxes() {
        return phaseTaxes;
    }

    public void setPhaseTaxes(Double phaseTaxes) {
        this.phaseTaxes = phaseTaxes;
    }

    public Double getRateTaxes() {
        return rateTaxes;
    }

    public void setRateTaxes(Double rateTaxes) {
        this.rateTaxes = rateTaxes;
    }

    public Double getPercentTaxes() {
        return percentTaxes;
    }

    public void setPercentTaxes(Double percentTaxes) {
        this.percentTaxes = percentTaxes;
    }

    public Double getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(Double afterTax) {
        this.afterTax = afterTax;
    }

    public Double getPhaseAfterTax() {
        return phaseAfterTax;
    }

    public void setPhaseAfterTax(Double phaseAfterTax) {
        this.phaseAfterTax = phaseAfterTax;
    }

    public Double getRateAfterTax() {
        return rateAfterTax;
    }

    public void setRateAfterTax(Double rateAfterTax) {
        this.rateAfterTax = rateAfterTax;
    }

    public Double getPercentAfterTax() {
        return percentAfterTax;
    }

    public void setPercentAfterTax(Double percentAfterTax) {
        this.percentAfterTax = percentAfterTax;
    }
}
