package com.bjike.goddess.budget.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 项目收入周
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "budget_projectweek")
public class ProjectWeek extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "arrival", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String arrival;

    /**
     * 项目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String project;

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
     * 周数
     */
    @Column(name = "week", nullable = false, columnDefinition = "INT(11)   COMMENT '周数'")
    private Integer week;

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
     * 工作量差异
     */
    @Column(name = "workDifferences", nullable = false, columnDefinition = "INT(11)   COMMENT '工作量差异'")
    private Integer workDifferences;

    /**
     * 参考单价
     */
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '参考单价'")
    private Double price;

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
     * 收入差异
     */
    @Column(name = "incomeDifferences", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '收入差异'")
    private Double incomeDifferences;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Double getIncomeDifferences() {
        return incomeDifferences;
    }

    public void setIncomeDifferences(Double incomeDifferences) {
        this.incomeDifferences = incomeDifferences;
    }
}