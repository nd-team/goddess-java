package com.bjike.goddess.businesscommission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 业务提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-26 11:38 ]
 * @Description: [ 业务提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businesscommission_quota")
public class Quota extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 内部项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String projectName;

    /**
     * 是否立项
     */
    @Column(name = "is_isBuild", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否立项'")
    private Boolean isBuild;

    /**
     * 立项时间
     */
    @Column(name = "buildTime", nullable = false, columnDefinition = "DATETIME   COMMENT '立项时间'")
    private LocalDateTime buildTime;

    /**
     * 是否完工
     */
    @Column(name = "is_isComplete", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否完工'")
    private Boolean isComplete;

    /**
     * 目标业务提成额
     */
    @Column(name = "aimAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标业务提成额'")
    private Double aimAmount;

    /**
     * 计划业务提成额
     */
    @Column(name = "planAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划业务提成额'")
    private Double planAmount;

    /**
     * 实际业务提成额
     */
    @Column(name = "actualAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际业务提成额'")
    private Double actualAmount;

    /**
     * 信息提供人
     */
    @Column(name = "informationProvide", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '信息提供人'")
    private String informationProvide;

    /**
     * 信息提供占比
     */
    @Column(name = "messageProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '信息提供占比'")
    private Double messageProportion;

    /**
     * 信息提供占额
     */
    @Column(name = "provideAccount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '信息提供占额'")
    private Double provideAccount;

    /**
     * 业务揽接人
     */
    @Column(name = "businessContracting", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务揽接人'")
    private String businessContracting;

    /**
     * 业务揽接占比
     */
    @Column(name = "businessProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务揽接占比'")
    private Double businessProportion;

    /**
     * 业务揽接占额
     */
    @Column(name = "contractAccount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务揽接占额'")
    private Double contractAccount;

    /**
     * 业务洽谈人
     */
    @Column(name = "businessNegotiation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务洽谈人'")
    private String businessNegotiation;

    /**
     * 业务洽谈占比
     */
    @Column(name = "talkProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务洽谈占比'")
    private Double talkProportion;

    /**
     * 业务洽谈占额
     */
    @Column(name = "negotiationAccount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务洽谈占额'")
    private Double negotiationAccount;

    /**
     * 维护人
     */
    @Column(name = "maintenance", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '维护人'")
    private String maintenance;

    /**
     * 维护占比
     */
    @Column(name = "maintainProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '维护占比'")
    private Double maintainProportion;

    /**
     * 维护占额
     */
    @Column(name = "maintenanceAccount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '维护占额'")
    private Double maintenanceAccount;

    /**
     * 剩余分配比例
     */
    @Column(name = "surplusProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余分配比例'")
    private Double surplusProportion;

    /**
     * 剩余占额
     */
    @Column(name = "remainingAccount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余占额'")
    private Double remainingAccount;

    /**
     * 总比例
     */
    @Column(name = "ratio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总比例'")
    private Double ratio;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Boolean getIsBuild() {
        return isBuild;
    }

    public void setIsBuild(Boolean isBuild) {
        this.isBuild = isBuild;
    }

    public LocalDateTime getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(LocalDateTime buildTime) {
        this.buildTime = buildTime;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Double getAimAmount() {
        return aimAmount;
    }

    public void setAimAmount(Double aimAmount) {
        this.aimAmount = aimAmount;
    }

    public Double getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(Double planAmount) {
        this.planAmount = planAmount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getInformationProvide() {
        return informationProvide;
    }

    public void setInformationProvide(String informationProvide) {
        this.informationProvide = informationProvide;
    }

    public Double getMessageProportion() {
        return messageProportion;
    }

    public void setMessageProportion(Double messageProportion) {
        this.messageProportion = messageProportion;
    }

    public Double getProvideAccount() {
        return provideAccount;
    }

    public void setProvideAccount(Double provideAccount) {
        this.provideAccount = provideAccount;
    }

    public String getBusinessContracting() {
        return businessContracting;
    }

    public void setBusinessContracting(String businessContracting) {
        this.businessContracting = businessContracting;
    }

    public Double getBusinessProportion() {
        return businessProportion;
    }

    public void setBusinessProportion(Double businessProportion) {
        this.businessProportion = businessProportion;
    }

    public Double getContractAccount() {
        return contractAccount;
    }

    public void setContractAccount(Double contractAccount) {
        this.contractAccount = contractAccount;
    }

    public String getBusinessNegotiation() {
        return businessNegotiation;
    }

    public void setBusinessNegotiation(String businessNegotiation) {
        this.businessNegotiation = businessNegotiation;
    }

    public Double getTalkProportion() {
        return talkProportion;
    }

    public void setTalkProportion(Double talkProportion) {
        this.talkProportion = talkProportion;
    }

    public Double getNegotiationAccount() {
        return negotiationAccount;
    }

    public void setNegotiationAccount(Double negotiationAccount) {
        this.negotiationAccount = negotiationAccount;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public Double getMaintainProportion() {
        return maintainProportion;
    }

    public void setMaintainProportion(Double maintainProportion) {
        this.maintainProportion = maintainProportion;
    }

    public Double getMaintenanceAccount() {
        return maintenanceAccount;
    }

    public void setMaintenanceAccount(Double maintenanceAccount) {
        this.maintenanceAccount = maintenanceAccount;
    }

    public Double getSurplusProportion() {
        return surplusProportion;
    }

    public void setSurplusProportion(Double surplusProportion) {
        this.surplusProportion = surplusProportion;
    }

    public Double getRemainingAccount() {
        return remainingAccount;
    }

    public void setRemainingAccount(Double remainingAccount) {
        this.remainingAccount = remainingAccount;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getBuild() {
        return isBuild;
    }

    public void setBuild(Boolean build) {
        isBuild = build;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}