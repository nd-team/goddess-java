package com.bjike.goddess.bankrecords.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 项目承包洽谈
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.913 ]
 * @Description: [ 项目承包洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankRecordsAnalyzeExcel extends BaseTO {

    /**
     * 银行
     */
    @ExcelHeader(name = "银行", notNull = false)
    private String bank;

    /**
     * 日期
     */
    @ExcelHeader(name = "日期", notNull = false)
    private String theDateOf;

    /**
     * 本月借方金额
     */
    @ExcelHeader(name = "本月借方金额", notNull = false)
    private Double currentDebtorCost;

    /**
     * 本月贷方金额
     */
    @ExcelHeader(name = "本月贷方金额", notNull = false)
    private Double currentCreditorCost;
    /**
     * 上月借方金额
     */
    @ExcelHeader(name = "上月借方金额", notNull = false)
    private Double lastDebtorCost;
    /**
     * 上月贷方金额
     */
    @ExcelHeader(name = "上月贷方金额", notNull = false)
    private Double lastCreditorCost;
    /**
     * 收入差额
     */
    @ExcelHeader(name = "收入差额", notNull = false)
    private Double creditorSubtract;
    /**
     * 支出差额
     */
    @ExcelHeader(name = "支出差额", notNull = false)
    private Double debtorSubtract;
    /**
     * 收入比例
     */
    @ExcelHeader(name = "收入比例", notNull = false)
    private Double creditorRate;
    /**
     * 支出比例
     */
    @ExcelHeader(name = "支出比例", notNull = false)
    private Double debtorRate;
    /**
     * 收入增长率
     */
    @ExcelHeader(name = "收入增长率", notNull = false)
    private Double creditorGrow;
    /**
     * 支出增长率
     */
    @ExcelHeader(name = "支出增长率", notNull = false)
    private Double debtorGrow;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTheDateOf() {
        return theDateOf;
    }

    public void setTheDateOf(String theDateOf) {
        this.theDateOf = theDateOf;
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