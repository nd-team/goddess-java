package com.bjike.goddess.projectmarketfee.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 费用效益分析
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:35 ]
 * @Description: [ 费用效益分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmarketfee_costanalysis")
public class CostAnalysis extends BaseEntity {

    /**
     * 项目组
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;

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
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 预计收入
     */
    @Column(name = "expectedIncome", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预计收入'")
    private Double expectedIncome;

    /**
     * 预计市场费
     */
    @Column(name = "expectedMarketCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预计市场费'")
    private Double expectedMarketCost;

    /**
     * 等级评定
     */
    @Column(name = "grade", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '等级评定'")
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Double getExpectedMarketCost() {
        return expectedMarketCost;
    }

    public void setExpectedMarketCost(Double expectedMarketCost) {
        this.expectedMarketCost = expectedMarketCost;
    }
}