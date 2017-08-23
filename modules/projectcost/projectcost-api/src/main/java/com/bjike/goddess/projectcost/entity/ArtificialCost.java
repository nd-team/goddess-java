package com.bjike.goddess.projectcost.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 人工费用
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:19 ]
 * @Description: [ 人工费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectcost_artificial_cost")
public class ArtificialCost extends BaseEntity {

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
     * 目标人工时数
     */
    @Column(name = "targetHour", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标人工时数'")
    private Double targetHour;

    /**
     * 实际人工时数
     */
    @Column(name = "actualHour", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际人工时数'")
    private Double actualHour;

    /**
     * 单价
     */
    @Column(name = "univalent", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '单价'")
    private Double univalent;

    /**
     * 目标人工成本
     */
    @Column(name = "targetCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标人工成本'")
    private Double targetCost;

    /**
     * 实际人工成本
     */
    @Column(name = "actualCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际人工成本'")
    private Double actualCost;

    /**
     * 预警
     */
    @Column(name = "alert", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预警'")
    private Double alert;

    /**
     * 费用占比
     */
    @Column(name = "proportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '费用占比'")
    private Double proportion;


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

    public Double getTargetHour() {
        return targetHour;
    }

    public void setTargetHour(Double targetHour) {
        this.targetHour = targetHour;
    }

    public Double getActualHour() {
        return actualHour;
    }

    public void setActualHour(Double actualHour) {
        this.actualHour = actualHour;
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

    public Double getAlert() {
        return alert;
    }

    public void setAlert(Double alert) {
        this.alert = alert;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }
}