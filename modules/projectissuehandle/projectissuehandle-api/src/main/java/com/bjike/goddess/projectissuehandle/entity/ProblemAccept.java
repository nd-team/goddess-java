package com.bjike.goddess.projectissuehandle.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectissuehandle.enums.AffectedDepartment;
import com.bjike.goddess.projectissuehandle.enums.NoticeWay;
import com.bjike.goddess.projectissuehandle.enums.ProblemProcessingTime;
import com.bjike.goddess.projectissuehandle.enums.ProblemTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


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
     * 项目问题编号
     */
    @Column(name = "projectNum", unique = true,columnDefinition = "VARCHAR(255)   COMMENT '项目问题编号'")
    private String projectNum;
    /**
     * 年份
     */
    @Column(name = "year", columnDefinition = "VARCHAR(255)   COMMENT '年份'")
    private String year;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 合同外部编号
     */
    @Column(name = "externalContractNum", columnDefinition = "VARCHAR(255)   COMMENT '合同外部编号'")
    private String externalContractNum;

    /**
     * 合同外部项目名称
     */
    @Column(name = "externalContractProjectName", columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String externalContractProjectName;

    /**
     * 内部项目名称
     */
    @Column(name = "internalProjectName", columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String internalProjectName;

    /**
     * 内部项目编号
     */
    @Column(name = "internalNum", columnDefinition = "VARCHAR(255)   COMMENT '内部项目编号'")
    private String internalNum;

    /**
     * 工程类型
     */
    @Column(name = "projectType", columnDefinition = "VARCHAR(255)   COMMENT '工程类型'")
    private String projectType;

    /**
     * 通知方式
     */
    @Column(name = "noticeWay", columnDefinition = "TINYINT(2)   COMMENT '通知方式'")
    private NoticeWay noticeWay;

    /**
     * 问题具体内容
     */
    @Column(name = "problemSpecificContent", columnDefinition = "VARCHAR(255)   COMMENT '问题具体内容'")
    private String problemSpecificContent;

    /**
     * 问题类型
     */
    @Column(name = "problemTypes", columnDefinition = "TINYINT(2)   COMMENT '问题类型'")
    private ProblemTypes problemTypes;

    /**
     * 解决方式
     */
    @Column(name = "solution", columnDefinition = "VARCHAR(255)   COMMENT '解决方式'")
    private String solution;

    /**
     * 问题紧急程度
     */
    @Column(name = "problemEmergencyDegree", columnDefinition = "VARCHAR(255)   COMMENT '问题紧急程度'")
    private String problemEmergencyDegree;

    /**
     * 问题处理时间
     */
    @Column(name = "problemProcessingTime", columnDefinition = "TINYINT(2)   COMMENT '问题处理时间'")
    private ProblemProcessingTime problemProcessingTime;

    /**
     * 受影响部门
     */
    @Column(name = "affectedDepartment", columnDefinition = "TINYINT(2)   COMMENT '受影响部门'")
    private AffectedDepartment affectedDepartment;

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

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

    public NoticeWay getNoticeWay() {
        return noticeWay;
    }

    public void setNoticeWay(NoticeWay noticeWay) {
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

    public String getProblemEmergencyDegree() {
        return problemEmergencyDegree;
    }

    public void setProblemEmergencyDegree(String problemEmergencyDegree) {
        this.problemEmergencyDegree = problemEmergencyDegree;
    }

    public ProblemProcessingTime getProblemProcessingTime() {
        return problemProcessingTime;
    }

    public void setProblemProcessingTime(ProblemProcessingTime problemProcessingTime) {
        this.problemProcessingTime = problemProcessingTime;
    }

    public AffectedDepartment getAffectedDepartment() {
        return affectedDepartment;
    }

    public void setAffectedDepartment(AffectedDepartment affectedDepartment) {
        this.affectedDepartment = affectedDepartment;
    }

}