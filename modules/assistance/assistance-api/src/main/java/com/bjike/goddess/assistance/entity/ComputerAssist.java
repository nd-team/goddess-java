package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 电脑补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:20 ]
 * @Description: [ 电脑补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_computerassist")
public class ComputerAssist extends BaseEntity {

    /**
     * 员工名称
     */
    @Column(name = "empName",  columnDefinition = "VARCHAR(255)   COMMENT '员工名称'")
    private String empName;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 电脑补助开始时间
     */
    @Column(name = "assistStartTime",  columnDefinition = "DATE   COMMENT '电脑补助开始时间'")
    private LocalDate assistStartTime;

    /**
     * 电脑补助结束时间
     */
    @Column(name = "assistEndTime",  columnDefinition = "DATE  COMMENT '电脑补助结束时间'")
    private LocalDate assistEndTime;

    /**
     * 补助计薪周期开始时间
     */
    @Column(name = "salaryStartTime",  columnDefinition = "DATE  COMMENT '补助计薪周期开始时间'")
    private LocalDate salaryStartTime;

    /**
     * 补助计薪周期结束时间
     */
    @Column(name = "salaryEndTime",  columnDefinition = "DATE  COMMENT '补助计薪周期结束时间'")
    private LocalDate salaryEndTime;

    /**
     * 补助天数
     */
    @Column(name = "assistDays",  columnDefinition = "DECIMAL(10,2)   COMMENT '补助天数'")
    private Double assistDays;

    /**
     * 补助金额补助金额(100元/月)
     */
    @Column(name = "assistMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '补助金额补助金额(100元/月)'")
    private Double assistMoney;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;




    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDate getAssistStartTime() {
        return assistStartTime;
    }

    public void setAssistStartTime(LocalDate assistStartTime) {
        this.assistStartTime = assistStartTime;
    }

    public LocalDate getAssistEndTime() {
        return assistEndTime;
    }

    public void setAssistEndTime(LocalDate assistEndTime) {
        this.assistEndTime = assistEndTime;
    }

    public LocalDate getSalaryStartTime() {
        return salaryStartTime;
    }

    public void setSalaryStartTime(LocalDate salaryStartTime) {
        this.salaryStartTime = salaryStartTime;
    }

    public LocalDate getSalaryEndTime() {
        return salaryEndTime;
    }

    public void setSalaryEndTime(LocalDate salaryEndTime) {
        this.salaryEndTime = salaryEndTime;
    }

    public Double getAssistDays() {
        return assistDays;
    }

    public void setAssistDays(Double assistDays) {
        this.assistDays = assistDays;
    }

    public Double getAssistMoney() {
        return assistMoney;
    }

    public void setAssistMoney(Double assistMoney) {
        this.assistMoney = assistMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}