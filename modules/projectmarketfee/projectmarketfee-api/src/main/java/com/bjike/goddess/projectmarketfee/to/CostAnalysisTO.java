package com.bjike.goddess.projectmarketfee.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 费用效益分析
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:35 ]
 * @Description: [ 费用效益分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostAnalysisTO extends BaseTO {

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String project;

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String arrival;

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
    @Max(value = 12, groups = {ADD.class, EDIT.class}, message = "月份必须大于等于12")
    private Integer month;

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 预计收入
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "预计收入不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, inclusive = false, message = "预计收入必须大于0")
    private Double expectedIncome;

    /**
     * 预计市场费
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "预计市场费不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, inclusive = false, message = "预计市场费必须大于0")
    private Double expectedMarketCost;

    /**
     * 等级评定
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "等级评定不能为空")
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Double getExpectedMarketCost() {
        return expectedMarketCost;
    }

    public void setExpectedMarketCost(Double expectedMarketCost) {
        this.expectedMarketCost = expectedMarketCost;
    }
}