package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.financeinit.enums.BalanceDirection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 初始化数据录入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:21 ]
 * @Description: [ 初始化数据录入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_initdateentry")
public class InitDateEntry extends BaseEntity {

    /**
     * 代码
     */
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '代码'")
    private String code;

    /**
     * 会计科目名称
     */
    @Column(name = "accountanName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会计科目名称'")
    private String accountanName;

    /**
     * 本年借方累计数
     */
    @Column(name = "yearBorrowerNum", nullable = false,insertable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0  COMMENT '本年借方累计数'")
    private Double yearBorrowerNum;

    /**
     * 本年贷方累计数
     */
    @Column(name = "yearLenderNum", nullable = false,insertable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0  COMMENT '本年贷方累计数'")
    private Double yearLenderNum;

    /**
     * 方向
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '方向'")
    private BalanceDirection balanceDirection;

    /**
     * 期初余额
     */
    @Column(name = "begingBalance", nullable = false,insertable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0  COMMENT '期初余额'")
    private Double begingBalance;

    /**
     * 本年损益类累计数
     */
    @Column(name = "yearProfitLossNum", nullable = false,insertable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0  COMMENT '本年损益类累计数'")
    private Double yearProfitLossNum;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountanName() {
        return accountanName;
    }

    public void setAccountanName(String accountanName) {
        this.accountanName = accountanName;
    }

    public Double getYearBorrowerNum() {
        return yearBorrowerNum;
    }

    public void setYearBorrowerNum(Double yearBorrowerNum) {
        this.yearBorrowerNum = yearBorrowerNum;
    }

    public Double getYearLenderNum() {
        return yearLenderNum;
    }

    public void setYearLenderNum(Double yearLenderNum) {
        this.yearLenderNum = yearLenderNum;
    }

    public BalanceDirection getBalanceDirection() {
        return balanceDirection;
    }

    public void setBalanceDirection(BalanceDirection balanceDirection) {
        this.balanceDirection = balanceDirection;
    }

    public Double getBegingBalance() {
        return begingBalance;
    }

    public void setBegingBalance(Double begingBalance) {
        this.begingBalance = begingBalance;
    }

    public Double getYearProfitLossNum() {
        return yearProfitLossNum;
    }

    public void setYearProfitLossNum(Double yearProfitLossNum) {
        this.yearProfitLossNum = yearProfitLossNum;
    }
}