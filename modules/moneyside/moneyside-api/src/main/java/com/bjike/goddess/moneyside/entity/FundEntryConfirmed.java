package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.moneyside.enums.PassStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资金进入申请已确认
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:08 ]
 * @Description: [ 资金进入申请已确认 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_fundentryconfirmed")
public class FundEntryConfirmed extends BaseEntity {

    /**
     * 申请人
     */
    @Column(name = "applyPeople", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '申请人'")
    private String applyPeople;

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
     * 金额
     */
    @Column(name = "money", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;

    /**
     * 打款账户名
     */
    @Column(name = "moneyAccountName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '打款账户名'")
    private String moneyAccountName;

    /**
     * 打款账号
     */
    @Column(name = "moneyAccount", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '打款账号'")
    private String moneyAccount;

    /**
     * 确认人
     */
    @Column(name = "confirmPeople", columnDefinition = "VARCHAR(255)   COMMENT '确认人'")
    private String confirmPeople;

    /**
     * 确认情况
     */
    @Column(name = "confirmSituation", columnDefinition = "VARCHAR(255)   COMMENT '确认情况'")
    private String confirmSituation;

    /**
     * 是否通过
     */
    @Column(name = "pass", columnDefinition = "TINYINT(2) COMMENT '是否通过'")
    private PassStatus pass;


    public String getApplyPeople() {
        return applyPeople;
    }

    public void setApplyPeople(String applyPeople) {
        this.applyPeople = applyPeople;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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

    public String getConfirmPeople() {
        return confirmPeople;
    }

    public void setConfirmPeople(String confirmPeople) {
        this.confirmPeople = confirmPeople;
    }

    public String getConfirmSituation() {
        return confirmSituation;
    }

    public void setConfirmSituation(String confirmSituation) {
        this.confirmSituation = confirmSituation;
    }

    public PassStatus getPass() {
        return pass;
    }

    public void setPass(PassStatus pass) {
        this.pass = pass;
    }
}