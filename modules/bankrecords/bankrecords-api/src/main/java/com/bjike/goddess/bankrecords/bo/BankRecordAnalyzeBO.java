package com.bjike.goddess.bankrecords.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * 分析对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankRecordAnalyzeBO extends BaseBO {
    /**
     * 日期
     */
    private String theDateOf;
    /**
     * 银行
     */
    private String bank;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 本月借方金额
     */
    private Double currentDebtorCost;
    /**
     * 本月贷方金额
     */
    private Double currentCreditorCost;
    /**
     * 本月余额
     */
    private Double currentBalance;
    /**
     * 上月借方金额
     */
    private Double lastDebtorCost;
    /**
     * 上月贷方金额
     */
    private Double lastCreditorCost;
    /**
     * 上月余额
     */
    private Double lastBalance;
    /**
     * 收入差额
     */
    private Double creditorSubtract;
    /**
     * 支出差额
     */
    private Double debtorSubtract;
    /**
     * 收入比例
     */
    private Double creditorRate;
    /**
     * 支出比例
     */
    private Double debtorRate;
    /**
     * 收入增长率
     */
    private Double creditorGrow;
    /**
     * 支出增长率
     */
    private Double debtorGrow;

    public String getTheDateOf() {
        return theDateOf;
    }

    public void setTheDateOf(String theDateOf) {
        this.theDateOf = theDateOf;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public Double getCurrentDebtorCost() {
        return currentDebtorCost;
    }

    public void setCurrentDebtorCost(Double currentDebtorCost) {
        this.currentDebtorCost = currentDebtorCost;
    }

    public Double getCurrentCreditorCost() {
        return currentCreditorCost;
    }

    public void setCurrentCreditorCost(Double currentCreditorCost) {
        this.currentCreditorCost = currentCreditorCost;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Double getLastDebtorCost() {
        return lastDebtorCost;
    }

    public void setLastDebtorCost(Double lastDebtorCost) {
        this.lastDebtorCost = lastDebtorCost;
    }

    public Double getLastCreditorCost() {
        return lastCreditorCost;
    }

    public void setLastCreditorCost(Double lastCreditorCost) {
        this.lastCreditorCost = lastCreditorCost;
    }

    public Double getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(Double lastBalance) {
        this.lastBalance = lastBalance;
    }

    public Double getCreditorSubtract() {
        return creditorSubtract;
    }

    public void setCreditorSubtract(Double creditorSubtract) {
        this.creditorSubtract = creditorSubtract;
    }

    public Double getDebtorSubtract() {
        return debtorSubtract;
    }

    public void setDebtorSubtract(Double debtorSubtract) {
        this.debtorSubtract = debtorSubtract;
    }

    public Double getCreditorRate() {
        return creditorRate;
    }

    public void setCreditorRate(Double creditorRate) {
        this.creditorRate = creditorRate;
    }

    public Double getDebtorRate() {
        return debtorRate;
    }

    public void setDebtorRate(Double debtorRate) {
        this.debtorRate = debtorRate;
    }

    public Double getCreditorGrow() {
        return creditorGrow;
    }

    public void setCreditorGrow(Double creditorGrow) {
        this.creditorGrow = creditorGrow;
    }

    public Double getDebtorGrow() {
        return debtorGrow;
    }

    public void setDebtorGrow(Double debtorGrow) {
        this.debtorGrow = debtorGrow;
    }

}