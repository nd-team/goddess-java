package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 年计划
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:13 ]
 * @Description: [ 年计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_planyear")
public class PlanYear extends BaseEntity {

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '年份'")
    private String year;

    /**
     * 全年目标金额(万元)
     */
    @Column(name = "yearTargetMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '全年目标金额(万元)'")
    private Double yearTargetMoney;

    /**
     * 全年计划金额(万元)
     */
    @Column(name = "yearPlanMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '全年计划金额(万元)'")
    private Double yearPlanMoney;

    /**
     * 全年实际金额（万元）
     */
    @Column(name = "yearActualMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '全年实际金额（万元）'")
    private Double yearActualMoney;

    /**
     * 全年差异金额（万元）
     */
    @Column(name = "yearDiferenceMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '全年差异金额（万元）'")
    private Double yearDiferenceMoney;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0  COMMENT '状态'", insertable = false)
    private Status status;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getYearTargetMoney() {
        return yearTargetMoney;
    }

    public void setYearTargetMoney(Double yearTargetMoney) {
        this.yearTargetMoney = yearTargetMoney;
    }

    public Double getYearPlanMoney() {
        return yearPlanMoney;
    }

    public void setYearPlanMoney(Double yearPlanMoney) {
        this.yearPlanMoney = yearPlanMoney;
    }

    public Double getYearActualMoney() {
        return yearActualMoney;
    }

    public void setYearActualMoney(Double yearActualMoney) {
        this.yearActualMoney = yearActualMoney;
    }

    public Double getYearDiferenceMoney() {
        return yearDiferenceMoney;
    }

    public void setYearDiferenceMoney(Double yearDiferenceMoney) {
        this.yearDiferenceMoney = yearDiferenceMoney;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}