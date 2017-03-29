package com.bjike.goddess.projectissuehandle.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectissuehandle.enums.ProblemEmergencyDegree;
import com.bjike.goddess.projectissuehandle.enums.ProblemTypes;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 项目执行中的问题受理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:16 ]
 * @Description: [ 项目执行中的问题受理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectissuehandle_problemaccept")
public class ProblemAccept extends BaseEntity {

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '年份'")
    private String year;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 合同外部编号
     */
    @Column(name = "externalContractNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合同外部编号'")
    private String externalContractNum;

    /**
     * 合同外部项目名称
     */
    @Column(name = "externalContractProjectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String externalContractProjectName;

    /**
     * 内部项目名称
     */
    @Column(name = "internalProjectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String internalProjectName;

    /**
     * 内部项目编号
     */
    @Column(name = "internalNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目编号'")
    private String internalNum;

    /**
     * 工程类型
     */
    @Column(name = "projectType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工程类型'")
    private String projectType;

    /**
     * 通知方式
     */
    @Column(name = "noticeWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '通知方式'")
    private String noticeWay;

    /**
     * 问题具体内容
     */
    @Column(name = "problemSpecificContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题具体内容'")
    private String problemSpecificContent;

    /**
     * 问题类型
     */
    @Column(name = "problemTypes", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题类型'")
    private ProblemTypes problemTypes;

    /**
     * 解决方式
     */
    @Column(name = "solution", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '解决方式'")
    private String solution;

    /**
     * 问题紧急程度
     */
    @Column(name = "problemEmergencyDegree", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题紧急程度'")
    private ProblemEmergencyDegree problemEmergencyDegree;

    /**
     * 问题处理时间
     */
    @Column(name = "problemProcessingTime", nullable = false, columnDefinition = "DATE   COMMENT '问题处理时间'")
    private LocalDate problemProcessingTime;

    /**
     * 受影响部门
     */
    @Column(name = "affectedDepartment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '受影响部门'")
    private String affectedDepartment;
    /**
     * 确认问题处理结果
     */
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "problemAccept", fetch = FetchType.LAZY)
    private ProblemHandlingResult problemHandlingResult;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getExternalContractNum() {
        return externalContractNum;
    }

    public void setExternalContractNum(String externalContractNum) {
        this.externalContractNum = externalContractNum;
    }

    public String getExternalContractProjectName() {
        return externalContractProjectName;
    }

    public void setExternalContractProjectName(String externalContractProjectName) {
        this.externalContractProjectName = externalContractProjectName;
    }

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public String getInternalNum() {
        return internalNum;
    }

    public void setInternalNum(String internalNum) {
        this.internalNum = internalNum;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getNoticeWay() {
        return noticeWay;
    }

    public void setNoticeWay(String noticeWay) {
        this.noticeWay = noticeWay;
    }

    public String getProblemSpecificContent() {
        return problemSpecificContent;
    }

    public void setProblemSpecificContent(String problemSpecificContent) {
        this.problemSpecificContent = problemSpecificContent;
    }

    public ProblemTypes getProblemTypes() {
        return problemTypes;
    }

    public void setProblemTypes(ProblemTypes problemTypes) {
        this.problemTypes = problemTypes;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public ProblemEmergencyDegree getProblemEmergencyDegree() {
        return problemEmergencyDegree;
    }

    public void setProblemEmergencyDegree(ProblemEmergencyDegree problemEmergencyDegree) {
        this.problemEmergencyDegree = problemEmergencyDegree;
    }

    public LocalDate getProblemProcessingTime() {
        return problemProcessingTime;
    }

    public void setProblemProcessingTime(LocalDate problemProcessingTime) {
        this.problemProcessingTime = problemProcessingTime;
    }

    public String getAffectedDepartment() {
        return affectedDepartment;
    }

    public void setAffectedDepartment(String affectedDepartment) {
        this.affectedDepartment = affectedDepartment;
    }

    public ProblemHandlingResult getProblemHandlingResult() {
        return problemHandlingResult;
    }

    public void setProblemHandlingResult(ProblemHandlingResult problemHandlingResult) {
        this.problemHandlingResult = problemHandlingResult;
    }
}