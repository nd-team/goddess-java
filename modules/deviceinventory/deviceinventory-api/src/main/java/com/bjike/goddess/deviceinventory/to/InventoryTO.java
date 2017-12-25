package com.bjike.goddess.deviceinventory.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 盘点
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 盘点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InventoryTO extends BaseTO {

    /**
     * 盘点时间
     */
    @NotBlank(groups = {EDIT.class}, message = "盘点时间不能为空")
    private String inventoryTime;

    /**
     * 编号
     */
    private String stockEncoding;

    /**
     * 地区
     */
    private String storageArea;

    /**
     * 部门
     */
    private String projectGroup;

    /**
     * 物资名称
     */
    private String materialName;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private Double unitPrice;

    /**
     * 购买日期
     */
    private String purchaseDate;

    /**
     * 保修期(月)
     */
    private Integer warrantyExpire;

    /**
     * 账面数
     */
    @NotNull(groups = {EDIT.class}, message = "账面数不能为空")
    private Integer quantity;

    /**
     * 盘点数
     */
    @NotNull(groups = {EDIT.class}, message = "盘点数不能为空")
    private Integer inventoryNum;

    /**
     * 差异说明
     */
    @NotBlank(groups = {EDIT.class}, message = "差异说明不能为空")
    private String differThat;

    /**
     * 责任人
     */
    @NotBlank(groups = {EDIT.class}, message = "责任人不能为空")
    private String responsible;

    /**
     * 使用状态
     */
    @NotBlank(groups = {EDIT.class}, message = "使用状态不能为空")
    private String state;

    /**
     * 盘点人
     */
    @NotBlank(groups = {EDIT.class}, message = "盘点人不能为空")
    private String inventoryMan;

    /**
     * 备注
     */
    @NotBlank(groups = {EDIT.class}, message = "备注不能为空")
    private String note;


    public String getInventoryTime() {
        return inventoryTime;
    }

    public void setInventoryTime(String inventoryTime) {
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

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
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
}