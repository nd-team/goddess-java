package com.bjike.goddess.materialbuy.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

/**
 * 银行流水数据传输对象
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-04 09:39 ]
 * @Description: [ 银行流水数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankOfTheWaterDTO extends BaseDTO {
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
     * 账号id
     */
    private String accountId;


    @NotBlank(message = "开始时间不能空")
    private LocalDate theStartTime;//开始时间

    @NotBlank(message = "结束时间不能空")
    private LocalDate theEndOfrThe;//结束时间

    private LocalDate accountingDate;//账套会计启动时间

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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