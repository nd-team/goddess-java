package com.bjike.goddess.materialbuy.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * 银行流水
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-04 09:39 ]
 * @Description: [ 银行流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialbuy_bankofthewater")
public class BankOfTheWater extends BaseEntity {

    /**
     *
     */
    private List<BankOfTheWaterDetail> detailList = new ArrayList<BankOfTheWaterDetail>();
    /**
     * 交易日
     */
    @Column(name = "tradingDay", nullable = false, columnDefinition = "DATE   COMMENT '交易日'")
    private LocalDate tradingDay;

    /**
     * 交易时间 ；LocalDate
     */
    @Column(name = "theBankAccount", nullable = false, columnDefinition = "DATE   COMMENT '交易时间 ；LocalDate'")
    private LocalDate theBankAccount;
    /**
     * 起息日 ；LocalDate
     */
    @Column(name = "whereItIs", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '贷方金额'")
    private Double whereItIs;
    /**
     * 交易类型 ；LocalDate
     */
    @Column(name = "transactionType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '交易类型'")
    private String transactionType;
    /**
     * 余额
     */
    @Column(name = "theBalanceOf", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '余额'")
    private Double theBalanceOf;

    /**
     * 摘要
     */
    @Column(name = "abstracts", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '摘要'")
    private String abstracts;
    /**
     * 借方金额
     */
    @Column(name = "debitAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '借方金额'")
    private Double debitAmount;
    /**
     * 贷方金额
     */
    @Column(name = "theSum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '贷方金额'")
    private Double theSum;
    /**
     * 银行账户信息
     */
    @Column(name = "accountInformation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '银行账户；'")
    private String accountInformation;
    /**
     *银行属性
     */
    @Column(name = "bankProperty", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '银行属性；'")
    private String bankProperty;

    public String getBankProperty() {
        return bankProperty;
    }

    public void setBankProperty(String bankProperty) {
        this.bankProperty = bankProperty;
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

    public String getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(String accountInformation) {
        this.accountInformation = accountInformation;
    }
}