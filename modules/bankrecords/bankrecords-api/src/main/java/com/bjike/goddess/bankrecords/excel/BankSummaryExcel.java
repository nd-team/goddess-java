package com.bjike.goddess.bankrecords.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class BankSummaryExcel extends BaseTO {
    /**
     * 银行
     */
    @ExcelHeader(name = "银行", notNull = false)
    private String bankName;
    /**
     * 余额
     */
    @ExcelHeader(name = "余额", notNull = false)
    private Double theBalanceOf;
    /**
     * 收入贷方金额
     */
    @ExcelHeader(name = "(收入)借方金额", notNull = false)
    private Double incomeCreditAmount;

    /**
     * 支出借方金额
     */
    @ExcelHeader(name = "(支出)借方金额", notNull = false)
    private Double expenseDebitAmount;
    /**
     * 日期；Long
     */
    @ExcelHeader(name = "日期", notNull = false)
    private String theDateOf;
    /**
     * 本月发生额
     */
    @ExcelHeader(name = "本月发生额", notNull = false)
    private Double amountOfThisMonth;

    public Double getAmountOfThisMonth() {
        return amountOfThisMonth;
    }

    public void setAmountOfThisMonth(Double amountOfThisMonth) {
        this.amountOfThisMonth = amountOfThisMonth;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getTheBalanceOf() {
        return theBalanceOf;
    }

    public void setTheBalanceOf(Double theBalanceOf) {
        this.theBalanceOf = theBalanceOf;
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
    public String getTheDateOf() {
        return theDateOf;
    }

    public void setTheDateOf(String theDateOf) {
        this.theDateOf = theDateOf;
    }
}
