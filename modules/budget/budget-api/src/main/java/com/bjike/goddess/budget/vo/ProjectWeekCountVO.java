package com.bjike.goddess.budget.vo;

/**
 * @Author: [yewenbo]
 * @Date: [2017-05-03 09:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectWeekCountVO {
    /**
     * 地区
     */
    private String arrival;
    /**
     * 项目
     */
    private String project;
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
     * 工作量差异总量
     */
    private Integer workDifferencesSum;
    /**
     * 目标收入总量
     */
    private Double targetIncomeSum;
    /**
     * 计划收入总量
     */
    private Double planIncomeSum;
    /**
     * 收入差异总量
     */
    private Double incomeDifferencesSum;

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

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public Double getPlanIncomeSum() {
        return planIncomeSum;
    }

    public void setPlanIncomeSum(Double planIncomeSum) {
        this.planIncomeSum = planIncomeSum;
    }

    public Double getIncomeDifferencesSum() {
        return incomeDifferencesSum;
    }

    public void setIncomeDifferencesSum(Double incomeDifferencesSum) {
        this.incomeDifferencesSum = incomeDifferencesSum;
    }
}
