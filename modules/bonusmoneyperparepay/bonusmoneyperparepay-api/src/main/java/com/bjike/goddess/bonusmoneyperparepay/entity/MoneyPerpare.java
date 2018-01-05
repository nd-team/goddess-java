package com.bjike.goddess.bonusmoneyperparepay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 奖金资金准备
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:44 ]
 * @Description: [ 奖金资金准备]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bonusmoneyperparepay_moneyperpare")
public class MoneyPerpare extends BaseEntity {

    /**
     * 年份
     */
    @Column(name = "years", nullable = false, columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer years;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

    /**
     * 科目
     */
    @Column(name = "subjects", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目'")
    private String subjects;

    /**
     * 总准备金
     */
    @Column(name = "totalReserve", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总准备金'")
    private Double totalReserve;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 比例分配
     */
    @Column(name = "proportional", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '比例分配'")
    private Double proportional;

    /**
     * 准备金
     */
    @Column(name = "reserve", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '准备金'")
    private Double reserve;


    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Double getTotalReserve() {
        return totalReserve;
    }

    public void setTotalReserve(Double totalReserve) {
        this.totalReserve = totalReserve;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getProportional() {
        return proportional;
    }

    public void setProportional(Double proportional) {
        this.proportional = proportional;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
    }
}