package com.bjike.goddess.bankrecords.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;

/**
 * 银行流水
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-08 10:27 ]
 * @Description: [ 银行流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankSummaryTO extends BaseTO {
    /**
     * 银行名称
     */

    private String bankName;

    /**
     * 日期；Long
     */

    private LocalDate theDateOf;

    /**
     * 本月发生额
     */


    private Double amountOfThisMonth;

    /**
     * 收入贷方金额
     */
    private Double incomeCreditAmount;

    /**
     * 支出借方金额
     */

    private Double expenseDebitAmount;
    /**
     * 余额
     */
    private Double theBalanceOf;

    private LocalDate theStartTime;

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

    public Double getAmountOfThisMonth() {
        return amountOfThisMonth;
    }

    public void setAmountOfThisMonth(Double amountOfThisMonth) {
        this.amountOfThisMonth = amountOfThisMonth;
    }

    public Double getIncomeCreditAmount() {
        return incomeCreditAmount;
    }

    public void setIncomeCreditAmount(Double incomeCreditAmount) {
        this.incomeCreditAmount = incomeCreditAmount;
    }

    public Double getExpenseDebitAmount() {
        return expenseDebitAmount;
    }

    public void setExpenseDebitAmount(Double expenseDebitAmount) {
        this.expenseDebitAmount = expenseDebitAmount;
    }

    public Double getTheBalanceOf() {
        return theBalanceOf;
    }

    public void setTheBalanceOf(Double theBalanceOf) {
        this.theBalanceOf = theBalanceOf;
    }

    public LocalDate getTheStartTime() {
        return theStartTime;
    }

    public void setTheStartTime(LocalDate theStartTime) {
        this.theStartTime = theStartTime;
    }
}