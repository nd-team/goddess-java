package com.bjike.goddess.bankrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 银行流水数据传输对象
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-08 10:21 ]
 * @Description: [ 银行流水数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankOfTheWaterDTO extends BaseDTO {
    /**
     * 交易日
     */

    private LocalDateTime tradingDay;

    /**
     * 交易时间 ；
     */

    private LocalDateTime theBankAccount;
    /**
     * 起息日 ；
     */

    private Double whereItIs;
    /**
     * 交易类型 ；
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



    @NotBlank(message = "开始时间不能空")
    private LocalDate theStartTime;//开始时间

    @NotBlank(message = "结束时间不能空")
    private LocalDate theEndOfrThe;//结束时间

    private LocalDate accountingDate;//账套会计启动时间

    public LocalDateTime getTradingDay() {
        return tradingDay;
    }

    public void setTradingDay(LocalDateTime tradingDay) {
        this.tradingDay = tradingDay;
    }

    public LocalDateTime getTheBankAccount() {
        return theBankAccount;
    }

    public void setTheBankAccount(LocalDateTime theBankAccount) {
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

    public String getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(String accountInformation) {
        this.accountInformation = accountInformation;
    }

    public String getBankProperty() {
        return bankProperty;
    }

    public void setBankProperty(String bankProperty) {
        this.bankProperty = bankProperty;
    }

    public LocalDate getTheStartTime() {
        return theStartTime;
    }

    public void setTheStartTime(LocalDate theStartTime) {
        this.theStartTime = theStartTime;
    }

    public LocalDate getTheEndOfrThe() {
        return theEndOfrThe;
    }

    public void setTheEndOfrThe(LocalDate theEndOfrThe) {
        this.theEndOfrThe = theEndOfrThe;
    }

    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }
}