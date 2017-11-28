package com.bjike.goddess.voucher.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 科目汇总表表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubjectCollectExport {

    /**
     * 代码
     */
    @ExcelHeader(name = "代码" ,notNull = true)
    private String code;
    /**
     * 月份
     */
    @ExcelHeader(name = "月份" ,notNull = true)
    private Integer  months;
    /**
     * 一级科目
     */
    @ExcelHeader(name = "一级科目" ,notNull = true)
    private String firstSubject;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区" ,notNull = true)
    private String area;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称" ,notNull = true)
    private String projectName;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门" ,notNull = true)
    private String projectGroup;

    /**
     * 期初借方余额
     */
    @ExcelHeader(name = "期初借方余额" ,notNull = true)
    private Double beginningDebitAmount;

    /**
     * 期初贷方余额
     */
    @ExcelHeader(name = "期初贷方余额" ,notNull = true)
    private Double beginningCreditAmount;

    /**
     * 本期借方发生额
     */
    @ExcelHeader(name = "本期借方发生额" ,notNull = true)
    private Double issueDebitAmount;

    /**
     * 本期贷方发生额
     */
    @ExcelHeader(name = "本期贷方发生额" ,notNull = true)
    private Double issueCreditAmount;

    /**
     * 期末借方余额
     */
    @ExcelHeader(name = "期末借方余额" ,notNull = true)
    private Double endDebitAmount;

    /**
     * 期末贷方余额
     */
    @ExcelHeader(name = "期末贷方余额" ,notNull = true)
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

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

}