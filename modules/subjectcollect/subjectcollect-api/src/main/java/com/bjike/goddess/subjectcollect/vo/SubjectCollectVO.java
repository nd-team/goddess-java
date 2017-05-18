package com.bjike.goddess.subjectcollect.vo;

/**
 * 科目汇总表表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubjectCollectVO {

    /**
     * id
     */
    private String id;
    /**
     * 代码
     */
    private String code;
    /**
     * 月份
     */
    private Integer  months;
    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目组/部门
     */
    private String projectGroup;

    /**
     * 期初借方余额
     */
    private Double beginningDebitAmount;

    /**
     * 期初贷方余额
     */
    private Double beginningCreditAmount;
    /**
     * 期初差额
     */
    private Double beginMinusMoney;

    /**
     * 本期借方发生额
     */
    private Double issueDebitAmount;

    /**
     * 本期贷方发生额
     */
    private Double issueCreditAmount;
    /**
     * 本期差额
     */
    private Double issueMinusMoney;

    /**
     * 期末借方余额
     */
    private Double endDebitAmount;

    /**
     * 期末贷方余额
     */
    private Double endCreditAmount;

    /**
     * 期末差额
     */
    private Double endMinusMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Double getBeginMinusMoney() {
        return beginMinusMoney;
    }

    public void setBeginMinusMoney(Double beginMinusMoney) {
        this.beginMinusMoney = beginMinusMoney;
    }

    public Double getIssueMinusMoney() {
        return issueMinusMoney;
    }

    public void setIssueMinusMoney(Double issueMinusMoney) {
        this.issueMinusMoney = issueMinusMoney;
    }

    public Double getEndMinusMoney() {
        return endMinusMoney;
    }

    public void setEndMinusMoney(Double endMinusMoney) {
        this.endMinusMoney = endMinusMoney;
    }
}