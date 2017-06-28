package com.bjike.goddess.deviceinventory.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 盘点记录导出excel
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-28 14:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InventoryRecordExcel extends BaseTO {
    /**
     * 盘点时间
     */
    @ExcelHeader(name = "盘点时间", notNull = true)
    private LocalDate inventoryTime;

    /**
     * 编号
     */
    @ExcelHeader(name = "编号", notNull = true)
    private String stockEncoding;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String storageArea;

    /**
     * 部门
     */
    @ExcelHeader(name = "部门", notNull = true)
    private String projectGroup;

    /**
     * 物资名称
     */
    @ExcelHeader(name = "物资名称", notNull = true)
    private String materialName;

    /**
     * 单位
     */
    @ExcelHeader(name = "单位", notNull = true)
    private String unit;

    /**
     * 单价
     */
    @ExcelHeader(name = "单价", notNull = true)
    private Double unitPrice;

    /**
     * 购买日期
     */
    @ExcelHeader(name = "购买日期", notNull = true)
    private LocalDate purchaseDate;

    /**
     * 保修期(月)
     */
    @ExcelHeader(name = "保修期(月)", notNull = true)
    private Integer warrantyExpire;

    /**
     * 账面数
     */
    @ExcelHeader(name = "账面数", notNull = true)
    private Integer quantity;

    /**
     * 盘点数
     */
    @ExcelHeader(name = "盘点数", notNull = true)
    private Integer inventoryNum;

    /**
     * 差异说明
     */
    @ExcelHeader(name = "差异说明", notNull = true)
    private String differThat;

    /**
     * 责任人
     */
    @ExcelHeader(name = "责任人", notNull = true)
    private String responsible;

    /**
     * 使用状态
     */
    @ExcelHeader(name = "使用状态", notNull = true)
    private String state;

    /**
     * 盘点人
     */
    @ExcelHeader(name = "盘点人", notNull = true)
    private String inventoryMan;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = true)
    private String note;

    /**
     * 盈亏数
     */
    @ExcelHeader(name = "盈亏数", notNull = true)
    private Integer profitLoss;

    /**
     * 盈亏合计
     */
    @ExcelHeader(name = "盈亏合计", notNull = true)
    private Double profitLossCount;

    public LocalDate getInventoryTime() {
        return inventoryTime;
    }

    public void setInventoryTime(LocalDate inventoryTime) {
        this.inventoryTime = inventoryTime;
    }

    public String getStockEncoding() {
        return stockEncoding;
    }

    public void setStockEncoding(String stockEncoding) {
        this.stockEncoding = stockEncoding;
    }

    public String getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(String storageArea) {
        this.storageArea = storageArea;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getWarrantyExpire() {
        return warrantyExpire;
    }

    public void setWarrantyExpire(Integer warrantyExpire) {
        this.warrantyExpire = warrantyExpire;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getDifferThat() {
        return differThat;
    }

    public void setDifferThat(String differThat) {
        this.differThat = differThat;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInventoryMan() {
        return inventoryMan;
    }

    public void setInventoryMan(String inventoryMan) {
        this.inventoryMan = inventoryMan;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(Integer profitLoss) {
        this.profitLoss = profitLoss;
    }

    public Double getProfitLossCount() {
        return profitLossCount;
    }

    public void setProfitLossCount(Double profitLossCount) {
        this.profitLossCount = profitLossCount;
    }
}
