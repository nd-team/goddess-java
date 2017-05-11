package com.bjike.goddess.projectcost.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 车辆费用
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:14 ]
 * @Description: [ 车辆费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectcost_car_cost")
public class CarCost extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;


    /**
     * 项目名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String name;

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
     * 目标车次
     */
    @Column(name = "targetDegree", nullable = false, columnDefinition = "INT(11)   COMMENT '目标车次'")
    private Integer targetDegree;

    /**
     * 实际车次
     */
    @Column(name = "actualDegree", nullable = false, columnDefinition = "INT(11)   COMMENT '实际车次'")
    private Integer actualDegree;

    /**
     * 车次差额
     */
    @Column(name = "balanceDegree", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '车次差额'")
    private Double balanceDegree;

    /**
     * 单价
     */
    @Column(name = "univalent", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '单价'")
    private Double univalent;

    /**
     * 目标车辆费用
     */
    @Column(name = "targetCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标车辆费用'")
    private Double targetCost;

    /**
     * 实际车辆费用
     */
    @Column(name = "actualCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际车辆费用'")
    private Double actualCost;

    /**
     * 差额
     */
    @Column(name = "balanceCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '差额'")
    private Double balanceCost;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getTargetDegree() {
        return targetDegree;
    }

    public void setTargetDegree(Integer targetDegree) {
        this.targetDegree = targetDegree;
    }

    public Integer getActualDegree() {
        return actualDegree;
    }

    public void setActualDegree(Integer actualDegree) {
        this.actualDegree = actualDegree;
    }

    public Double getBalanceDegree() {
        return balanceDegree;
    }

    public void setBalanceDegree(Double balanceDegree) {
        this.balanceDegree = balanceDegree;
    }

    public Double getUnivalent() {
        return univalent;
    }

    public void setUnivalent(Double univalent) {
        this.univalent = univalent;
    }

    public Double getTargetCost() {
        return targetCost;
    }

    public void setTargetCost(Double targetCost) {
        this.targetCost = targetCost;
    }

    public Double getActualCost() {
        return actualCost;
    }

    public void setActualCost(Double actualCost) {
        this.actualCost = actualCost;
    }

    public Double getBalanceCost() {
        return balanceCost;
    }

    public void setBalanceCost(Double balanceCost) {
        this.balanceCost = balanceCost;
    }
}