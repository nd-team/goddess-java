package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.businessevaluate.enums.CooperateWay;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目基本信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EvaluateProjectInfoTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 合作方式
     */
    @NotNull(message = "合作方式不能为空", groups = {ADD.class, EDIT.class})
    private CooperateWay cooperateWay;

    /**
     * 工作量
     */
    @NotBlank(message = "工作量不能为空", groups = {ADD.class, EDIT.class})
    private String workload;

    /**
     * 成本
     */
    @NotNull(message = "成本不能为空", groups = {ADD.class, EDIT.class})
    private Double cost;

    /**
     * 总金额
     */
    @NotNull(message = "总金额不能为空", groups = {ADD.class, EDIT.class})
    private Double totalAmount;

    /**
     * 税金
     */
    @NotNull(message = "税金不能为空", groups = {ADD.class, EDIT.class})
    private Double taxes;

    /**
     * 管理费
     */
    @NotNull(message = "管理费不能为空", groups = {ADD.class, EDIT.class})
    private Double manageCost;

    /**
     * 工期开始时间
     */
    @NotBlank(message = "工期开始时间不能为空", groups = {ADD.class, EDIT.class})
    private String startTime;

    /**
     * 工期结束时间
     */
    @NotBlank(message = "工期结束时间不能为空", groups = {ADD.class, EDIT.class})
    private String endTime;

    /**
     * 工期经历时间
     */
    private String experienceTime;

    /**
     * 年
     */
    @NotNull(message = "工期经历年数不能为空", groups = {ADD.class, EDIT.class})
    private Integer years;
    /**
     * 月
     */
    @NotNull(message = "工期经历月数不能为空", groups = {ADD.class, EDIT.class})
    private Integer months;
    /**
     * 日
     */
    @NotNull(message = "工期经历日数不能为空", groups = {ADD.class, EDIT.class})
    private Integer days;


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

    public CooperateWay getCooperateWay() {
        return cooperateWay;
    }

    public void setCooperateWay(CooperateWay cooperateWay) {
        this.cooperateWay = cooperateWay;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getManageCost() {
        return manageCost;
    }

    public void setManageCost(Double manageCost) {
        this.manageCost = manageCost;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(String experienceTime) {
        this.experienceTime = experienceTime;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}