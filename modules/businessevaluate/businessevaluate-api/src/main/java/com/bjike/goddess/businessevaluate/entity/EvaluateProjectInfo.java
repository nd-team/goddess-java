package com.bjike.goddess.businessevaluate.entity;

import com.bjike.goddess.businessevaluate.enums.CooperateWay;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 项目基本信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_evaluateprojectinfo")
public class EvaluateProjectInfo extends BaseEntity {

    /**
     * 数据状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '数据状态'", nullable = false, insertable = false)
    private Status status;
    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目名称
     */
    @Column(name = "project", nullable = false,  columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 合作方式
     */
    @Column(name = "cooperateWay", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '合作方式'")
    private CooperateWay cooperateWay;

    /**
     * 工作量
     */
    @Column(name = "workload", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工作量'")
    private String workload;

    /**
     * 成本
     */
    @Column(name = "cost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '成本'")
    private Double cost;

    /**
     * 总金额
     */
    @Column(name = "totalAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总金额'")
    private Double totalAmount;

    /**
     * 税金
     */
    @Column(name = "taxes", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税金'")
    private Double taxes;

    /**
     * 管理费
     */
    @Column(name = "manageCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理费'")
    private Double manageCost;

    /**
     * 工期开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '工期开始时间'")
    private LocalDate startTime;

    /**
     * 工期结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '工期结束时间'")
    private LocalDate endTime;

    /**
     * 工期经历时间
     */
    @Column(name = "experienceTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工期经历时间'")
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(String experienceTime) {
        this.experienceTime = experienceTime;
    }
}