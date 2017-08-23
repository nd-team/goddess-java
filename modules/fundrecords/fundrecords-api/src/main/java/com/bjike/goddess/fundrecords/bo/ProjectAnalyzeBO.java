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
public class ProjectAnalyzeBO extends BaseBO {

    /**
     * 时间
     */
    private String recordDate;

    /**
     * 项目
     */
    private String project;


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

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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