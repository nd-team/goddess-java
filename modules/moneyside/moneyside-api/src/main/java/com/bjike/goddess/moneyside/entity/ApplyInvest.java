package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 申请投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:23 ]
 * @Description: [ 申请投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_applyinvest")
public class ApplyInvest extends BaseEntity {

    /**
     * 投资人
     */
    @Column(name = "investor", columnDefinition = "VARCHAR(255)   COMMENT '投资人'")
    private String investor;

    /**
     * 投资对象
     */
    @Column(name = "investObject", columnDefinition = "VARCHAR(255)   COMMENT '投资对象'")
    private String investObject;

    /**
     * 投资形式
     */
    @Column(name = "investForm", columnDefinition = "VARCHAR(255)   COMMENT '投资形式'")
    private String investForm;

    /**
     * 投资总额
     */
    @Column(name = "investTotal", columnDefinition = "DECIMAL(10,2)   COMMENT '投资总额'")
    private Double investTotal;

    /**
     * 投资占比（投资总额/筹资总额）
     */
    @Column(name = "investProportion", columnDefinition = "DECIMAL(10,2)   COMMENT '投资占比（投资总额/筹资总额）'")
    private Double investProportion;

    /**
     * 投资日期
     */
    @Column(name = "investDate", columnDefinition = "DATE   COMMENT '投资日期'")
    private LocalDate investDate;

    /**
     * 本次投资额
     */
    @Column(name = "thisInvestMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '本次投资额'")
    private Double thisInvestMoney;

    /**
     * 累计投资额
     */
    @Column(name = "accumulativeInvestMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '累计投资额'")
    private Double accumulativeInvestMoney;

    /**
     * 累计投资比（累计投资额/投资总额）
     */
    @Column(name = "accumulativeInvestProportion", columnDefinition = "DECIMAL(10,2)   COMMENT '累计投资比（累计投资额/投资总额）'")
    private Double accumulativeInvestProportion;

    /**
     * 提取风险控制保证金（本次投资额/提取风险准备金率）
     */
    @Column(name = "extractRiskControlMargin", columnDefinition = "DECIMAL(10,2)   COMMENT '提取风险控制保证金（本次投资额/提取风险准备金率）'")
    private Double extractRiskControlMargin;

    /**
     * 打款账户
     */
    @Column(name = "moneyAccountName", columnDefinition = "VARCHAR(255)   COMMENT '打款账户'")
    private String moneyAccountName;

    /**
     * 打款账号
     */
    @Column(name = "moneyAccount", columnDefinition = "VARCHAR(255)   COMMENT '打款账号'")
    private String moneyAccount;

    /**
     * 备注
     */
    @Column(name = "remark",columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getInvestObject() {
        return investObject;
    }

    public void setInvestObject(String investObject) {
        this.investObject = investObject;
    }

    public String getInvestForm() {
        return investForm;
    }

    public void setInvestForm(String investForm) {
        this.investForm = investForm;
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

    public LocalDate getInvestDate() {
        return investDate;
    }

    public void setInvestDate(LocalDate investDate) {
        this.investDate = investDate;
    }

    public Double getThisInvestMoney() {
        return thisInvestMoney;
    }

    public void setThisInvestMoney(Double thisInvestMoney) {
        this.thisInvestMoney = thisInvestMoney;
    }


    public Double getAccumulativeInvestMoney() {
        return accumulativeInvestMoney;
    }

    public void setAccumulativeInvestMoney(Double accumulativeInvestMoney) {
        this.accumulativeInvestMoney = accumulativeInvestMoney;
    }

    public Double getAccumulativeInvestProportion() {
        return accumulativeInvestProportion;
    }

    public void setAccumulativeInvestProportion(Double accumulativeInvestProportion) {
        this.accumulativeInvestProportion = accumulativeInvestProportion;
    }

    public Double getExtractRiskControlMargin() {
        return extractRiskControlMargin;
    }

    public void setExtractRiskControlMargin(Double extractRiskControlMargin) {
        this.extractRiskControlMargin = extractRiskControlMargin;
    }

    public String getMoneyAccountName() {
        return moneyAccountName;
    }

    public void setMoneyAccountName(String moneyAccountName) {
        this.moneyAccountName = moneyAccountName;
    }

    public String getMoneyAccount() {
        return moneyAccount;
    }

    public void setMoneyAccount(String moneyAccount) {
        this.moneyAccount = moneyAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}