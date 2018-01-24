package com.bjike.goddess.voucher.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [zhuangkaiqin]
 * @Date: [20171102 14:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SubjectCollectBO extends BaseBO {

//    /**
//     * 一级科目
//     */
//    private String firstSubject;
//
//    /**
//     * 地区
//     */
//    private String area;
//
//    /**
//     * 项目名称
//     */
//    private String projectName;
//
//    /**
//     * 项目组/部门
//     */
//    private String projectGroup;

    /**
     * 年初数
     */
    private Double beginAmount;

    /**
     * 年末数
     */
    private Double endAmount;

    /**
     * 本年累计数
     */
    private Double yearAmount;

    /**
     * 本期发生额(本月数)
     */
    private Double currentAmount;

    /**
     * 期初余额
     */
    private Double beginningCreditAmount;

    /**
     * 本期借方总额
     */
    private Double issueDebitAmount;

    /**
     * 本期贷方总额
     */
    private Double issueCreditAmount;

    /**
     * 本期合计余额
     */
    private Double issueTotalAmount;

    /**
     * 期末借方总额
     */
    private Double endDebitAmount;

    /**
     * 期末贷方总额
     */
    private Double endCreditAmount;

    /**
     * 本年累计额
     */
    private Double endTotalAmount;


    public Double getYearAmount() {
        return yearAmount;
    }

    public void setYearAmount(Double yearAmount) {
        this.yearAmount = yearAmount;
    }

    public Double getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(Double beginAmount) {
        this.beginAmount = beginAmount;
    }

    public Double getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(Double endAmount) {
        this.endAmount = endAmount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
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

    public Double getIssueTotalAmount() {
        return issueTotalAmount;
    }

    public void setIssueTotalAmount(Double issueTotalAmount) {
        this.issueTotalAmount = issueTotalAmount;
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

    public Double getEndTotalAmount() {
        return endTotalAmount;
    }

    public void setEndTotalAmount(Double endTotalAmount) {
        this.endTotalAmount = endTotalAmount;
    }

    public Double getBeginningCreditAmount() {
        return beginningCreditAmount;
    }

    public void setBeginningCreditAmount(Double beginningCreditAmount) {
        this.beginningCreditAmount = beginningCreditAmount;
    }
}
