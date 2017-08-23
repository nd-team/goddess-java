package com.bjike.goddess.subjectcollect.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 科目汇总表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubjectCollectTO extends BaseTO {

    /**
     * 代码
     */
    @NotBlank(message = "代码不能为空",groups = {ADD.class, EDIT.class})
    private String code;
    /**
     * 月份
     */
    @NotNull(message = "代码不能为空",groups = {ADD.class, EDIT.class})
    private Integer  months;
    /**
     * 一级科目
     */
    @NotBlank(message = "一级科目不能为空",groups = {ADD.class, EDIT.class})
    private String firstSubject;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String projectName;

    /**
     * 项目组/部门
     */
    @NotBlank(message = "项目组/部门不能为空",groups = {ADD.class, EDIT.class})
    private String projectGroup;

    /**
     * 期初借方余额
     */
    @NotNull(message = "期初借方余额不能为空",groups = {ADD.class, EDIT.class})
    private Double beginningDebitAmount;

    /**
     * 期初贷方余额
     */
    @NotNull(message = "期初贷方余额不能为空",groups = {ADD.class, EDIT.class})
    private Double beginningCreditAmount;

    /**
     * 本期借方发生额
     */
    @NotNull(message = "本期借方发生额不能为空",groups = {ADD.class, EDIT.class})
    private Double issueDebitAmount;

    /**
     * 本期贷方发生额
     */
    @NotNull(message = "本期贷方发生额不能为空",groups = {ADD.class, EDIT.class})
    private Double issueCreditAmount;

    /**
     * 期末借方余额
     */
    @NotNull(message = "期末借方余额不能为空",groups = {ADD.class, EDIT.class})
    private Double endDebitAmount;

    /**
     * 期末贷方余额
     */
    @NotNull(message = "期末贷方余额不能为空",groups = {ADD.class, EDIT.class})
    private Double endCreditAmount;

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

}