package com.bjike.goddess.materialcheck.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.materialcheck.type.InventoryType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 物资分析
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:18 ]
 * @Description: [ 物资分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MaterialAnalyzeTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组/地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组/地区不能为空")
    private String projectGroup;

    /**
     * 类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "类型不能为空")
    private String type;

    /**
     * 设备名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "设备名称不能为空")
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
    @NotNull(groups = {ADD.class, EDIT.class}, message = "盘点类型不能为空")
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