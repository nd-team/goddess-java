package com.bjike.goddess.fundrecords.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资金流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "fundrecords_fundrecord")
public class FundRecord extends BaseEntity {

    /**
     * 流水日期
     */
    @Column(name = "recordDate", nullable = false, columnDefinition = "DATE   COMMENT '流水日期'")
    private LocalDate recordDate;

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
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 摘要
     */
    @Column(name = "digest", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '摘要'")
    private String digest;

    /**
     * 收入
     */
    @Column(name = "income", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '收入'")
    private Double income;

    /**
     * 支出
     */
    @Column(name = "expenditure", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '支出'")
    private Double expenditure;

    /**
     * 数据来源
     */
    @Column(name = "dataSource", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '支出'")
    private String dataSource;


    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

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

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}