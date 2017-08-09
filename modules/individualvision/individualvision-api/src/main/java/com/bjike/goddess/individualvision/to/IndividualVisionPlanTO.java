package com.bjike.goddess.individualvision.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 个人愿景计划
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:26 ]
 * @Description: [ 个人愿景计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndividualVisionPlanTO extends BaseTO {
    public interface TestAudit{}

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 部门
     */
    @NotBlank(message = "部门不能为空",groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 入职时间
     */
    @NotBlank(message = "入职时间不能为空",groups = {ADD.class, EDIT.class})
    private String entryTime;
    /**
     * 期望的福利待遇
     */
    @NotBlank(message = "期望的福利待遇不能为空",groups = {ADD.class, EDIT.class})
    private String expectedBenefit;


    /**
     * 期望薪资上升幅度
     */
    @NotBlank(message = "期望薪资上升幅度不能为空",groups = {ADD.class, EDIT.class})
    private String expectedSalaryIncrease;

    /**
     * 规划日期
     */
    @NotBlank(message = "规划日期不能为空",groups = {ADD.class, EDIT.class})
    private String planningDate;

    /**
     * 目前状态
     */
    @NotBlank(message = "目前状态不能为空",groups = {ADD.class, EDIT.class})
    private String currentState;

    /**
     * 选择发展路径：
     */
    @NotBlank(message = "选择发展路径：不能为空",groups = {ADD.class, EDIT.class})
    private String positive;

    /**
     * 期望此能力的提升程度
     */
    @NotBlank(message = "期望此能力的提升程度不能为空",groups = {ADD.class, EDIT.class})
    private String degreeAbilityAscend;

    /**
     * 期望学到的东西
     */
    @NotBlank(message = "选择发展路径：不能为空",groups = {ADD.class, EDIT.class})
    private String expectLearnThings;

    /**
     * 预想的权利及义务
     */
    @NotBlank(message = "预想的权利及义务不能为空",groups = {ADD.class, EDIT.class})
    private String rightsObligations;

    /**
     * 预计完成时间
     */
    @NotBlank(message = "预计完成时间不能为空",groups = {ADD.class, EDIT.class})
    private String expectedCompletionTime;

    /**
     * 期望此时的职位状态
     */
    @NotBlank(message = "期望此时的职位状态不能为空",groups = {ADD.class, EDIT.class})
    private String positionsStatus;

    /**
     * 期望周收益（元）
     */
    @NotNull(message = "期望周收益（元）不能为空",groups = {ADD.class, EDIT.class})
    private Integer expectWeeksEarnings;

    /**
     * 期望月收益（元）
     */
    @NotNull(message = "期望月收益（元）不能为空",groups = {ADD.class, EDIT.class})
    private Integer expectMonthsEarnings;

    /**
     * 期望年收益（元）
     */
    @NotNull(message = "期望年收益（元）不能为空",groups = {ADD.class, EDIT.class})
    private Integer expectYearsEarnings;
    /**
     * 审核人
     */
    @NotBlank(message = "审核人不能为空",groups = {IndividualVisionPlanTO.TestAudit.class})
    private String audit;
    /**
     * 审核状态
     */
    @NotBlank(message = "审核状态不能为空",groups = {IndividualVisionPlanTO.TestAudit.class})
    private String auditStatus;

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getExpectedBenefit() {
        return expectedBenefit;
    }

    public void setExpectedBenefit(String expectedBenefit) {
        this.expectedBenefit = expectedBenefit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExpectedSalaryIncrease() {
        return expectedSalaryIncrease;
    }

    public void setExpectedSalaryIncrease(String expectedSalaryIncrease) {
        this.expectedSalaryIncrease = expectedSalaryIncrease;
    }

    public String getPlanningDate() {
        return planningDate;
    }

    public void setPlanningDate(String planningDate) {
        this.planningDate = planningDate;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }


    public String getDegreeAbilityAscend() {
        return degreeAbilityAscend;
    }

    public void setDegreeAbilityAscend(String degreeAbilityAscend) {
        this.degreeAbilityAscend = degreeAbilityAscend;
    }

    public String getExpectLearnThings() {
        return expectLearnThings;
    }

    public void setExpectLearnThings(String expectLearnThings) {
        this.expectLearnThings = expectLearnThings;
    }

    public String getRightsObligations() {
        return rightsObligations;
    }

    public void setRightsObligations(String rightsObligations) {
        this.rightsObligations = rightsObligations;
    }

    public String getExpectedCompletionTime() {
        return expectedCompletionTime;
    }

    public void setExpectedCompletionTime(String expectedCompletionTime) {
        this.expectedCompletionTime = expectedCompletionTime;
    }

    public String getPositionsStatus() {
        return positionsStatus;
    }

    public void setPositionsStatus(String positionsStatus) {
        this.positionsStatus = positionsStatus;
    }

    public Integer getExpectWeeksEarnings() {
        return expectWeeksEarnings;
    }

    public void setExpectWeeksEarnings(Integer expectWeeksEarnings) {
        this.expectWeeksEarnings = expectWeeksEarnings;
    }

    public Integer getExpectMonthsEarnings() {
        return expectMonthsEarnings;
    }

    public void setExpectMonthsEarnings(Integer expectMonthsEarnings) {
        this.expectMonthsEarnings = expectMonthsEarnings;
    }

    public Integer getExpectYearsEarnings() {
        return expectYearsEarnings;
    }

    public void setExpectYearsEarnings(Integer expectYearsEarnings) {
        this.expectYearsEarnings = expectYearsEarnings;
    }
}