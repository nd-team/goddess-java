package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 补助员工名单
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:12 ]
 * @Description: [ 补助员工名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_assistanceemp")
public class AssistanceEmp extends BaseEntity {

    /**
     * 补助方案序号
     */
    @Column(name = "planNumber", columnDefinition = "VARCHAR(255)   COMMENT '补助方案序号'")
    private String planNumber;

    /**
     * 补助类型名称
     */
    @Column(name = "assistName", columnDefinition = "VARCHAR(255)   COMMENT '补助类型名称'")
    private String assistName;

    /**
     * 补助内容
     */
    @Column(name = "assistContent", columnDefinition = "VARCHAR(255)   COMMENT '补助内容'")
    private String assistContent;

    /**
     * 内容明细
     */
    @Column(name = "contentDetail", columnDefinition = "VARCHAR(255)   COMMENT '内容明细'")
    private String contentDetail;

    /**
     * 获得补助的开始时间
     */
    @Column(name = "recieveStartTime", columnDefinition = "DATE   COMMENT '获得补助的开始时间'")
    private LocalDate recieveStartTime;

    /**
     * 获得补助的结束时间
     */
    @Column(name = "recieveEndTime", columnDefinition = "DATE   COMMENT '获得补助的结束时间'")
    private LocalDate recieveEndTime;

    /**
     * 员工名称
     */
    @Column(name = "empName", columnDefinition = "VARCHAR(255)   COMMENT '员工名称'")
    private String empName;

    /**
     * 员工编号
     */
    @Column(name = "empNumber", columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String empNumber;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 岗位
     */
    @Column(name = "job", columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String job;

    /**
     * 入职时间
     */
    @Column(name = "entryJobTime", columnDefinition = "DATE  COMMENT '入职时间'")
    private LocalDate entryJobTime;

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

    public LocalDate getRecieveStartTime() {
        return recieveStartTime;
    }

    public void setRecieveStartTime(LocalDate recieveStartTime) {
        this.recieveStartTime = recieveStartTime;
    }

    public LocalDate getRecieveEndTime() {
        return recieveEndTime;
    }

    public void setRecieveEndTime(LocalDate recieveEndTime) {
        this.recieveEndTime = recieveEndTime;
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
}