package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.marketdevelopment.enums.MoneyType;
import com.bjike.goddess.marketdevelopment.enums.MonthType;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 年计划
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:32 ]
 * @Description: [ 年计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_planyeartype")
public class PlanYearType extends BaseEntity {

    /**
     * 年份id
     */
    @Column(name = "yearId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '年份id'")
    private String yearId;

    /**
     * 类型
     */
    @Column(name = "moneyType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private MoneyType moneyType;

    /**
     * 月份
     */
    @Column(name = "monthType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '月份'")
    private MonthType monthType;

    /**
     * 金额
     */
    @Column(name = "money", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double money;

    /**
     * 合计
     */
    @Column(name = "total", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合计'")
    private String total;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0  COMMENT '状态'", insertable = false)
    private Status status;


    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    public MonthType getMonthType() {
        return monthType;
    }

    public void setMonthType(MonthType monthType) {
        this.monthType = monthType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}