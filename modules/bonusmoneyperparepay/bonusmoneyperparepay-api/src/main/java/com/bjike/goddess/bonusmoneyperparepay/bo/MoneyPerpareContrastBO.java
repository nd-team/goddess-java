package com.bjike.goddess.bonusmoneyperparepay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 奖金资金准备对比业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:59 ]
 * @Description: [ 奖金资金准备对比业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyPerpareContrastBO extends BaseBO {

    /**
     * 年份
     */
    private Integer years;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 科目
     */
    private String subjects;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 上月资金准备金
     */
    private Double lastMonthReserve;

    /**
     * 本月资金准备金
     */
    private Double thisMonthReserve;

    /**
     * 差额
     */
    private Double difference;

    /**
     * 增长率
     */
    private Double growthRate;


    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getLastMonthReserve() {
        return lastMonthReserve;
    }

    public void setLastMonthReserve(Double lastMonthReserve) {
        this.lastMonthReserve = lastMonthReserve;
    }

    public Double getThisMonthReserve() {
        return thisMonthReserve;
    }

    public void setThisMonthReserve(Double thisMonthReserve) {
        this.thisMonthReserve = thisMonthReserve;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }
}