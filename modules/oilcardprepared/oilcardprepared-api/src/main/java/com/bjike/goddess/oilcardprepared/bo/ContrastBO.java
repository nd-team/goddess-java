package com.bjike.goddess.oilcardprepared.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 已付款信息对比
 *
 * @Author: [chenjunhao]
 * @Date: [2017-05-12 16:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContrastBO extends BaseBO {
    /**
     * 月份
     */
    private Integer month;
    /**
     * 卡号
     */
    private String oilCardNumber;
    /**
     * 当前月合计
     */
    private Double currentSum;
    /**
     * 上月合计
     */
    private Double lastSum;
    /**
     * 差额
     */
    private Double differences;
    /**
     * 增长率
     */
    private Double growthRate;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public Double getCurrentSum() {
        return currentSum;
    }

    public void setCurrentSum(Double currentSum) {
        this.currentSum = currentSum;
    }

    public Double getLastSum() {
        return lastSum;
    }

    public void setLastSum(Double lastSum) {
        this.lastSum = lastSum;
    }

    public Double getDifferences() {
        return differences;
    }

    public void setDifferences(Double differences) {
        this.differences = differences;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }
}
