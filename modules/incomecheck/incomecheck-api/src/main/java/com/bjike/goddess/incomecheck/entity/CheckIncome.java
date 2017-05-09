package com.bjike.goddess.incomecheck.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 收入核算资金回笼
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:54 ]
 * @Description: [ 收入核算资金回笼 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "incomecheck_checkincome")
public class CheckIncome extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '时间'")
    private String time;

    /**
     * 计划收入
     */
    @Column(name = "planIncome", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划收入'")
    private Double planIncome;

    /**
     * 实际收入
     */
    @Column(name = "actualIncome", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际收入'")
    private Double actualIncome;

    /**
     * 比例
     */
    @Column(name = "rate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '比例'")
    private Double rate;

    /**
     * 差额
     */
    @Column(name = "balance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '差额'")
    private Double balance;

    /**
     * 目标任务量
     */
    @Column(name = "targetTask", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标任务量'")
    private Double targetTask;

    /**
     * 实际任务量
     */
    @Column(name = "actualTask", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际任务量'")
    private Double actualTask;

    /**
     * 完工比例
     */
    @Column(name = "completeRate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '完工比例'")
    private Double completeRate;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPlanIncome() {
        return planIncome;
    }

    public void setPlanIncome(Double planIncome) {
        this.planIncome = planIncome;
    }

    public Double getActualIncome() {
        return actualIncome;
    }

    public void setActualIncome(Double actualIncome) {
        this.actualIncome = actualIncome;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTargetTask() {
        return targetTask;
    }

    public void setTargetTask(Double targetTask) {
        this.targetTask = targetTask;
    }

    public Double getActualTask() {
        return actualTask;
    }

    public void setActualTask(Double actualTask) {
        this.actualTask = actualTask;
    }

    public Double getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(Double completeRate) {
        this.completeRate = completeRate;
    }


}