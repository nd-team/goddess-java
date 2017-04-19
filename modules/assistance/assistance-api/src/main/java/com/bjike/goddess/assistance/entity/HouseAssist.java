package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 住宿补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 11:27 ]
 * @Description: [ 住宿补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_houseassist")
public class HouseAssist extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 姓名
     */
    @Column(name = "empName",  columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String empName;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 岗位
     */
    @Column(name = "jobPosition",  columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobPosition;

    /**
     * 获得补助开始时间
     */
    @Column(name = "assistStartTime",  columnDefinition = "DATE   COMMENT '获得补助开始时间'")
    private LocalDate assistStartTime;

    /**
     * 获得补助结束时间
     */
    @Column(name = "assistEndTime",  columnDefinition = "DATE   COMMENT '获得补助结束时间'")
    private LocalDate assistEndTime;

    /**
     * 计薪周期开始时间
     */
    @Column(name = "salaryStartTime",  columnDefinition = "DATE  COMMENT '计薪周期开始时间'")
    private LocalDate salaryStartTime;

    /**
     * 计薪周期结束时间
     */
    @Column(name = "salaryEndTime",  columnDefinition = "DATE   COMMENT '计薪周期结束时间'")
    private LocalDate salaryEndTime;

    /**
     * 外宿天数
     */
    @Column(name = "outDays",  columnDefinition = "DECIMAL(10,2)   COMMENT '外宿天数'")
    private Double outDays;

    /**
     * 住宿补助额度
     */
    @Column(name = "money",  columnDefinition = "DECIMAL(10,2)   COMMENT '住宿补助额度'")
    private Double money;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

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

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
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

    public Double getOutDays() {
        return outDays;
    }

    public void setOutDays(Double outDays) {
        this.outDays = outDays;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}