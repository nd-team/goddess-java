package com.bjike.goddess.businessprojectmanage.vo;

/**
 * @Author: [caiwenxian]
 * @Date: [2017-12-11 14:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BusinessContractCollectVO {

    /**
     * 所属项目组
     */
    private String projectGroup;

    /**
     * 项目总规模
     */
    private String scaleContract;

    /**
     * 项目总金额
     */
    private Double totalMoney;

    /**
     * 新增合同数
     */
    private Integer contractAmount;

    /**
     * 新增合同金额(派工金额总和)
     */
    private String contractMoney;

    /**
     * 已立项合同规模 (“是否有合同立项”为“立项”总数)
     */
    private String makeContractAmount;

    /**
     * 已立项合同金额 ("是否有合同立项"为"立项"的立项总金额)
     */
    private Double makeMoney;

    /**
     * 预立项合同规模(“是否有合同立项”为“预立项”总数)
     */
    private String forecastContractAmount;

    /**
     * 预立项合同金额　("是否有合同立项"为"预立项"的预估总金额)
     */
    private Double forecastMoney;
    /**
     * 已完工规模
     */
    private String complete;

    /**
     * 已完工金额 ("完工"为"是"的"派工金额")
     */
    private Double completeMoney;

    /**
     * 未完工规模("合同规模数量"-"规模数量")
     */
    private String notCompleteContract;

    /**
     * 未完工金额 ("完工"为"否"的"派工金额")
     */
    private Double notCompleteMoney;

    /**
     * 需项目总结数量 ("计划商务总结开始时间" > NOW())
     */
    private Integer needSummarizeAmount;

    /**
     * 已完成项目总结 ("计划商务总结完成时间"中"状态"为"总结完成")
     */
    private Integer summarizeAmount;

    /**
     * 未完成项目总结 ("需项目总结数量"-"已完成项目总结")
     */
    private Integer notSummarizeAmount;


    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getScaleContract() {
        return scaleContract;
    }

    public void setScaleContract(String scaleContract) {
        this.scaleContract = scaleContract;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Integer contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getMakeContractAmout() {
        return makeContractAmount;
    }

    public void setMakeContractAmout(String makeContractAmout) {
        this.makeContractAmount = makeContractAmout;
    }

    public Double getMakeMoney() {
        return makeMoney;
    }

    public void setMakeMoney(Double makeMoney) {
        this.makeMoney = makeMoney;
    }

    public String getForecastContractAmount() {
        return forecastContractAmount;
    }

    public void setForecastContractAmount(String forecastContractAmount) {
        this.forecastContractAmount = forecastContractAmount;
    }

    public Double getForecastMoney() {
        return forecastMoney;
    }

    public void setForecastMoney(Double forecastMoney) {
        this.forecastMoney = forecastMoney;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public Double getCompleteMoney() {
        return completeMoney;
    }

    public void setCompleteMoney(Double completeMoney) {
        this.completeMoney = completeMoney;
    }

    public String getNotCompleteContract() {
        return notCompleteContract;
    }

    public void setNotCompleteContract(String notCompleteContract) {
        this.notCompleteContract = notCompleteContract;
    }

    public Double getNotCompleteMoney() {
        return notCompleteMoney;
    }

    public void setNotCompleteMoney(Double notCompleteMoney) {
        this.notCompleteMoney = notCompleteMoney;
    }

    public Integer getNeedSummarizeAmount() {
        return needSummarizeAmount;
    }

    public void setNeedSummarizeAmount(Integer needSummarizeAmount) {
        this.needSummarizeAmount = needSummarizeAmount;
    }

    public Integer getSummarizeAmount() {
        return summarizeAmount;
    }

    public void setSummarizeAmount(Integer summarizeAmount) {
        this.summarizeAmount = summarizeAmount;
    }

    public Integer getNotSummarizeAmount() {
        return notSummarizeAmount;
    }

    public void setNotSummarizeAmount(Integer notSummarizeAmount) {
        this.notSummarizeAmount = notSummarizeAmount;
    }
}
