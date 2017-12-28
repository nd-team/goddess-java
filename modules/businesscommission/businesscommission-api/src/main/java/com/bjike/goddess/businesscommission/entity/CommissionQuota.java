package com.bjike.goddess.businesscommission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:49 ]
 * @Description: [ 业务提成定额表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businesscommission_commissionquota")
public class CommissionQuota extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 实际业务提成总额
     */
    @Column(name = "actualAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际业务提成总额'")
    private Double actualAmount;

    /**
     * 信息提供人
     */
    @Column(name = "informationProvide", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '信息提供人'")
    private String informationProvide;

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
     * 业务洽谈占额
     */
    @Column(name = "negotiationAccount", nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '业务洽谈占额'")
    private Double negotiationAccount;

    /**
     * 维护人
     */
    @Column(name = "maintenance", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '维护人'")
    private String maintenance;

    /**
     * 维护占额
     */
    @Column(name = "maintenanceAccount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '维护占额'")
    private Double maintenanceAccount;

    /**
     * 剩余占额
     */
    @Column(name = "remainingAccount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余占额'")
    private Double remainingAccount;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Double getMaintenanceAccount() {
        return maintenanceAccount;
    }

    public void setMaintenanceAccount(Double maintenanceAccount) {
        this.maintenanceAccount = maintenanceAccount;
    }

    public Double getRemainingAccount() {
        return remainingAccount;
    }

    public void setRemainingAccount(Double remainingAccount) {
        this.remainingAccount = remainingAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}