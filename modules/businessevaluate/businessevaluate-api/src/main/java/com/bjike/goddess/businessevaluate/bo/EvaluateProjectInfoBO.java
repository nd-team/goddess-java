package com.bjike.goddess.businessevaluate.bo;

import com.bjike.goddess.businessevaluate.enums.CooperateWay;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 项目基本信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EvaluateProjectInfoBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 合作方式
     */
    private CooperateWay cooperateWay;

    /**
     * 工作量
     */
    private String workload;

    /**
     * 成本
     */
    private Double cost;

    /**
     * 总金额
     */
    private Double totalAmount;

    /**
     * 税金
     */
    private Double taxes;

    /**
     * 管理费
     */
    private Double manageCost;

    /**
     * 工期开始时间
     */
    private String startTime;

    /**
     * 工期结束时间
     */
    private String endTime;

    /**
     * 工期经历时间
     */
    private String experienceTime;


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
}