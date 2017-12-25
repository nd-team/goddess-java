package com.bjike.goddess.bankrecords.vo;

import com.bjike.goddess.bankrecords.beanlist.Detail;

import java.util.List;

/**
 * 银行流水表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankRecordInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 账号信息Id
     */
    private String accountId;

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
     * 流水日期
     */
    private String recordDate;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 账号名称
     */
    private String name;

    /**
     * 账号
     */
    private String number;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 公司
     */
    private String company;

    /**
     * 银行
     */
    private String bank;

    /**
     * 银行地址
     */
    private String bankAddress;

    /**
     * 类型
     */
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}