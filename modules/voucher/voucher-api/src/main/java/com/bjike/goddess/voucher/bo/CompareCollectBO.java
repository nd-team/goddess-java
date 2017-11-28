package com.bjike.goddess.voucher.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-5-5.
 */
public class CompareCollectBO extends BaseBO {

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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
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