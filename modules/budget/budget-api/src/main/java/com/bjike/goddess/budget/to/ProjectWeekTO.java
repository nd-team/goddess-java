package com.bjike.goddess.budget.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 项目收入周
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectWeekTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String arrival;

    /**
     * 所属项目
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目不能为空")
    private String project;
    /**
     * 年份
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "年份不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "年份必须大于0")
    private Integer year;
    /**
     * 月份
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "月份不能为空")
    @Min(value = 1, groups = {ADD.class, EDIT.class}, message = "月份必须大于等于1")
    @Max(value = 12, groups = {ADD.class, EDIT.class}, message = "月份必须小于等于12")
    private Integer month;

    /**
     * 周数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "周数不能为空")
    @Min(value = 1, groups = {ADD.class, EDIT.class}, message = "周数必须大于等于1")
    @Max(value = 5, groups = {ADD.class, EDIT.class}, message = "周数必须小于等于5")
    private Integer week;

    /**
     * 目标任务量
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "目标任务量不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "目标任务量必须大于0")
    private Integer targetWork;

    /**
     * 实际完工量
     */
//    @NotNull(groups = {ADD.class, EDIT.class}, message = "实际完工量不能为空")
//    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "实际完工量必须大于0")
    private Integer actualWork;

    private Integer workDifferences;

    /**
     * 参考单价
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "单价不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, inclusive = false, message = "单价必须大于0")
    private Double price;

    /**
     * 目标收入
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "目标收入不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, inclusive = false, message = "目标收入必须大于0")
    private Double targetIncome;

    /**
     * 计划收入
     */
//    @NotNull(groups = {ADD.class, EDIT.class}, message = "计划收入不能为空")
//    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, inclusive = false, message = "计划收入必须大于0")
    private Double planIncome;

    private Double incomeDifferences;

    /**
     * 内部项目名称
     */
    private String projectName;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getTargetWork() {
        return targetWork;
    }

    public void setTargetWork(Integer targetWork) {
        this.targetWork = targetWork;
    }

    public Integer getActualWork() {
        return actualWork;
    }

    public void setActualWork(Integer actualWork) {
        this.actualWork = actualWork;
    }

    public Integer getWorkDifferences() {
        return workDifferences;
    }

    public void setWorkDifferences(Integer workDifferences) {
        this.workDifferences = workDifferences;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTargetIncome() {
        return targetIncome;
    }

    public void setTargetIncome(Double targetIncome) {
        this.targetIncome = targetIncome;
    }

    public Double getPlanIncome() {
        return planIncome;
    }

    public void setPlanIncome(Double planIncome) {
        this.planIncome = planIncome;
    }

    public Double getIncomeDifferences() {
        return incomeDifferences;
    }

    public void setIncomeDifferences(Double incomeDifferences) {
        this.incomeDifferences = incomeDifferences;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}