package com.bjike.goddess.bankrecords.vo;

import java.time.LocalDate;

/**
 * 银行流水分析表现层对象
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-09 09:42 ]
 * @Description: [ 银行流水分析表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankAnalysisVO {

    /**
     * id
     */
    private String id;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 日期
     */

    private LocalDate theDateOf;

    /**
     * 本月收入
     */

    private Double thisMonthIncome;

    /**
     * 上个月收入
     */

    private Double lastMonthIncome;

    /**
     * 收入差额
     */

    private Double incomeGap;

    /**
     * 收入比例
     */

    private Double incomeRatio;

    /**
     * 收入增长率
     */

    private Double revenueGrowthRate;

    /**
     * 本月支出
     */

    private Double thisMonthSpending;

    /**
     * 上个月支出
     */

    private Double expenditureLastMonth;

    /**
     * 支出比例
     */

    private Double spendingProportion;

    /**
     * 支出增长率
     */

    private Double expenditureGrowthRate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public LocalDate getTheDateOf() {
        return theDateOf;
    }

    public void setTheDateOf(LocalDate theDateOf) {
        this.theDateOf = theDateOf;
    }

    public Double getThisMonthIncome() {
        return thisMonthIncome;
    }

    public void setThisMonthIncome(Double thisMonthIncome) {
        this.thisMonthIncome = thisMonthIncome;
    }

    public Double getLastMonthIncome() {
        return lastMonthIncome;
    }

    public void setLastMonthIncome(Double lastMonthIncome) {
        this.lastMonthIncome = lastMonthIncome;
    }

    public Double getIncomeGap() {
        return incomeGap;
    }

    public void setIncomeGap(Double incomeGap) {
        this.incomeGap = incomeGap;
    }

    public Double getIncomeRatio() {
        return incomeRatio;
    }

    public void setIncomeRatio(Double incomeRatio) {
        this.incomeRatio = incomeRatio;
    }

    public Double getRevenueGrowthRate() {
        return revenueGrowthRate;
    }

    public void setRevenueGrowthRate(Double revenueGrowthRate) {
        this.revenueGrowthRate = revenueGrowthRate;
    }

    public Double getThisMonthSpending() {
        return thisMonthSpending;
    }

    public void setThisMonthSpending(Double thisMonthSpending) {
        this.thisMonthSpending = thisMonthSpending;
    }

    public Double getExpenditureLastMonth() {
        return expenditureLastMonth;
    }

    public void setExpenditureLastMonth(Double expenditureLastMonth) {
        this.expenditureLastMonth = expenditureLastMonth;
    }

    public Double getSpendingProportion() {
        return spendingProportion;
    }

    public void setSpendingProportion(Double spendingProportion) {
        this.spendingProportion = spendingProportion;
    }

    public Double getExpenditureGrowthRate() {
        return expenditureGrowthRate;
    }

    public void setExpenditureGrowthRate(Double expenditureGrowthRate) {
        this.expenditureGrowthRate = expenditureGrowthRate;
    }
}