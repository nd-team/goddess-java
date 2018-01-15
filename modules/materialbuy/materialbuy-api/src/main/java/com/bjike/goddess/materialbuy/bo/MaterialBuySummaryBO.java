package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 物资购买汇总BO
 * @Author: [chenjunhao]
 * @Date: [2018-01-03 16:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MaterialBuySummaryBO extends BaseBO {


    /**
     * 地区
     * */
    private String area;

    /**
     * 项目组
     */
    private String projectTeam;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 型号
     */
    private String model;

    /**
     * 物资需求申请数
     * 临时物资需求
     */
    private String applyQuantity;

    /**
     * 不满足库存数
     * 临时物资需求
     */
    private String ifStockSatisfy;

    /**
     * 财务部审核通过数
     * 临时物资需求
     */
    private String ifFinanceAudit;

    /**
     * 物资购买数量
     */
    private Integer buyQuantity;

    /**
     * 总额
     */
    private Double totalSum;


    /**
     * 是否为代写借支
     */
    private String ifReplaceBorrow;

    /**
     * 是否付款
     */
    private String ifPayment;

    /**
     * 是否到货
     */
    private String ifArrival;

    /**
     * 是否需商务部审核
     */
    private String ifCommerceAudit;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getApplyQuantity() {
        return applyQuantity;
    }

    public void setApplyQuantity(String applyQuantity) {
        this.applyQuantity = applyQuantity;
    }

    public String getIfStockSatisfy() {
        return ifStockSatisfy;
    }

    public void setIfStockSatisfy(String ifStockSatisfy) {
        this.ifStockSatisfy = ifStockSatisfy;
    }

    public String getIfFinanceAudit() {
        return ifFinanceAudit;
    }

    public void setIfFinanceAudit(String ifFinanceAudit) {
        this.ifFinanceAudit = ifFinanceAudit;
    }

    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Integer buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public String getIfReplaceBorrow() {
        return ifReplaceBorrow;
    }

    public void setIfReplaceBorrow(String ifReplaceBorrow) {
        this.ifReplaceBorrow = ifReplaceBorrow;
    }

    public String getIfPayment() {
        return ifPayment;
    }

    public void setIfPayment(String ifPayment) {
        this.ifPayment = ifPayment;
    }

    public String getIfArrival() {
        return ifArrival;
    }

    public void setIfArrival(String ifArrival) {
        this.ifArrival = ifArrival;
    }

    public String getIfCommerceAudit() {
        return ifCommerceAudit;
    }

    public void setIfCommerceAudit(String ifCommerceAudit) {
        this.ifCommerceAudit = ifCommerceAudit;
    }
}
