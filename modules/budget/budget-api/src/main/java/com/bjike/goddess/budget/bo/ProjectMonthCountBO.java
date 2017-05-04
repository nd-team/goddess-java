package com.bjike.goddess.budget.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [yewenbo]
 * @Date: [2017-05-03 14:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectMonthCountBO extends BaseBO {
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
     * 目标收入总量
     */
    private Double targetIncomeSum;
    /**
     * 计划收入总量
     */
    private Double planIncomeSum;
    /**
     * 收入差额总量
     */
    private Double incomeDifferencesSum;
    /**
     * 目标任务量总量
     */
    private Integer targetWorkSum;
    /**
     * 实际完工量总量
     */
    private Integer actualWorkSum;
    /**
     * 工作量差额总量
     */
    private Integer workDifferencesSum;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
}
