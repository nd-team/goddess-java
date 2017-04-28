package com.bjike.goddess.managefee.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 外包费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:39 ]
 * @Description: [ 外包费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managefee_outfee")
public class OutFee extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目名称
     */
    @Column(name = "project", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 类别
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '类别'")
    private String type;

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
     * 目标管理费
     */
    @Column(name = "targetFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标管理费'")
    private Double targetFee;

    /**
     * 实际管理费
     */
    @Column(name = "actualFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际管理费'")
    private Double actualFee;

    /**
     * 比率
     */
    @Column(name = "rate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '比率'")
    private Double rate;

    /**
     * 差额
     */
    @Column(name = "balance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '差额'")
    private Double balance;


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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Double getTargetFee() {
        return targetFee;
    }

    public void setTargetFee(Double targetFee) {
        this.targetFee = targetFee;
    }

    public Double getActualFee() {
        return actualFee;
    }

    public void setActualFee(Double actualFee) {
        this.actualFee = actualFee;
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
}