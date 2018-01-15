package com.bjike.goddess.materialbuy.Excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 *
 * 物资购买汇总excel
 *
 * @Author: [chenjunhao]
 * @Date: [2018-01-03 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MaterialBuyExcel  extends BaseTO {

    /**
     * 地区
     * */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组", notNull = true)
    private String projectTeam;

    /**
     * 设备类型
     */
    @ExcelHeader(name = "设备类型", notNull = true)
    private String deviceType;

    /**
     * 设备名称
     */
    @ExcelHeader(name = "设备名称", notNull = true)
    private String deviceName;

    /**
     * 型号
     */
    @ExcelHeader(name = "型号", notNull = true)
    private String model;

    /**
     * 物资需求申请数
     * 临时物资需求
     */
    @ExcelHeader(name = "物资需求申请数", notNull = true)
    private Integer applyQuantity;

    /**
     * 不满足库存数
     * 临时物资需求
     */
    @ExcelHeader(name = "不满足库存数")
    private String ifStockSatisfy;

    /**
     * 财务部审核通过数
     * 临时物资需求
     */
    @ExcelHeader(name = "财务部审核通过数")
    private Integer adoptNum;

    /**
     * 物资购买数量
     */
    @ExcelHeader(name = "物资购买数量", notNull = true)
    private Integer buyQuantity;

    /**
     * 总额
     */
    @ExcelHeader(name = "物资购买金额", notNull = true)
    private Double totalSum;


    /**
     * 是否为代写借支
     */
    @ExcelHeader(name = "代写借支数")
    private String ifReplaceBorrow;

    /**
     * 是否付款
     */
    @ExcelHeader(name = "付款数")
    private String ifPayment;

    /**
     * 是否到货
     */
    @ExcelHeader(name = "到货数")
    private String ifArrival;

    /**
     * 是否需商务部审核
     */
    @ExcelHeader(name = "需商务部审核数")
    private Boolean ifCommerceAudit;

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

    public Integer getApplyQuantity() {
        return applyQuantity;
    }

    public void setApplyQuantity(Integer applyQuantity) {
        this.applyQuantity = applyQuantity;
    }

    public String getIfStockSatisfy() {
        return ifStockSatisfy;
    }

    public void setIfStockSatisfy(String ifStockSatisfy) {
        this.ifStockSatisfy = ifStockSatisfy;
    }

    public Integer getAdoptNum() {
        return adoptNum;
    }

    public void setAdoptNum(Integer adoptNum) {
        this.adoptNum = adoptNum;
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

    public Boolean getIfCommerceAudit() {
        return ifCommerceAudit;
    }

    public void setIfCommerceAudit(Boolean ifCommerceAudit) {
        this.ifCommerceAudit = ifCommerceAudit;
    }
}
