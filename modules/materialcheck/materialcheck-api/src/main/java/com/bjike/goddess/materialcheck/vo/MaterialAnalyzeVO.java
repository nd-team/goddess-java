package com.bjike.goddess.materialcheck.vo;

import com.bjike.goddess.materialcheck.type.InventoryType;

/**
 * 物资分析表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:18 ]
 * @Description: [ 物资分析表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MaterialAnalyzeVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/地区
     */
    private String projectGroup;

    /**
     * 类型
     */
    private String type;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 周转率
     */
    private String turnoverRatio;

    /**
     * 闲置率
     */
    private String vacancyRate;

    /**
     * 库存差
     */
    private Integer stockSubtract;

    /**
     * 盈亏率
     */
    private String balanceRate;

    /**
     * 盘点类型
     */
    private InventoryType inventoryType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getTurnoverRatio() {
        return turnoverRatio;
    }

    public void setTurnoverRatio(String turnoverRatio) {
        this.turnoverRatio = turnoverRatio;
    }

    public String getVacancyRate() {
        return vacancyRate;
    }

    public void setVacancyRate(String vacancyRate) {
        this.vacancyRate = vacancyRate;
    }

    public Integer getStockSubtract() {
        return stockSubtract;
    }

    public void setStockSubtract(Integer stockSubtract) {
        this.stockSubtract = stockSubtract;
    }

    public String getBalanceRate() {
        return balanceRate;
    }

    public void setBalanceRate(String balanceRate) {
        this.balanceRate = balanceRate;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }
}