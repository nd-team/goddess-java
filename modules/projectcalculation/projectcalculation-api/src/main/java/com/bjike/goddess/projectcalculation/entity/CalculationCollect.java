package com.bjike.goddess.projectcalculation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 项目测算管理汇总
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-12 10:27 ]
 * @Description: [ 项目测算管理汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectcalculation_calculationcollect")
public class CalculationCollect extends BaseEntity {

    /**
     * 业务类型
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 待测项目数
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '待测项目数'")
    private Integer pendingNum;

    /**
     * 完成项目数
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '完成项目数'")
    private Integer finisNum;

    /**
     * 未完成项目数
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '未完成项目数'")
    private Integer incomplete;

    /**
     * 测算通过数
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '测算通过数'")
    private Integer calculationPass;

    /**
     * 合同金额
     */
    @Column(name = "contractAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合同金额'")
    private Double contractAmount;

    /**
     * 规模数量
     */
    @Column(name = "scale", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '规模数量'")
    private Double scale;

    /**
     * 预估金额
     */
    @Column(name = "estimatedAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估金额'")
    private Double estimatedAmount;

    /**
     * 预估利润
     */
    @Column(name = "forecastProfit", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估利润'")
    private Double forecastProfit;

    /**
     * 预估总成本
     */
    @Column(name = "estimateTheTotalCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预估总成本'")
    private Double estimateTheTotalCost;

    /**
     * 技能人员是否能储备/调配项目数
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能人员是否能储备/调配项目数'")
    private Integer artisanAllocateNum;

    /**
     * 管理人员是否能储备/调配项目数
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '管理人员是否能储备/调配项目数'")
    private Integer administratorAllocateNum;

    /**
     * 设备调配/库存是否满足项目数
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '设备调配/库存是否满足项目数'")
    private Integer equipmentAllocateNum;

    /**
     * 车辆是否有可调配资源或者储备资源项目数
     */
    @Column(name = "vehicleAllocateNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '车辆是否有可调配资源或者储备资源项目数'")
    private Integer vehicleAllocateNum;

    /**
     * 项目界面完成数
     */
    @Column(name = "finishProgressNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目界面完成数'")
    private Integer finishProgressNum;

    /**
     * 确认外包数量
     */
    @Column(name = "epibolyNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '确认外包数量'")
    private Integer epibolyNum;

    /**
     * 不外包数量
     */
    @Column(name = "notEpibolyNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '不外包数量'")
    private Integer notEpibolyNum;


    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getPendingNum() {
        return pendingNum;
    }

    public void setPendingNum(Integer pendingNum) {
        this.pendingNum = pendingNum;
    }

    public Integer getFinisNum() {
        return finisNum;
    }

    public void setFinisNum(Integer finisNum) {
        this.finisNum = finisNum;
    }

    public Integer getIncomplete() {
        return incomplete;
    }

    public void setIncomplete(Integer incomplete) {
        this.incomplete = incomplete;
    }

    public Integer getCalculationPass() {
        return calculationPass;
    }

    public void setCalculationPass(Integer calculationPass) {
        this.calculationPass = calculationPass;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(Double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public Double getForecastProfit() {
        return forecastProfit;
    }

    public void setForecastProfit(Double forecastProfit) {
        this.forecastProfit = forecastProfit;
    }

    public Double getEstimateTheTotalCost() {
        return estimateTheTotalCost;
    }

    public void setEstimateTheTotalCost(Double estimateTheTotalCost) {
        this.estimateTheTotalCost = estimateTheTotalCost;
    }

    public Integer getArtisanAllocateNum() {
        return artisanAllocateNum;
    }

    public void setArtisanAllocateNum(Integer artisanAllocateNum) {
        this.artisanAllocateNum = artisanAllocateNum;
    }

    public Integer getAdministratorAllocateNum() {
        return administratorAllocateNum;
    }

    public void setAdministratorAllocateNum(Integer administratorAllocateNum) {
        this.administratorAllocateNum = administratorAllocateNum;
    }

    public Integer getEquipmentAllocateNum() {
        return equipmentAllocateNum;
    }

    public void setEquipmentAllocateNum(Integer equipmentAllocateNum) {
        this.equipmentAllocateNum = equipmentAllocateNum;
    }

    public Integer getVehicleAllocateNum() {
        return vehicleAllocateNum;
    }

    public void setVehicleAllocateNum(Integer vehicleAllocateNum) {
        this.vehicleAllocateNum = vehicleAllocateNum;
    }

    public Integer getFinishProgressNum() {
        return finishProgressNum;
    }

    public void setFinishProgressNum(Integer finishProgressNum) {
        this.finishProgressNum = finishProgressNum;
    }

    public Integer getEpibolyNum() {
        return epibolyNum;
    }

    public void setEpibolyNum(Integer epibolyNum) {
        this.epibolyNum = epibolyNum;
    }

    public Integer getNotEpibolyNum() {
        return notEpibolyNum;
    }

    public void setNotEpibolyNum(Integer notEpibolyNum) {
        this.notEpibolyNum = notEpibolyNum;
    }
}