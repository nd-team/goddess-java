package com.bjike.goddess.projectcost.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目成本控制
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目成本控制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostControlTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 年份
     */
    @NotNull(message = "年份不能为空", groups = {ADD.class, EDIT.class})
    private Integer year;

    /**
     * 月份
     */
    @NotNull(message = "月份不能为空", groups = {ADD.class, EDIT.class})
    private Integer month;

    /**
     * 目标收入
     */
    @NotNull(message = "目标收入不能为空", groups = {ADD.class, EDIT.class})
    private Double targetIncome;

    /**
     * 实际收入
     */
    private Double actualIncome;

    /**
     * 目标利润
     */
    @NotNull(message = "目标利润不能为空", groups = {ADD.class, EDIT.class})
    private Double targetProfit;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getTargetIncome() {
        return targetIncome;
    }

    public void setTargetIncome(Double targetIncome) {
        this.targetIncome = targetIncome;
    }

    public Double getActualIncome() {
        return actualIncome;
    }

    public void setActualIncome(Double actualIncome) {
        this.actualIncome = actualIncome;
    }

    public Double getTargetProfit() {
        return targetProfit;
    }

    public void setTargetProfit(Double targetProfit) {
        this.targetProfit = targetProfit;
    }

}