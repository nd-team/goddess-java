package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 利润表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_profit")
public class Profit extends BaseEntity {

    /**
     * 项目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String project;

    /**
     * 本月数
     */
    @Column(name = "currentMonthAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '本月数'")
    private Double currentMonthAmount;

    /**
     * 本年累计数
     */
    @Column(name = "currentYearAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '本年累计数'")
    private Double currentYearAmount;


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getCurrentMonthAmount() {
        return currentMonthAmount;
    }

    public void setCurrentMonthAmount(Double currentMonthAmount) {
        this.currentMonthAmount = currentMonthAmount;
    }

    public Double getCurrentYearAmount() {
        return currentYearAmount;
    }

    public void setCurrentYearAmount(Double currentYearAmount) {
        this.currentYearAmount = currentYearAmount;
    }
}