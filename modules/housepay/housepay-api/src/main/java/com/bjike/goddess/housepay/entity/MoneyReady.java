package com.bjike.goddess.housepay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 资金准备审核表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:32 ]
 * @Description: [ 资金准备审核表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "housepay_moneyready")
public class MoneyReady extends BaseEntity {

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
     * 类别
     */
    @Column(name = "category", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类别'")
    private String category;

    /**
     * 科目
     */
    @Column(name = "subject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目'")
    private String subject;

    /**
     * 总准备金
     */
    @Column(name = "totalReserves", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总准备金'")
    private Double totalReserves;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 比例分配
     */
    @Column(name = "prorate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '比例分配'")
    private String prorate;

    /**
     * 准备金
     */
    @Column(name = "reserves", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '准备金'")
    private Double reserves;


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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotalReserves() {
        return totalReserves;
    }

    public void setTotalReserves(Double totalReserves) {
        this.totalReserves = totalReserves;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProrate() {
        return prorate;
    }

    public void setProrate(String prorate) {
        this.prorate = prorate;
    }

    public Double getReserves() {
        return reserves;
    }

    public void setReserves(Double reserves) {
        this.reserves = reserves;
    }
}