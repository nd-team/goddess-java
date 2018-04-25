package com.bjike.goddess.materialbuy.Excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Column;
import java.time.LocalDate;

public class BankOfTheWaterExcel extends BaseTO {
    /**
     * 交易日
     */
    @ExcelHeader(name = "交易日", notNull = true)
    private LocalDate tradingDay;

    /**
     * 交易时间 ；LocalDate
     */
    @ExcelHeader(name = "交易时间", notNull = true)
    private LocalDate theBankAccount;
    /**
     * 起息日
     */
    @ExcelHeader(name = "起息日", notNull = true)
    private Double whereItIs;
    /**
     * 交易类型
     */
    @ExcelHeader(name = "交易类型", notNull = true)
    private String transactionType;

    /**
     * 借方金额
     */
    @ExcelHeader(name = "借方金额", notNull = true)
    private Double debitAmount;
    /**
     * 贷方金额
     */
    @ExcelHeader(name = "贷方金额", notNull = true)
    private Double theSum;

    /**
     * 余额
     */
    @ExcelHeader(name = "余额", notNull = true)
    private Double theBalanceOf;

    /**
     * 摘要
     */
    @ExcelHeader(name = "摘要", notNull = true)
    private String abstracts;

    /**
     * 银行账户信息
     */
    @ExcelHeader(name = "银行账户信息", notNull = true)
    private String accountInformation;
    /**
     * 银行属性
     */
    @ExcelHeader(name = "银行属性", notNull = true)
    private String bankProperty;

    public Double getWhereItIs() {
        return whereItIs;
    }

    public void setWhereItIs(Double whereItIs) {
        this.whereItIs = whereItIs;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(String accountInformation) {
        this.accountInformation = accountInformation;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public LocalDate getTradingDay() {
        return tradingDay;
    }

    public void setTradingDay(LocalDate tradingDay) {
        this.tradingDay = tradingDay;
    }

    public LocalDate getTheBankAccount() {
        return theBankAccount;
    }

    public void setTheBankAccount(LocalDate theBankAccount) {
        this.theBankAccount = theBankAccount;
    }

    public Double getTheSum() {
        return theSum;
    }

    public void setTheSum(Double theSum) {
        this.theSum = theSum;
    }

    public Double getTheBalanceOf() {
        return theBalanceOf;
    }

    public void setTheBalanceOf(Double theBalanceOf) {
        this.theBalanceOf = theBalanceOf;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }
}
