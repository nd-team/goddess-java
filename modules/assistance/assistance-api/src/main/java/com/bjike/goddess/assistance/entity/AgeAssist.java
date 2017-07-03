package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 工龄补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:59 ]
 * @Description: [ 工龄补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_ageassist")
public class AgeAssist extends BaseEntity {

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
     * 员工编号
     */
    @Column(name = "empNumber",  columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String empNumber;

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
     * 入职日期
     */
    @Column(name = "entryTime",  columnDefinition = "DATE   COMMENT '入职日期'")
    private LocalDate entryTime;

    /**
     * 开始发放补助日期
     */
    @Column(name = "giveAssistTime",  columnDefinition = "DATE   COMMENT '开始发放补助日期'")
    private LocalDate giveAssistTime;

    /**
     * 本公司工龄(月)
     */
    @Column(name = "jobAge",  columnDefinition = "DECIMAL(10,2)   COMMENT '本公司工龄(月)'")
    private Double jobAge;

    /**
     * 应获补助
     */
    @Column(name = "assisCondition",  columnDefinition = "VARCHAR(255)   COMMENT '应获补助'")
    private String assisCondition;

    /**
     * 状态(离职/在职)
     */
    @Column(name = "empStatus",  columnDefinition = "VARCHAR(255)   COMMENT '状态(离职/在职)'")
    private String empStatus;




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

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
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

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDate getGiveAssistTime() {
        return giveAssistTime;
    }

    public void setGiveAssistTime(LocalDate giveAssistTime) {
        this.giveAssistTime = giveAssistTime;
    }

    public Double getJobAge() {
        return jobAge;
    }

    public void setJobAge(Double jobAge) {
        this.jobAge = jobAge;
    }

    public String getAssisCondition() {
        return assisCondition;
    }

    public void setAssisCondition(String assisCondition) {
        this.assisCondition = assisCondition;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

}