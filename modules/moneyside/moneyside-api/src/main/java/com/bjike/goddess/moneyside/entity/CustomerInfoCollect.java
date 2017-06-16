package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 客户信息汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:11 ]
 * @Description: [ 客户信息汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_customerinfocollect")
public class CustomerInfoCollect extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 资金进入方式
     */
    @Column(name = "accessToFund", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '资金进入方式'")
    private String accessToFund;

    /**
     * 资金进入时间
     */
    @Column(name = "fundEntryAime", nullable = false, columnDefinition = "DATE   COMMENT '资金进入时间'")
    private LocalDate fundEntryAime;

    /**
     * 进入金额
     */
    @Column(name = "enterMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '进入金额'")
    private Double enterMoney;

    /**
     * 投资项目
     */
    @Column(name = "investProject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '投资项目'")
    private String investProject;

    /**
     * 投资总额
     */
    @Column(name = "investTotal", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '投资总额'")
    private Double investTotal;

    /**
     * 投资占比(%)
     */
    @Column(name = "investProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '投资占比(%)'")
    private Double investProportion;

    /**
     * 资金退出时间
     */
    @Column(name = "exitTime", nullable = false, columnDefinition = "DATE   COMMENT '资金退出时间'")
    private LocalDate exitTime;

    /**
     * 退出金额
     */
    @Column(name = "exitMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '退出金额'")
    private Double exitMoney;

    /**
     * 退出利息
     */
    @Column(name = "exitInterest", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '退出利息'")
    private Double exitInterest;

    /**
     * 被转让人
     */
    @Column(name = "transferee", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '被转让人'")
    private String transferee;

    /**
     * 转让金额
     */
    @Column(name = "transferAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '转让金额'")
    private Double transferAmount;

    /**
     * 收益分配时间
     */
    @Column(name = "incomeAllotTime", nullable = false, columnDefinition = "DATE   COMMENT '收益分配时间'")
    private LocalDate incomeAllotTime;

    /**
     * 分配利率
     */
    @Column(name = "allotInterestRate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '分配利率'")
    private Double allotInterestRate;

    /**
     * 分配额
     */
    @Column(name = "quota", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '分配额'")
    private Double quota;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToFund() {
        return accessToFund;
    }

    public void setAccessToFund(String accessToFund) {
        this.accessToFund = accessToFund;
    }

    public LocalDate getFundEntryAime() {
        return fundEntryAime;
    }

    public void setFundEntryAime(LocalDate fundEntryAime) {
        this.fundEntryAime = fundEntryAime;
    }

    public Double getEnterMoney() {
        return enterMoney;
    }

    public void setEnterMoney(Double enterMoney) {
        this.enterMoney = enterMoney;
    }

    public String getInvestProject() {
        return investProject;
    }

    public void setInvestProject(String investProject) {
        this.investProject = investProject;
    }

    public Double getInvestTotal() {
        return investTotal;
    }

    public void setInvestTotal(Double investTotal) {
        this.investTotal = investTotal;
    }

    public Double getInvestProportion() {
        return investProportion;
    }

    public void setInvestProportion(Double investProportion) {
        this.investProportion = investProportion;
    }

    public LocalDate getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDate exitTime) {
        this.exitTime = exitTime;
    }

    public Double getExitMoney() {
        return exitMoney;
    }

    public void setExitMoney(Double exitMoney) {
        this.exitMoney = exitMoney;
    }

    public Double getExitInterest() {
        return exitInterest;
    }

    public void setExitInterest(Double exitInterest) {
        this.exitInterest = exitInterest;
    }

    public String getTransferee() {
        return transferee;
    }

    public void setTransferee(String transferee) {
        this.transferee = transferee;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public LocalDate getIncomeAllotTime() {
        return incomeAllotTime;
    }

    public void setIncomeAllotTime(LocalDate incomeAllotTime) {
        this.incomeAllotTime = incomeAllotTime;
    }

    public Double getAllotInterestRate() {
        return allotInterestRate;
    }

    public void setAllotInterestRate(Double allotInterestRate) {
        this.allotInterestRate = allotInterestRate;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }
}