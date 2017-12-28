package com.bjike.goddess.bankrecords.vo;

/**
 * 银行流水业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankRecordCollectVO {

    /**
     * id
     */
    private String id;

    /**
     * 借方金额
     */
    private Double debtorCost;

    /**
     * 贷方金额
     */
    private Double creditorCost;

    /**
     * 余额
     */
    private Double balance;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 银行
     */
    private String bank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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