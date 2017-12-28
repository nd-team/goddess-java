package com.bjike.goddess.equipmentprepared.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 等待付款导出excel
 * @Author: [chenjunhao]
 * @Date: [2017-06-28 10:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WaitPayExcel extends BaseTO{
    /**
     * 申购时间
     */
    @ExcelHeader(name = "申购时间", notNull = true)
    private LocalDate subscribeDate;

    /**
     * 申购人
     */
    @ExcelHeader(name = "申购人", notNull = true)
    private String requisitioner;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 部门
     */
    @ExcelHeader(name = "部门", notNull = true)
    private String projectTeam;

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
     * 购买单价
     */
    @ExcelHeader(name = "购买单价", notNull = true)
    private Double unitPrice;

    /**
     * 数量
     */
    @ExcelHeader(name = "数量", notNull = true)
    private Integer quantity;

    /**
     * 合计金额
     */
    @ExcelHeader(name = "合计金额", notNull = true)
    private Double totalSum;

    /**
     * 购买途径
     */
    @ExcelHeader(name = "购买途径", notNull = true)
    private String approach;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = true)
    private String comment;

    /**
     * 是否付款
     */
    @ExcelHeader(name = "是否付款", notNull = true)
    private Boolean ifPayment;

    /**
     * 付款时间
     */
    @ExcelHeader(name = "付款时间", notNull = true)
    private LocalDateTime payTime;

    /**
     * 付款人
     */
    @ExcelHeader(name = "付款人", notNull = true)
    private String payMan;

    public LocalDate getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(LocalDate subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public String getRequisitioner() {
        return requisitioner;
    }

    public void setRequisitioner(String requisitioner) {
        this.requisitioner = requisitioner;
    }

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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public String getApproach() {
        return approach;
    }

    public void setApproach(String approach) {
        this.approach = approach;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIfPayment() {
        return ifPayment;
    }

    public void setIfPayment(Boolean ifPayment) {
        this.ifPayment = ifPayment;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public String getPayMan() {
        return payMan;
    }

    public void setPayMan(String payMan) {
        this.payMan = payMan;
    }
}
