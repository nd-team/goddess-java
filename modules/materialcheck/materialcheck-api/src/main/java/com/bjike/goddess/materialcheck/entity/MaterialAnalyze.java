package com.bjike.goddess.materialcheck.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.materialcheck.type.InventoryType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 物资分析
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:18 ]
 * @Description: [ 物资分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialcheck_materialanalyze")
public class MaterialAnalyze extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;

    /**
     * 项目组/地区
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目组/地区'")
    private String projectGroup;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '类型'")
    private String type;

    /**
     * 设备名称
     */
    @Column(name = "deviceName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '设备名称'")
    private String deviceName;

    /**
     * 周转率
     */
    @Column(name = "turnoverRatio", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '周转率'")
    private String turnoverRatio;

    /**
     * 闲置率
     */
    @Column(name = "vacancyRate", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '闲置率'")
    private String vacancyRate;

    /**
     * 库存差
     */
    @Column(name = "stockSubtract", nullable = false, columnDefinition = "INT(11) COMMENT '库存差'")
    private Integer stockSubtract;

    /**
     * 盈亏率
     */
    @Column(name = "balanceRate", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '盈亏率'")
    private String balanceRate;

    /**
     * 盘点类型
     */
    @Column(name = "inventoryType", nullable = false, columnDefinition = "TINYINT(2) COMMENT '盘点类型'")
    private InventoryType inventoryType;

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