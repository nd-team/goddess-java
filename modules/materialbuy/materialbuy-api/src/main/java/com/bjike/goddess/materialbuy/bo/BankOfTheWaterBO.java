package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * 银行流水业务传输对象
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-04 09:39 ]
 * @Description: [ 银行流水业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankOfTheWaterBO extends BaseBO {

    /**
     * 交易日
     */
    private LocalDate tradingDay;

    /**
     * 交易时间 ；LocalDate
     */
    private LocalDate theBankAccount;

    /**
     * 贷方金额
     */
    private Double theSum;

    /**
     * 余额
     */
    private Double theBalanceOf;

    /**
     * 摘要
     */
    private String abstracts;


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