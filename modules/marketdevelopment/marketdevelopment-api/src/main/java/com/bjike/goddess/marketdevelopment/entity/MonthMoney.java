package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务方向类型数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 02:11 ]
 * @Description: [ 业务方向类型数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_monthmoney")
public class MonthMoney extends BaseEntity {

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '年份'")
    private String year;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '月份'")
    private String month;

    /**
     * 目标金额（万元）
     */
    @Column(name = "targetMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标金额（万元）'")
    private Double targetMoney;

    /**
     * 计划金额（万元）
     */
    @Column(name = "planMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划金额（万元）'")
    private Double planMoney;

    /**
     * 实际金额（万元）
     */
    @Column(name = "actualMoney", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '实际金额（万元）'")
    private Double actualMoney;

    /**
     * 差异金额（万元）
     */
    @Column(name = "diferenceMoney", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '差异金额（万元）'")
    private Double diferenceMoney;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Double getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(Double planMoney) {
        this.planMoney = planMoney;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Double getDiferenceMoney() {
        return diferenceMoney;
    }

    public void setDiferenceMoney(Double diferenceMoney) {
        this.diferenceMoney = diferenceMoney;
    }
}