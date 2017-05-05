package com.bjike.goddess.budget.vo;

/**
 * @Author: [yewenbo]
 * @Date: [2017-05-03 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ArrivalWeekCountVO {
    /**
     * 地区
     */
    private String arrival;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 目标任务总量
     */
    private Integer targetWorkSum;
    /**
     * 实际完工总量
     */
    private Integer actualWorkSum;
    /**
     * 工作量差额总量
     */
    private Integer workDifferencesSum;
    /**
     * 目标收入总量
     */
    private Double targetIncomeSum;
    /**
     * 计划收入总量
     */
    private Double planIncome;
    /**
     * 收入差额总量
     */
    private Double incomeDifferencesSum;
    /**
     * 单价
     */
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTargetWorkSum() {
        return targetWorkSum;
    }

    public void setTargetWorkSum(Integer targetWorkSum) {
        this.targetWorkSum = targetWorkSum;
    }

    public Integer getActualWorkSum() {
        return actualWorkSum;
    }

    public void setActualWorkSum(Integer actualWorkSum) {
        this.actualWorkSum = actualWorkSum;
    }

    public Integer getWorkDifferencesSum() {
        return workDifferencesSum;
    }

    public void setWorkDifferencesSum(Integer workDifferencesSum) {
        this.workDifferencesSum = workDifferencesSum;
    }

    public Double getTargetIncomeSum() {
        return targetIncomeSum;
    }

    public void setTargetIncomeSum(Double targetIncomeSum) {
        this.targetIncomeSum = targetIncomeSum;
    }

    public Double getPlanIncome() {
        return planIncome;
    }

    public void setPlanIncome(Double planIncome) {
        this.planIncome = planIncome;
    }

    public Double getIncomeDifferencesSum() {
        return incomeDifferencesSum;
    }

    public void setIncomeDifferencesSum(Double incomeDifferencesSum) {
        this.incomeDifferencesSum = incomeDifferencesSum;
    }
}
