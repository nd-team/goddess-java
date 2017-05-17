package com.bjike.goddess.housepay.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-5-15.
 */
public class CollectCompareVO extends BaseBO {
    /**
     * 开始月份
     */
    private String startMonth;
    /**
     * 结束月份
     */
    private String endMonth;
    /**
     * 时间
     */
    private String time;
    /**
     * 项目组
     */
    private String projectGroup;
    /**
     * 上月准备金
     */
    private Double lastMonthReserves;
    /**
     * 本月准备金
     */
    private Double reserves;
    /**
     * 差额
     */
    private Double balance;
    /**
     * 增长率
     */
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

    public Double getLastMonthReserves() {
        return lastMonthReserves;
    }

    public void setLastMonthReserves(Double lastMonthReserves) {
        this.lastMonthReserves = lastMonthReserves;
    }

    public Double getReserves() {
        return reserves;
    }

    public void setReserves(Double reserves) {
        this.reserves = reserves;
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
