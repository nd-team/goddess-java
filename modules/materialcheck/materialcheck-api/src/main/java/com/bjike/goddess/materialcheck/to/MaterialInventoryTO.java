package com.bjike.goddess.materialcheck.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.materialcheck.type.InventoryType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 物资盘点
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:00 ]
 * @Description: [ 物资盘点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MaterialInventoryTO extends BaseTO {

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
     * 单位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "单位不能为空")
    private String unit;

    /**
     * 账目数
     */
    private Integer accountNo;

    /**
     * 总额
     */
    private Double total;

    /**
     * 库存数
     */
    private Integer stockNo;

    /**
     * 领用数
     */
    private Integer receiveNo;

    /**
     * 维修数
     */
    private Integer repairNo;

    /**
     * 调动数
     */
    private Integer transferNo;

    /**
     * 报废数
     */
    private Integer scrapNo;

    /**
     * 盘亏数
     */
    private Integer inventoryLossNo;

    /**
     * 盘亏总额
     */
    private Double inventoryLossTotal;

    /**
     * 盘盈数
     */
    private Integer inventorySurplusNo;

    /**
     * 盘盈总额
     */
    private Double inventorySurplusTotal;

    /**
     * 经办人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "经办人不能为空")
    private String operator;

    /**
     * 经办人确认情况
     */
    private String operatorConfirm;

    /**
     * 账务模块负责人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "账务模块负责人不能为空")
    private String accountModule;

    /**
     * 账务模块核实
     */
    private String accountModuleConfirm;

    /**
     * 总经办
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "总经办不能为空")
    private String zjb;

    /**
     * 总经办审核意见
     */
    private String zjbOpinion;

    /**
     * 盘点类型
     */
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getStockNo() {
        return stockNo;
    }

    public void setStockNo(Integer stockNo) {
        this.stockNo = stockNo;
    }

    public Integer getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(Integer receiveNo) {
        this.receiveNo = receiveNo;
    }

    public Integer getRepairNo() {
        return repairNo;
    }

    public void setRepairNo(Integer repairNo) {
        this.repairNo = repairNo;
    }

    public Integer getTransferNo() {
        return transferNo;
    }

    public void setTransferNo(Integer transferNo) {
        this.transferNo = transferNo;
    }

    public Integer getScrapNo() {
        return scrapNo;
    }

    public void setScrapNo(Integer scrapNo) {
        this.scrapNo = scrapNo;
    }

    public Integer getInventoryLossNo() {
        return inventoryLossNo;
    }

    public void setInventoryLossNo(Integer inventoryLossNo) {
        this.inventoryLossNo = inventoryLossNo;
    }

    public Double getInventoryLossTotal() {
        return inventoryLossTotal;
    }

    public void setInventoryLossTotal(Double inventoryLossTotal) {
        this.inventoryLossTotal = inventoryLossTotal;
    }

    public Integer getInventorySurplusNo() {
        return inventorySurplusNo;
    }

    public void setInventorySurplusNo(Integer inventorySurplusNo) {
        this.inventorySurplusNo = inventorySurplusNo;
    }

    public Double getInventorySurplusTotal() {
        return inventorySurplusTotal;
    }

    public void setInventorySurplusTotal(Double inventorySurplusTotal) {
        this.inventorySurplusTotal = inventorySurplusTotal;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorConfirm() {
        return operatorConfirm;
    }

    public void setOperatorConfirm(String operatorConfirm) {
        this.operatorConfirm = operatorConfirm;
    }

    public String getAccountModule() {
        return accountModule;
    }

    public void setAccountModule(String accountModule) {
        this.accountModule = accountModule;
    }

    public String getAccountModuleConfirm() {
        return accountModuleConfirm;
    }

    public void setAccountModuleConfirm(String accountModuleConfirm) {
        this.accountModuleConfirm = accountModuleConfirm;
    }

    public String getZjb() {
        return zjb;
    }

    public void setZjb(String zjb) {
        this.zjb = zjb;
    }

    public String getZjbOpinion() {
        return zjbOpinion;
    }

    public void setZjbOpinion(String zjbOpinion) {
        this.zjbOpinion = zjbOpinion;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }
}