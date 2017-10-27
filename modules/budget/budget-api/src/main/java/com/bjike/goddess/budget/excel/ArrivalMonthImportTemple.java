package com.bjike.goddess.budget.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 地区收入周导出目标
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-05-02 04:06 ]
 * @Description: [ 地区收入月导出目标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArrivalMonthImportTemple extends BaseBO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String arrival;

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
     * 单价
     */
    @ExcelHeader(name = "单价", notNull = true)
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
}