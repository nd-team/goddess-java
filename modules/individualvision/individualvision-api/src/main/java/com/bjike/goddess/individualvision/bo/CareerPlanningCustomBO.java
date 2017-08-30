package com.bjike.goddess.individualvision.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;

/**
 * 职业规划定制业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:45 ]
 * @Description: [ 职业规划定制业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CareerPlanningCustomBO extends BaseBO {

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
     * 入职时间
     */
    private String entryTime;
    /**
     * 可提供的福利待遇
     */
    private String availableBenefitPackage;

    /**
     * 薪资上升幅度
     */
    private String expectedSalaryIncrease;

    /**
     * 规划日期
     */
    private String planningDate;

    /**
     * 目前状态
     */
    private String currentState;

    /**
     * 选择发展路径：
     */
    private String positive;

    /**
     * 此路径能力提升程度的选择
     */
    private String degreeAbilityAscend;

    /**
     * 此时的权利及义务
     */
    private String rightsObligations;

    /**
     * 预计完成时间
     */
    private String expectedCompletionTime;

    /**
     * 实际完成时间
     */
    private String actualCompletionTime;

    /**
     * 是否按时完成
     */
    private Boolean finish;

    /**
     * 期望周收益（元）
     */
    private Integer expectWeeksEarnings;

    /**
     * 期望月收益（元）
     */
    private Integer expectMonthsEarnings;

    /**
     * 期望年收益（元）
     */
    private Integer expectYearsEarnings;

    /**
     * 实际周收益（元）
     */
    private Integer actualWeeksEarnings;

    /**
     * 实际月收益（元）
     */
    private Integer actualMonthsEarnings;

    /**
     * 实际年收益（元）
     */
    private Integer actualYearsEarnings;

    private String notUpStandard; //未达标项内容
    private String completeDegree;//此定制项完成程度
    private String standard;//此定制项标准
    private String surplusTime;//剩余时间

    public String getAvailableBenefitPackage() {
        return availableBenefitPackage;
    }

    public void setAvailableBenefitPackage(String availableBenefitPackage) {
        this.availableBenefitPackage = availableBenefitPackage;
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

    public String getActualCompletionTime() {
        return actualCompletionTime;
    }

    public void setActualCompletionTime(String actualCompletionTime) {
        this.actualCompletionTime = actualCompletionTime;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
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

    public Integer getActualWeeksEarnings() {
        return actualWeeksEarnings;
    }

    public void setActualWeeksEarnings(Integer actualWeeksEarnings) {
        this.actualWeeksEarnings = actualWeeksEarnings;
    }

    public Integer getActualMonthsEarnings() {
        return actualMonthsEarnings;
    }

    public void setActualMonthsEarnings(Integer actualMonthsEarnings) {
        this.actualMonthsEarnings = actualMonthsEarnings;
    }

    public Integer getActualYearsEarnings() {
        return actualYearsEarnings;
    }

    public void setActualYearsEarnings(Integer actualYearsEarnings) {
        this.actualYearsEarnings = actualYearsEarnings;
    }

    public String getNotUpStandard() {
        return notUpStandard;
    }

    public void setNotUpStandard(String notUpStandard) {
        this.notUpStandard = notUpStandard;
    }

    public String getCompleteDegree() {
        return completeDegree;
    }

    public void setCompleteDegree(String completeDegree) {
        this.completeDegree = completeDegree;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSurplusTime() {
        return surplusTime;
    }

    public void setSurplusTime(String surplusTime) {
        this.surplusTime = surplusTime;
    }
}