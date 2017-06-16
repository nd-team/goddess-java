package com.bjike.goddess.moneyside.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 客户信息汇总业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:11 ]
 * @Description: [ 客户信息汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerInfoCollectBO extends BaseBO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 资金进入方式
     */
    private String accessToFund;

    /**
     * 资金进入时间
     */
    private String fundEntryAime;

    /**
     * 进入金额
     */
    private Double enterMoney;

    /**
     * 投资项目
     */
    private String investProject;

    /**
     * 投资总额
     */
    private Double investTotal;

    /**
     * 投资占比(%)
     */
    private Double investProportion;

    /**
     * 资金退出时间
     */
    private String exitTime;

    /**
     * 退出金额
     */
    private Double exitMoney;

    /**
     * 退出利息
     */
    private Double exitInterest;

    /**
     * 被转让人
     */
    private String transferee;

    /**
     * 转让金额
     */
    private Double transferAmount;

    /**
     * 收益分配时间
     */
    private String incomeAllotTime;

    /**
     * 分配利率(%)
     */
    private Double allotInterestRate;

    /**
     * 分配额
     */
    private Double quota;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToFund() {
        return accessToFund;
    }

    public void setAccessToFund(String accessToFund) {
        this.accessToFund = accessToFund;
    }

    public String getFundEntryAime() {
        return fundEntryAime;
    }

    public void setFundEntryAime(String fundEntryAime) {
        this.fundEntryAime = fundEntryAime;
    }

    public Double getEnterMoney() {
        return enterMoney;
    }

    public void setEnterMoney(Double enterMoney) {
        this.enterMoney = enterMoney;
    }

    public String getInvestProject() {
        return investProject;
    }

    public void setInvestProject(String investProject) {
        this.investProject = investProject;
    }

    public Double getInvestTotal() {
        return investTotal;
    }

    public void setInvestTotal(Double investTotal) {
        this.investTotal = investTotal;
    }

    public Double getInvestProportion() {
        return investProportion;
    }

    public void setInvestProportion(Double investProportion) {
        this.investProportion = investProportion;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public Double getExitMoney() {
        return exitMoney;
    }

    public void setExitMoney(Double exitMoney) {
        this.exitMoney = exitMoney;
    }

    public Double getExitInterest() {
        return exitInterest;
    }

    public void setExitInterest(Double exitInterest) {
        this.exitInterest = exitInterest;
    }

    public String getTransferee() {
        return transferee;
    }

    public void setTransferee(String transferee) {
        this.transferee = transferee;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getIncomeAllotTime() {
        return incomeAllotTime;
    }

    public void setIncomeAllotTime(String incomeAllotTime) {
        this.incomeAllotTime = incomeAllotTime;
    }

    public Double getAllotInterestRate() {
        return allotInterestRate;
    }

    public void setAllotInterestRate(Double allotInterestRate) {
        this.allotInterestRate = allotInterestRate;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }
}