package com.bjike.goddess.businessevaluate.bo;

/**
 * 各月金额差额
 *
 * @Author: [Jason]
 * @Date: [17-3-29 上午10:52]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SwapMonthBO {

    /**
     * 月份
     */
    private String month;
    /**
     * 月份差额
     */
    private Double gapMoney;
    /**
     * 当月金额
     */
    private Double monthMoney;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getGapMoney() {
        return gapMoney;
    }

    public void setGapMoney(Double gapMoney) {
        this.gapMoney = gapMoney;
    }

    public Double getMonthMoney() {
        return monthMoney;
    }

    public void setMonthMoney(Double monthMoney) {
        this.monthMoney = monthMoney;
    }

    public SwapMonthBO() {
    }

    public SwapMonthBO(String month, Double gapMoney, Double monthMoney) {
        this.month = month;
        this.gapMoney = gapMoney;
        this.monthMoney = monthMoney;
    }
}
