package com.bjike.goddess.outcarfare.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资金准备审核汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-05-05 16:30]
 * @Description: [资金准备审核汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MoneyReadyCountBO extends BaseBO {
    /**
     * 项目组
     */
    private String groupTeam;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 上月准备金
     */
    private Double lastMonthReserveSum;
    /**
     * 本月准备金
     */
    private Double currentMonthReserveSum;
    /**
     * 差额
     */
    private Double differences;
    /**
     * 增长率
     */
    private Double growth;

    public String getGroupTeam() {
        return groupTeam;
    }

    public void setGroupTeam(String groupTeam) {
        this.groupTeam = groupTeam;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getLastMonthReserveSum() {
        return lastMonthReserveSum;
    }

    public void setLastMonthReserveSum(Double lastMonthReserveSum) {
        this.lastMonthReserveSum = lastMonthReserveSum;
    }

    public Double getCurrentMonthReserveSum() {
        return currentMonthReserveSum;
    }

    public void setCurrentMonthReserveSum(Double currentMonthReserveSum) {
        this.currentMonthReserveSum = currentMonthReserveSum;
    }

    public Double getDifferences() {
        return differences;
    }

    public void setDifferences(Double differences) {
        this.differences = differences;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }
}
