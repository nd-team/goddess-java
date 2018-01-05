package com.bjike.goddess.projectcost.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 其他费用
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:02 ]
 * @Description: [ 其他费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectcost_other_expenses")
public class OtherExpenses extends BaseEntity {

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
     * 类别
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类别'")
    private String type;

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
     * 目标其他费用
     */
    @Column(name = "target", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标其他费用'")
    private Double target;

    /**
     * 实际其他费用
     */
    @Column(name = "actual", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际其他费用'")
    private Double actual;

    /**
     * 比例
     */
    @Column(name = "ratio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '比例'")
    private Double ratio;

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

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}