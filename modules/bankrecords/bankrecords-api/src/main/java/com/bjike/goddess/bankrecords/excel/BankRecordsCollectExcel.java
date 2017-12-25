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
public class BankRecordsCollectExcel extends BaseTO {

    /**
     * 银行
     */
    @ExcelHeader(name = "银行", notNull = false)
    private String bank;

    /**
     * 年份
     */
    @ExcelHeader(name = "年份", notNull = false)
    private Integer year;

    /**
     * 月份
     */
    @ExcelHeader(name = "月份", notNull = false)
    private Integer month;

    /**
     * (收入)贷方金额
     */
    @ExcelHeader(name = "(收入)贷方金额", notNull = false)
    private Double creditorCost;

    /**
     * (支出)借方金额
     */
    @ExcelHeader(name = "(支出)借方金额", notNull = false)
    private Double debtorCost;

    /**
     * 余额
     */
    @ExcelHeader(name = "余额", notNull = false)
    private Double balance;


    public Double getDebtorCost() {
        return debtorCost;
    }

    public void setDebtorCost(Double debtorCost) {
        this.debtorCost = debtorCost;
    }

    public Double getCreditorCost() {
        return creditorCost;
    }

    public void setCreditorCost(Double creditorCost) {
        this.creditorCost = creditorCost;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}