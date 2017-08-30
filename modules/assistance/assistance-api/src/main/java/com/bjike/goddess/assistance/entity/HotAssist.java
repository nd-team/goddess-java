package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 高温补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:52 ]
 * @Description: [ 高温补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_hotassist")
public class HotAssist extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目名称
     */
    @Column(name = "projectName",  columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 获得补助开始时间
     */
    @Column(name = "assistStartTime",  columnDefinition = "DATE  COMMENT '获得补助开始时间'")
    private LocalDate assistStartTime;

    /**
     * 获得补助结束时间
     */
    @Column(name = "assistEndTime",  columnDefinition = "DATE  COMMENT '获得补助结束时间'")
    private LocalDate assistEndTime;

    /**
     * 补助计薪周期开始时间
     */
    @Column(name = "salaryStartTime",  columnDefinition = "DATE   COMMENT '补助计薪周期开始时间'")
    private LocalDate salaryStartTime;

    /**
     * 补助计薪周期结束时间
     */
    @Column(name = "salaryEndTime",  columnDefinition = "DATE  COMMENT '补助计薪周期结束时间'")
    private LocalDate salaryEndTime;

    /**
     * 外出日期
     */
    @Column(name = "outTime",  columnDefinition = "DATE  COMMENT '外出日期'")
    private LocalDate outTime;

    /**
     * 高温补贴人员
     */
    @Column(name = "empName",  columnDefinition = "VARCHAR(255)   COMMENT '高温补贴人员'")
    private String empName;

    /**
     * 员工编号
     */
    @Column(name = "empNumber",  columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String empNumber;

    /**
     * 岗位
     */
    @Column(name = "jobPosition",  columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobPosition;

    /**
     * 出车单号
     */
    @Column(name = "outCarNumber",  columnDefinition = "VARCHAR(255)   COMMENT '出车单号'")
    private String outCarNumber;

    /**
     * 出车司机
     */
    @Column(name = "outDriver",  columnDefinition = "VARCHAR(255)   COMMENT '出车司机'")
    private String outDriver;

    /**
     * 高温补贴金额(6.9元/天)(有/无)
     */
    @Column(name = "moneyCondition",  columnDefinition = "VARCHAR(255)   COMMENT '高温补贴金额(6.9元/天)(有/无)'")
    private String moneyCondition;

    /**
     * 物资发放(有/无)
     */
    @Column(name = "thingCondtion",  columnDefinition = "VARCHAR(255)   COMMENT '物资发放(有/无)'")
    private String thingCondtion;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    /**
     * 总条数
     */
    @Transient
    private Integer counts;

    /**
     * 补助金额
     */
    @Transient
    private Double assistMoney;



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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public LocalDate getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDate outTime) {
        this.outTime = outTime;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getOutCarNumber() {
        return outCarNumber;
    }

    public void setOutCarNumber(String outCarNumber) {
        this.outCarNumber = outCarNumber;
    }

    public String getOutDriver() {
        return outDriver;
    }

    public void setOutDriver(String outDriver) {
        this.outDriver = outDriver;
    }

    public String getMoneyCondition() {
        return moneyCondition;
    }

    public void setMoneyCondition(String moneyCondition) {
        this.moneyCondition = moneyCondition;
    }

    public String getThingCondtion() {
        return thingCondtion;
    }

    public void setThingCondtion(String thingCondtion) {
        this.thingCondtion = thingCondtion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Double getAssistMoney() {
        return assistMoney;
    }

    public void setAssistMoney(Double assistMoney) {
        this.assistMoney = assistMoney;
    }
}