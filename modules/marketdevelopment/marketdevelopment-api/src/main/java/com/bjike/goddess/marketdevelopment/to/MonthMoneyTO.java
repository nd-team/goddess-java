package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 业务方向类型数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 02:11 ]
 * @Description: [ 业务方向类型数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthMoneyTO extends BaseTO {

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    /**
     * 目标金额（万元）
     */
    private Double targetMoney;

    /**
     * 计划金额（万元）
     */
    private Double planMoney;

    /**
     * 实际金额（万元）
     */
    private Double actualMoney;

    /**
     * 差异金额（万元）
     */
    private Double diferenceMoney;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Double getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(Double planMoney) {
        this.planMoney = planMoney;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Double getDiferenceMoney() {
        return diferenceMoney;
    }

    public void setDiferenceMoney(Double diferenceMoney) {
        this.diferenceMoney = diferenceMoney;
    }
}