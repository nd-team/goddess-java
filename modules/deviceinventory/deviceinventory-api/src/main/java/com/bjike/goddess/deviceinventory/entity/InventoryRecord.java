package com.bjike.goddess.deviceinventory.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 盘点记录
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 盘点记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "deviceinventory_inventoryrecord")
public class InventoryRecord extends BaseEntity {

    /**
     * 盘点时间
     */
    @Column(name = "inventoryTime", nullable = false, columnDefinition = "DATE   COMMENT '盘点时间'")
    private LocalDate inventoryTime;

    /**
     * 编号
     */
    @Column(name = "stockEncoding", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '编号'")
    private String stockEncoding;

    /**
     * 地区
     */
    @Column(name = "storageArea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String storageArea;

    /**
     * 部门
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String projectGroup;

    /**
     * 物资名称
     */
    @Column(name = "materialName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '物资名称'")
    private String materialName;

    /**
     * 单位
     */
    @Column(name = "unit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '单位'")
    private String unit;

    /**
     * 单价
     */
    @Column(name = "unitPrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '单价'")
    private Double unitPrice;

    /**
     * 购买日期
     */
    @Column(name = "purchaseDate", nullable = false, columnDefinition = "DATE   COMMENT '购买日期'")
    private LocalDate purchaseDate;

    /**
     * 保修期(月)
     */
    @Column(name = "warrantyExpire", nullable = false, columnDefinition = "INT(11)   COMMENT '保修期(月)'")
    private Integer warrantyExpire;

    /**
     * 账面数
     */
    @Column(name = "quantity", nullable = false, columnDefinition = "INT(11)   COMMENT '账面数'")
    private Integer quantity;

    /**
     * 盘点数
     */
    @Column(name = "inventoryNum", nullable = false, columnDefinition = "INT(11)   COMMENT '盘点数'")
    private Integer inventoryNum;

    /**
     * 差异说明
     */
    @Column(name = "differThat", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '差异说明'")
    private String differThat;

    /**
     * 责任人
     */
    @Column(name = "responsible", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '责任人'")
    private String responsible;

    /**
     * 使用状态
     */
    @Column(name = "state", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '使用状态'")
    private String state;

    /**
     * 盘点人
     */
    @Column(name = "inventoryMan", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '盘点人'")
    private String inventoryMan;

    /**
     * 备注
     */
    @Column(name = "note", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String note;

    /**
     * 盈亏数
     */
    @Column(name = "profitLoss", nullable = false, columnDefinition = "INT(11)   COMMENT '盈亏数'")
    private Integer profitLoss;

    /**
     * 盈亏合计
     */
    @Column(name = "profitLossCount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '盈亏合计'")
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