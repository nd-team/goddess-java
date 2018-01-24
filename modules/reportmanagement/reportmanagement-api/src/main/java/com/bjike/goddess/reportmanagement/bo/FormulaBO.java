package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.reportmanagement.enums.AccessRules;
import com.bjike.goddess.reportmanagement.enums.Form;

/**
 * 对应的公式业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FormulaBO extends BaseBO {

    /**
     * 科目
     */
    private String project;

    /**
     * 公式方向
     */
    private Form form;

    /**
     * 取数规则
     */
    private AccessRules accessRules;

    /**
     * 年初数
     */
    private Double begin;

    /**
     * 期末数
     */
    private Double end;

    /**
     * 本期发生额(本月数)
     */
    private Double current;

    /**
     * 运算方式
     */
    private String operation;

    /**
     * 本年累计数
     */
    private Double year;

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

    public Double getYear() {
        return year;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getBegin() {
        return begin;
    }

    public void setBegin(Double begin) {
        this.begin = begin;
    }

    public Double getEnd() {
        return end;
    }

    public void setEnd(Double end) {
        this.end = end;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public AccessRules getAccessRules() {
        return accessRules;
    }

    public void setAccessRules(AccessRules accessRules) {
        this.accessRules = accessRules;
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
}