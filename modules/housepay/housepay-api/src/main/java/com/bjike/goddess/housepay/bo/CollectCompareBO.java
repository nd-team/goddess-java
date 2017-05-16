package com.bjike.goddess.housepay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-5-15.
 */
public class CollectCompareBO extends BaseBO {
    private String startMonth;
    private String endMonth;
    private String time;
    private String projectGroup;
    private Double monthReserves;
    private Double lastMonthReserves;
    private Double balance;
    private Double increase;

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getMonthReserves() {
        return monthReserves;
    }

    public void setMonthReserves(Double monthReserves) {
        this.monthReserves = monthReserves;
    }

    public Double getLastMonthReserves() {
        return lastMonthReserves;
    }

    public void setLastMonthReserves(Double lastMonthReserves) {
        this.lastMonthReserves = lastMonthReserves;
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
