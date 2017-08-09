package com.bjike.goddess.individualvision.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.individualvision.enums.PositionsStatus;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 个人愿景计划
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:26 ]
 * @Description: [ 个人愿景计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "individualvision_individualvisionplan")
public class IndividualVisionPlan extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name",  columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 地区
     */
    @Column(name = "area",columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 部门
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 入职时间
     */
    @Column(name = "entryTime", columnDefinition = "DATE   COMMENT '入职时间String'")
    private LocalDate entryTime;
    /**
     * 期望的福利待遇
     */
    @Column(name = "expectedBenefit",  columnDefinition = "VARCHAR(255)   COMMENT '期望的福利待遇'")
    private String expectedBenefit;

    /**
     * 期望薪资上升幅度
     */
    @Column(name = "expectedSalaryIncrease",columnDefinition = "VARCHAR(255)   COMMENT '期望薪资上升幅度'")
    private String expectedSalaryIncrease;

    /**
     * 规划日期
     */
    @Column(name = "planningDate",columnDefinition = "DATE   COMMENT '规划日期'")
    private LocalDate planningDate;

    /**
     * 目前状态
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '目前状态'")
    private String currentState;

    /**
     * 选择发展路径：
     */
    @Column(name = "positive",columnDefinition = "VARCHAR(255)   COMMENT '选择发展路径：'")
    private String positive;


    /**
     * 期望此能力的提升程度
     */
    @Column(name = "degreeAbilityAscend",columnDefinition = "VARCHAR(255)   COMMENT '期望此能力的提升程度'")
    private String degreeAbilityAscend;

    /**
     * 期望学到的东西
     */
    @Column(name = "expectLearnThings",columnDefinition = "VARCHAR(255)   COMMENT '期望学到的东西'")
    private String expectLearnThings;

    /**
     * 预想的权利及义务
     */
    @Column(name = "rightsObligations",  columnDefinition = "VARCHAR(255)   COMMENT '预想的权利及义务'")
    private String rightsObligations;

    /**
     * 预计完成时间
     */
    @Column(name = "expectedCompletionTime",columnDefinition = "VARCHAR(255)   COMMENT '预计完成时间'")
    private LocalDate expectedCompletionTime;

    /**
     * 期望此时的职位状态
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '期望此时的职位状态'")
    private String positionsStatus;

    /**
     * 期望周收益（元）
     */
    @Column(name = "expectWeeksEarnings", columnDefinition = "INT(11)   COMMENT '期望周收益（元）'")
    private Integer expectWeeksEarnings;

    /**
     * 期望月收益（元）
     */
    @Column(name = "expectMonthsEarnings", columnDefinition = "INT(11)   COMMENT '期望月收益（元）'")
    private Integer expectMonthsEarnings;

    /**
     * 期望年收益（元）
     */
    @Column(name = "expectYearsEarnings", columnDefinition = "INT(11)  COMMENT '期望年收益（元）'")
    private Integer expectYearsEarnings;
    /**
     * 审核人
     */
    @Column(name = "audit", columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String audit;
    /**
     * 审核状态
     */
    @Column(name = "auditStatus", columnDefinition = "VARCHAR(255)   COMMENT '审核状态'")
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

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public String getExpectedSalaryIncrease() {
        return expectedSalaryIncrease;
    }

    public void setExpectedSalaryIncrease(String expectedSalaryIncrease) {
        this.expectedSalaryIncrease = expectedSalaryIncrease;
    }

    public LocalDate getPlanningDate() {
        return planningDate;
    }

    public void setPlanningDate(LocalDate planningDate) {
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

    public LocalDate getExpectedCompletionTime() {
        return expectedCompletionTime;
    }

    public void setExpectedCompletionTime(LocalDate expectedCompletionTime) {
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