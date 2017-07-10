package com.bjike.goddess.fundcheck.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 支付给股东
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:55 ]
 * @Description: [ 支付给股东 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "fundcheck_paystock")
public class PayStock extends BaseEntity {

    /**
     * 日期
     */
    @Column(name = "date", nullable = false, columnDefinition = "DATE   COMMENT '日期'")
    private LocalDate date;
    /**
     * 一级
     */
    @Column(name = "firstName", columnDefinition = "VARCHAR(255)   COMMENT '一级'")
    private String firstName;
    /**
     * 二级
     */
    @Column(name = "secondName", columnDefinition = "VARCHAR(255)   COMMENT '二级'")
    private String secondName;

    /**
     * 股东名
     */
    @Column(name = "stockName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股东名'")
    private String stockName;

    /**
     * 金额
     */
    @Column(name = "money", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}