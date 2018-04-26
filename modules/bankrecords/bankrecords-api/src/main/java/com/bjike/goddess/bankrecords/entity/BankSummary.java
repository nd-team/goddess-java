package com.bjike.goddess.bankrecords.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 银行流水
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-08 10:27 ]
 * @Description: [ 银行流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bankrecords_banksummary")
public class BankSummary extends BaseEntity {

    /**
     * 银行名称
     */
    @Column(name = "bankName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '银行名称'")
    private String bankName;

    /**
     * 日期；Long
     */
    @Column(name = "theDateOf", nullable = false, columnDefinition = "DATE   COMMENT '日期；Long'")
    private LocalDateTime theDateOf;

    /**
     * 本月发生额
     */

    @Column(name = "amountOfThisMonth", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '本月发生额'")
    private Double amountOfThisMonth;

    /**
     * 收入贷方金额
     */
    @Column(name = "incomeCreditAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '收入贷方金额'")
    private Double incomeCreditAmount;

    /**
     * 支出借方金额
     */
    @Column(name = "expenseDebitAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '支出借方金额'")
    private Double expenseDebitAmount;

    /**
     * 余额
     */
    @Column(name = "theBalanceOf", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '余额'")
    private Double theBalanceOf;

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;
    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public LocalDateTime getTheDateOf() {
        return theDateOf;
    }

    public void setTheDateOf(LocalDateTime theDateOf) {
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
}