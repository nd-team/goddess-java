package com.bjike.goddess.businessprojectmanage.vo;

/**
 * @Author: [caiwenxian]
 * @Date: [2017-12-12 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BusinessContractMoneyCollectVO {

    /**
     * 所属项目组
     */
    private String projectGroup;

    /**
     * 项目总金额
     */
    private Double totalMoney;

    /**
     * 新增合同金额(派工金额总和)
     */
    private Double contractMoney;

    /**
     * 已立项合同金额 ("是否有合同立项"为"立项"的立项总金额)
     */
    private Double makeMoney;

    /**
     * 预立项合同金额　("是否有合同立项"为"预立项"的预估总金额)
     */
    private Double forecastMoney;

    /**
     * 已完工金额 ("完工"为"是"的"派工金额")
     */
    private Double completeMoney;

    /**
     * 未完工金额 ("完工"为"否"的"派工金额")
     */
    private Double notCompleteMoney;

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
    }

    public Double getMakeMoney() {
        return makeMoney;
    }

    public void setMakeMoney(Double makeMoney) {
        this.makeMoney = makeMoney;
    }

    public Double getForecastMoney() {
        return forecastMoney;
    }

    public void setForecastMoney(Double forecastMoney) {
        this.forecastMoney = forecastMoney;
    }

    public Double getCompleteMoney() {
        return completeMoney;
    }

    public void setCompleteMoney(Double completeMoney) {
        this.completeMoney = completeMoney;
    }

    public Double getNotCompleteMoney() {
        return notCompleteMoney;
    }

    public void setNotCompleteMoney(Double notCompleteMoney) {
        this.notCompleteMoney = notCompleteMoney;
    }
}
