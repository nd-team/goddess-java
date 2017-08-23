package com.bjike.goddess.bankrecords.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 银行流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bankrecords_bankrecord")
public class BankRecord extends BaseEntity {

    /**
     * 流水明细
     */
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "bankRecord")
    private List<BankRecordDetail> detailList = new ArrayList<BankRecordDetail>();

    /**
     * 账号信息Id
     */
    @Column(name = "accountId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '账号信息Id'")
    private String accountId;

    /**
     * 借方金额
     */
    @Column(name = "debtorCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '借方金额'")
    private Double debtorCost;

    /**
     * 贷方金额
     */
    @Column(name = "creditorCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '贷方金额'")
    private Double creditorCost;

    /**
     * 余额
     */
    @Column(name = "balance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '余额'")
    private Double balance;

    /**
     * 流水日期
     */
    @Column(name = "recordDate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '流水日期'")
    private String recordDate;

    /**
     * 年份
     */
    @Column(name = "recordYear", nullable = false, columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;

    /**
     * 月份
     */
    @Column(name = "recordMonth", nullable = false, columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

    public List<BankRecordDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<BankRecordDetail> detailList) {
        this.detailList = detailList;
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
}