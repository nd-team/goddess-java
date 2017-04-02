package com.bjike.goddess.individualvision.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;

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

    /**
     * 姓名
     */
    private String name;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门
     */
    private String department;

    /**
     * 入职时间String
     */
    private String entryTime;

    /**
     * 期望薪资上升幅度
     */
    private String expectedSalaryIncrease;

    /**
     * 规划日期
     */
    private String planningDate;

    /**
     * 目前状态
     */
    private Status currentState;

    /**
     * 选择发展路径：转正
     */
    private String positive;

    /**
     * 选择发展路径：管理方面
     */
    private String management;

    /**
     * 选择发展路径：技能方面
     */
    private String skills;

    /**
     * 期望此能力的提升程度
     */
    private String degreeAbilityAscend;

    /**
     * 期望学到的东西
     */
    private String expectLearnThings;

    /**
     * 预想的权利及义务
     */
    private String rightsObligations;

    /**
     * 预计完成时间
     */
    private String expectedCompletionTime;

    /**
     * 期望此时的职位状态
     */
    private Status positionsStatus;

    /**
     * 期望周收益（元）
     */
    private String expectWeeksEarnings;

    /**
     * 期望月收益（元）
     */
    private String expectMonthsEarnings;

    /**
     * 期望年收益（元）
     */
    private String expectYearsEarnings;


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

    public Status getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Status currentState) {
        this.currentState = currentState;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
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

    public Status getPositionsStatus() {
        return positionsStatus;
    }

    public void setPositionsStatus(Status positionsStatus) {
        this.positionsStatus = positionsStatus;
    }

    public String getExpectWeeksEarnings() {
        return expectWeeksEarnings;
    }

    public void setExpectWeeksEarnings(String expectWeeksEarnings) {
        this.expectWeeksEarnings = expectWeeksEarnings;
    }

    public String getExpectMonthsEarnings() {
        return expectMonthsEarnings;
    }

    public void setExpectMonthsEarnings(String expectMonthsEarnings) {
        this.expectMonthsEarnings = expectMonthsEarnings;
    }

    public String getExpectYearsEarnings() {
        return expectYearsEarnings;
    }

    public void setExpectYearsEarnings(String expectYearsEarnings) {
        this.expectYearsEarnings = expectYearsEarnings;
    }
}