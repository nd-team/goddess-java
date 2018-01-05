package com.bjike.goddess.budget.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 项目收入周
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectWeekImportTemple extends BaseBO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String arrival;

    /**
     * 所属项目
     */
    @ExcelHeader(name = "所属项目", notNull = true)
    private String project;

    /**
     * 年份
     */
    @ExcelHeader(name = "年份", notNull = true)
    private Integer year;

    /**
     * 月份
     */
    @ExcelHeader(name = "月份", notNull = true)
    private Integer month;

    /**
     * 周数
     */
    @ExcelHeader(name = "周数", notNull = true)
    private Integer week;

    /**
     * 目标任务量
     */
    @ExcelHeader(name = "目标任务量", notNull = true)
    private Integer targetWork;

    /**
     * 实际完工量
     */
    @ExcelHeader(name = "实际完工量", notNull = true)
    private Integer actualWork;


    /**
     * 参考单价
     */
    @ExcelHeader(name = "参考单价", notNull = true)
    private Double price;

    /**
     * 目标收入
     */
    @ExcelHeader(name = "目标收入", notNull = true)
    private Double targetIncome;

    /**
     * 计划收入
     */
    @ExcelHeader(name = "计划收入", notNull = true)
    private Double planIncome;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称", notNull = true)
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}