package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 公司员工补助信息记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:07 ]
 * @Description: [ 公司员工补助信息记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_assistancerecord")
public class AssistanceRecord extends BaseEntity {

    /**
     * 员工名称
     */
    @Column(name = "empName",  columnDefinition = "VARCHAR(255)   COMMENT '员工名称'")
    private String empName;

    /**
     * 员工编号
     */
    @Column(name = "empNumber",  columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String empNumber;

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
     * 岗位
     */
    @Column(name = "job",  columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String job;

    /**
     * 入职时间
     */
    @Column(name = "entryJobTime",  columnDefinition = "DATE  COMMENT '入职时间'")
    private LocalDate entryJobTime;

    /**
     * 补助方案序号
     */
    @Column(name = "planNumber",  columnDefinition = "VARCHAR(255)   COMMENT '补助方案序号'")
    private String planNumber;

    /**
     * 补助类型名称
     */
    @Column(name = "assistName",  columnDefinition = "VARCHAR(255)   COMMENT '补助类型名称'")
    private String assistName;

    /**
     * 获得补助的开始时间
     */
    @Column(name = "assistStartTime",  columnDefinition = "DATE  COMMENT '获得补助的开始时间'")
    private LocalDate assistStartTime;

    /**
     * 获得补助的结束时间
     */
    @Column(name = "assistEndTime",  columnDefinition = "DATE  COMMENT '获得补助的结束时间'")
    private LocalDate assistEndTime;

    /**
     * 计薪周期开始时间
     */
    @Column(name = "salaryStartTime",  columnDefinition = "DATE  COMMENT '计薪周期开始时间'")
    private LocalDate salaryStartTime;

    /**
     * 计薪周期结束时间
     */
    @Column(name = "salaryEndTime",  columnDefinition = "DATE  COMMENT '计薪周期结束时间'")
    private LocalDate salaryEndTime;

    /**
     * 获得补助的天数
     */
    @Column(name = "assistDays",  columnDefinition = "DECIMAL(10,2)  COMMENT '获得补助的天数'")
    private Double assistDays;

    /**
     * 补助内容
     */
    @Column(name = "assistContent",  columnDefinition = "VARCHAR(255)   COMMENT '补助内容'")
    private String assistContent;

    /**
     * 内容明细
     */
    @Column(name = "contentDetail",  columnDefinition = "VARCHAR(255)   COMMENT '内容明细'")
    private String contentDetail;

    /**
     * 补助形式
     */
    @Column(name = "assistForm",  columnDefinition = "VARCHAR(255)   COMMENT '补助形式'")
    private String assistForm;

    /**
     * 补助发放时间
     */
    @Column(name = "assistGiveTime",  columnDefinition = "DATE  COMMENT '补助发放时间'")
    private LocalDate assistGiveTime;

    /**
     * 本人是否已领补助(是/否)
     */
    @Column(name = "recieveCondition",  columnDefinition = "VARCHAR(255)   COMMENT '本人是否已领补助(是/否)'")
    private String recieveCondition;

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

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDate getEntryJobTime() {
        return entryJobTime;
    }

    public void setEntryJobTime(LocalDate entryJobTime) {
        this.entryJobTime = entryJobTime;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getAssistName() {
        return assistName;
    }

    public void setAssistName(String assistName) {
        this.assistName = assistName;
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

    public String getAssistContent() {
        return assistContent;
    }

    public void setAssistContent(String assistContent) {
        this.assistContent = assistContent;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getAssistForm() {
        return assistForm;
    }

    public void setAssistForm(String assistForm) {
        this.assistForm = assistForm;
    }

    public LocalDate getAssistGiveTime() {
        return assistGiveTime;
    }

    public void setAssistGiveTime(LocalDate assistGiveTime) {
        this.assistGiveTime = assistGiveTime;
    }

    public String getRecieveCondition() {
        return recieveCondition;
    }

    public void setRecieveCondition(String recieveCondition) {
        this.recieveCondition = recieveCondition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}