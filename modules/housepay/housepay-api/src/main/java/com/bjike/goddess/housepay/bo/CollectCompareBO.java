package com.bjike.goddess.housepay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-5-15.
 */
public class CollectCompareBO extends BaseBO {
    private Integer month;
    private String projectGroup;
    private Double lastReserveSum;
    private Double reserveSum;
    private Double balance;
    private Double increase;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
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
