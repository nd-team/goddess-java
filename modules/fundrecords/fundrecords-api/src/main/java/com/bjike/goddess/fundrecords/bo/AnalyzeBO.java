package com.bjike.goddess.fundrecords.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 条件汇总
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnalyzeBO extends BaseBO {

    /**
     * 时间
     */
    private String recordDate;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目
     */
    private String project;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 本月收入
     */
    private Double income;

    /**
     * 本月支出
     */
    private Double expenditure;

    /**
     * 上月收入
     */
    private Double lastIncome;

    /**
     * 上月支出
     */
    private Double lastExpenditure;

    /**
     * 收入差额
     */
    private Double incomeSubtract;

    /**
     * 支出差额
     */
    private Double expenditureSubtract;

    /**
     * 收入比例
     */
    private Double incomeRate;

    /**
     * 支出比例
     */
    private Double expenditureRate;

    /**
     * 收入增长率
     */
    private String incomeGrowRate;

    /**
     * 支出增长率
     */
    private String expenditureGrowRate;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }

    public Double getLastIncome() {
        return lastIncome;
    }

    public void setLastIncome(Double lastIncome) {
        this.lastIncome = lastIncome;
    }

    public Double getLastExpenditure() {
        return lastExpenditure;
    }

    public void setLastExpenditure(Double lastExpenditure) {
        this.lastExpenditure = lastExpenditure;
    }

    public Double getIncomeSubtract() {
        return incomeSubtract;
    }

    public void setIncomeSubtract(Double incomeSubtract) {
        this.incomeSubtract = incomeSubtract;
    }

    public Double getExpenditureSubtract() {
        return expenditureSubtract;
    }

    public void setExpenditureSubtract(Double expenditureSubtract) {
        this.expenditureSubtract = expenditureSubtract;
    }

    public Double getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(Double incomeRate) {
        this.incomeRate = incomeRate;
    }

    public Double getExpenditureRate() {
        return expenditureRate;
    }

    public void setExpenditureRate(Double expenditureRate) {
        this.expenditureRate = expenditureRate;
    }

    public String getIncomeGrowRate() {
        return incomeGrowRate;
    }

    public void setIncomeGrowRate(String incomeGrowRate) {
        this.incomeGrowRate = incomeGrowRate;
    }

    public String getExpenditureGrowRate() {
        return expenditureGrowRate;
    }

    public void setExpenditureGrowRate(String expenditureGrowRate) {
        this.expenditureGrowRate = expenditureGrowRate;
    }
}