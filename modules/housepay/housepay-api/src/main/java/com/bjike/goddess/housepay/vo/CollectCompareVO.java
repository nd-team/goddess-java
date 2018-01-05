package com.bjike.goddess.housepay.vo;

/**
 * Created by ike on 17-5-15.
 */
public class CollectCompareVO {
    /**
     * 月份
     */
    private Integer month;
    /**
     * 项目组
     */
    private String projectGroup;
    /**
     * 上月准备金
     */
    private Double lastReserveSum;
    /**
     * 本月准备金
     */
    private Double reserveSum;
    /**
     * 差额
     */
    private Double balance;
    /**
     * 增长率
     */
    private Double increase;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getLastReserveSum() {
        return lastReserveSum;
    }

    public void setLastReserveSum(Double lastReserveSum) {
        this.lastReserveSum = lastReserveSum;
    }

    public Double getReserveSum() {
        return reserveSum;
    }

    public void setReserveSum(Double reserveSum) {
        this.reserveSum = reserveSum;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getIncrease() {
        return increase;
    }

    public void setIncrease(Double increase) {
        this.increase = increase;
    }
}
