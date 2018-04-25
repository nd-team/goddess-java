package com.bjike.goddess.materialbuy.vo;

import java.time.LocalDate;

/**
 * 银行流水表现层对象
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-04 09:39 ]
 * @Description: [ 银行流水表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankOfTheWaterVO {

    /**
     * 交易日
     */

    private LocalDate tradingDay;

    /**
     * 交易时间 ；LocalDate
     */

    private LocalDate theBankAccount;
    /**
     * 起息日 ；LocalDate
     */

    private Double whereItIs;
    /**
     * 交易类型 ；LocalDate
     */
    private String transactionType;
    /**
     * 余额
     */

    private Double theBalanceOf;

    /**
     * 摘要
     */

    private String abstracts;
    /**
     * 借方金额
     */

    private Double debitAmount;
    /**
     * 贷方金额
     */

    private Double theSum;
    /**
     * 银行账户信息
     */
    private String accountInformation;
    /**
     * 银行属性
     */
    private String bankProperty;

    public String getBankProperty() {
        return bankProperty;
    }

    public void setBankProperty(String bankProperty) {
        this.bankProperty = bankProperty;
    }

    public String getAccountInformation() {
        return accountInformation;
    }
    public void setAccountInformation(String accountInformation) {
        this.accountInformation = accountInformation;
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

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Double getTheSum() {
        return theSum;
    }

    public void setTheSum(Double theSum) {
        this.theSum = theSum;
    }
}