package com.bjike.goddess.subjectcollect.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 科目汇总表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "subjectcollect_subjectcollect")
public class SubjectCollect extends BaseEntity {

    /**
     * 代码
     */
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '代码'")
    private String code;

    /**
     * 一级科目
     */
    @Column(name = "firstSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '一级科目'")
    private String firstSubject;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 项目组/部门
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String projectGroup;

    /**
     * 期初借方余额
     */
    @Column(name = "beginningDebitAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '期初借方余额'")
    private Double beginningDebitAmount;

    /**
     * 期初贷方余额
     */
    @Column(name = "beginningCreditAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '期初贷方余额'")
    private Double beginningCreditAmount;

    /**
     * 本期借方发生额
     */
    @Column(name = "issueDebitAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '本期借方发生额'")
    private Double issueDebitAmount;

    /**
     * 本期贷方发生额
     */
    @Column(name = "issueCreditAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '本期贷方发生额'")
    private Double issueCreditAmount;

    /**
     * 期末借方余额
     */
    @Column(name = "endDebitAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '期末借方余额'")
    private Double endDebitAmount;

    /**
     * 期末贷方余额
     */
    @Column(name = "endCreditAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '期末贷方余额'")
    private Double endCreditAmount;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getBeginningDebitAmount() {
        return beginningDebitAmount;
    }

    public void setBeginningDebitAmount(Double beginningDebitAmount) {
        this.beginningDebitAmount = beginningDebitAmount;
    }

    public Double getBeginningCreditAmount() {
        return beginningCreditAmount;
    }

    public void setBeginningCreditAmount(Double beginningCreditAmount) {
        this.beginningCreditAmount = beginningCreditAmount;
    }

    public Double getIssueDebitAmount() {
        return issueDebitAmount;
    }

    public void setIssueDebitAmount(Double issueDebitAmount) {
        this.issueDebitAmount = issueDebitAmount;
    }

    public Double getIssueCreditAmount() {
        return issueCreditAmount;
    }

    public void setIssueCreditAmount(Double issueCreditAmount) {
        this.issueCreditAmount = issueCreditAmount;
    }

    public Double getEndDebitAmount() {
        return endDebitAmount;
    }

    public void setEndDebitAmount(Double endDebitAmount) {
        this.endDebitAmount = endDebitAmount;
    }

    public Double getEndCreditAmount() {
        return endCreditAmount;
    }

    public void setEndCreditAmount(Double endCreditAmount) {
        this.endCreditAmount = endCreditAmount;
    }

    public SubjectCollect(String firstSubject, Double beginningDebitAmount, Double beginningCreditAmount, Double issueDebitAmount, Double issueCreditAmount, Double endDebitAmount, Double endCreditAmount) {
        this.firstSubject = firstSubject;
        this.beginningDebitAmount = beginningDebitAmount;
        this.beginningCreditAmount = beginningCreditAmount;
        this.issueDebitAmount = issueDebitAmount;
        this.issueCreditAmount = issueCreditAmount;
        this.endDebitAmount = endDebitAmount;
        this.endCreditAmount = endCreditAmount;
    }
    public SubjectCollect(String area,String projectName,String projectGroup, Double beginningDebitAmount, Double beginningCreditAmount, Double issueDebitAmount, Double issueCreditAmount, Double endDebitAmount, Double endCreditAmount) {
        this.area = area;
        this.projectName = projectName;
        this.projectGroup = projectGroup;
        this.beginningDebitAmount = beginningDebitAmount;
        this.beginningCreditAmount = beginningCreditAmount;
        this.issueDebitAmount = issueDebitAmount;
        this.issueCreditAmount = issueCreditAmount;
        this.endDebitAmount = endDebitAmount;
        this.endCreditAmount = endCreditAmount;
    }

}