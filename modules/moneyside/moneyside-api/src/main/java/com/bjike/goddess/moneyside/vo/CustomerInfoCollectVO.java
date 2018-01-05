package com.bjike.goddess.moneyside.vo;

/**
 * 客户信息汇总表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:11 ]
 * @Description: [ 客户信息汇总表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerInfoCollectVO {

    /**
     * 姓名
     */
    private String investor;

    /**
     * 资金进入方式
     */
    private String accessToFund;

    /**
     * 资金进入时间
     */
    private String fundEntryTime;

    /**
     * 进入金额
     */
    private Double thisInvestMoney;

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
    private Double needInvestAmount;

    /**
     * 收益分配时间
     */
    private String incomeDistributionTime;

    /**
     * 分配利率(%)
     */
    private Double proportionInvestment;

    /**
     * 分配额
     */
    private Double totalQuota;

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getAccessToFund() {
        return accessToFund;
    }

    public void setAccessToFund(String accessToFund) {
        this.accessToFund = accessToFund;
    }

    public String getFundEntryTime() {
        return fundEntryTime;
    }

    public void setFundEntryTime(String fundEntryTime) {
        this.fundEntryTime = fundEntryTime;
    }

    public Double getThisInvestMoney() {
        return thisInvestMoney;
    }

    public void setThisInvestMoney(Double thisInvestMoney) {
        this.thisInvestMoney = thisInvestMoney;
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

    public Double getNeedInvestAmount() {
        return needInvestAmount;
    }

    public void setNeedInvestAmount(Double needInvestAmount) {
        this.needInvestAmount = needInvestAmount;
    }

    public String getIncomeDistributionTime() {
        return incomeDistributionTime;
    }

    public void setIncomeDistributionTime(String incomeDistributionTime) {
        this.incomeDistributionTime = incomeDistributionTime;
    }

    public Double getProportionInvestment() {
        return proportionInvestment;
    }

    public void setProportionInvestment(Double proportionInvestment) {
        this.proportionInvestment = proportionInvestment;
    }

    public Double getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(Double totalQuota) {
        this.totalQuota = totalQuota;
    }
}