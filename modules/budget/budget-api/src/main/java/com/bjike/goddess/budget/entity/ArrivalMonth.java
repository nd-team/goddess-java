package com.bjike.goddess.budget.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 地区收入月
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:06 ]
 * @Description: [ 地区收入月 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "budget_arrivalmonth")
public class ArrivalMonth extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "arrival", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String arrival;

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

    /**
     * 目标收入
     */
    @Column(name = "targetIncome", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标收入'")
    private Double targetIncome;

    /**
     * 计划收入
     */
    @Column(name = "planIncome", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划收入'")
    private Double planIncome;

    /**
     * 比例
     */
    @Column(name = "scale", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '比例'")
    private Double scale;

    /**
     * 收入差异
     */
    @Column(name = "incomeDifferences", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '收入差异'")
    private Double incomeDifferences;

    /**
     * 目标任务量
     */
    @Column(name = "targetWork", nullable = false, columnDefinition = "INT(11)   COMMENT '目标任务量'")
    private Integer targetWork;

    /**
     * 实际完工量
     */
    @Column(name = "actualWork", nullable = false, columnDefinition = "INT(11)   COMMENT '实际完工量'")
    private Integer actualWork;

    /**
     * 任务量差异
     */
    @Column(name = "workDifferences", nullable = false, columnDefinition = "INT(11)   COMMENT '任务量差异'")
    private Integer workDifferences;


    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getTargetIncome() {
        return targetIncome;
    }

    public void setTargetIncome(Double targetIncome) {
        this.targetIncome = targetIncome;
    }

    public Double getPlanIncome() {
        return planIncome;
    }

    public void setPlanIncome(Double planIncome) {
        this.planIncome = planIncome;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Double getIncomeDifferences() {
        return incomeDifferences;
    }

    public void setIncomeDifferences(Double incomeDifferences) {
        this.incomeDifferences = incomeDifferences;
    }

    public Integer getTargetWork() {
        return targetWork;
    }

    public void setTargetWork(Integer targetWork) {
        this.targetWork = targetWork;
    }

    public Integer getActualWork() {
        return actualWork;
    }

    public void setActualWork(Integer actualWork) {
        this.actualWork = actualWork;
    }

    public Integer getWorkDifferences() {
        return workDifferences;
    }

    public void setWorkDifferences(Integer workDifferences) {
        this.workDifferences = workDifferences;
    }
}