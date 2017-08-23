package com.bjike.goddess.fundcheck.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 账务收入
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:52 ]
 * @Description: [ 账务收入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "fundcheck_accountincome")
public class AccountIncome extends BaseEntity {

    /**
     * 日期
     */
    @Column(name = "date", nullable = false, columnDefinition = "DATE   COMMENT '日期'")
    private LocalDate date;

    /**
     * 回款
     */
    @Column(name = "back", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '回款'")
    private Double back;

    /**
     * 股东借款
     */
    @Column(name = "stockMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '股东借款'")
    private Double stockMoney;

    /**
     * 其他收入
     */
    @Column(name = "otherIncome", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '其他收入'")
    private Double otherIncome;

    /**
     * 账务收入合计
     */
    @Column(name = "money", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '账务收入合计'")
    private Double money;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getBack() {
        return back;
    }

    public void setBack(Double back) {
        this.back = back;
    }

    public Double getStockMoney() {
        return stockMoney;
    }

    public void setStockMoney(Double stockMoney) {
        this.stockMoney = stockMoney;
    }

    public Double getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(Double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}