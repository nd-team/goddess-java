package com.bjike.goddess.fundcheck.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 账上余额
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:08 ]
 * @Description: [ 账上余额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "fundcheck_accountbalance")
public class AccountBalance extends BaseEntity {

    /**
     * 日期
     */
    @Column(name = "date", nullable = false, columnDefinition = "DATE   COMMENT '日期'")
    private LocalDate date;

    /**
     * 期初余额
     */
    @Column(name = "beginningBalance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '期初余额'")
    private Double beginningBalance;
    /**
     * 账务收入
     */
    @Column(name = "accountIncome", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '账务收入'")
    private Double accountIncome;
    /**
     * 账务支出
     */
    @Column(name = "accountSpend", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '账务支出'")
    private Double accountSpend;

    /**
     * 账上余额
     */
    @Column(name = "accountBalance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '账上余额'")
    private Double accountBalance;

    /**
     * 资金差异(期初余额+账上支出-财务支出)
     */
    @Column(name = "fundsDifference", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金差异(期初余额+账上支出-财务支出)'")
    private Double fundsDifference;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(Double beginningBalance) {
        this.beginningBalance = beginningBalance;
    }

    public Double getAccountIncome() {
        return accountIncome;
    }

    public void setAccountIncome(Double accountIncome) {
        this.accountIncome = accountIncome;
    }

    public Double getAccountSpend() {
        return accountSpend;
    }

    public void setAccountSpend(Double accountSpend) {
        this.accountSpend = accountSpend;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Double getFundsDifference() {
        return fundsDifference;
    }

    public void setFundsDifference(Double fundsDifference) {
        this.fundsDifference = fundsDifference;
    }
}