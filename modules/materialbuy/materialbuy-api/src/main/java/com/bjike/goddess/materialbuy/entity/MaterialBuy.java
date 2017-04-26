package com.bjike.goddess.materialbuy.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.materialbuy.type.AuditState;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 物资购买
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialbuy_materialbuy")
public class MaterialBuy extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectTeam", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目组'")
    private String projectTeam;

    /**
     * 申购人
     */
    @Column(name = "requisitioner", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '申购人'")
    private String requisitioner;

    /**
     * 申购日期
     */
    @Column(name = "subscribeDate", nullable = false, columnDefinition = "DATE COMMENT '申购日期'")
    private LocalDate subscribeDate;

    /**
     * 设备类型
     */
    @Column(name = "deviceType", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '设备类型'")
    private String deviceType;

    /**
     * 设备名称
     */
    @Column(name = "deviceName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '设备名称'")
    private String deviceName;

    /**
     * 型号
     */
    @Column(name = "model", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '型号'")
    private String model;

    /**
     * 数量
     */
    @Column(name = "quantity", nullable = false, columnDefinition = "INT(11) COMMENT '数量'")
    private Integer quantity;

    /**
     * 单位
     */
    @Column(name = "unit", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '单位'")
    private String unit;

    /**
     * 购买原因
     */
    @Column(name = "buyReason", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '购买原因'")
    private String buyReason;

    /**
     * 单价
     */
    @Column(name = "unitPrice", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '单价'")
    private Double unitPrice;

    /**
     * 总额
     */
    @Column(name = "totalSum", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '总额'")
    private Double totalSum;

    /**
     * 途径
     */
    @Column(name = "approach", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '途径'")
    private String approach;

    /**
     * 购买网址/店铺
     */
    @Column(name = "buyAddress", columnDefinition = "VARCHAR(255) COMMENT '购买网址/店铺'")
    private String buyAddress;

    /**
     * 收件地址
     */
    @Column(name = "deliveryAddress", columnDefinition = "VARCHAR(255) COMMENT '收件地址'")
    private String deliveryAddress;

    /**
     * 收件人
     */
    @Column(name = "receiver", columnDefinition = "VARCHAR(255) COMMENT '收件人'")
    private String receiver;

    /**
     * 收件人联系方式
     */
    @Column(name = "receiverPhone", columnDefinition = "VARCHAR(255) COMMENT '收件人联系方式'")
    private String receiverPhone;

    /**
     * 借款人
     */
    @Column(name = "borrower", columnDefinition = "VARCHAR(255) COMMENT '借款人'")
    private String borrower;

    /**
     * 借款方式
     */
    @Column(name = "borrowManner", columnDefinition = "VARCHAR(255) COMMENT '借款方式'")
    private String borrowManner;

    /**
     * 是否为代写借支
     */
    @Column(name = "ifReplaceBorrow", columnDefinition = "TINYINT(1) DEFAULT 0  COMMENT '是否为代写借支'")
    private Boolean ifReplaceBorrow;

    /**
     * 预计借款时间
     */
    @Column(name = "planBorrowTime", columnDefinition = "DATETIME COMMENT '预计借款时间'")
    private LocalDateTime planBorrowTime;

    /**
     * 是否付款
     */
    @Column(name = "ifPayment", columnDefinition = "TINYINT(1) DEFAULT 0  COMMENT '是否付款'")
    private Boolean ifPayment;

    /**
     * 快递单号
     */
    @Column(name = "expressNumber", columnDefinition = "VARCHAR(255) COMMENT '快递单号'")
    private String expressNumber;

    /**
     * 备注(款打到谁的卡)
     */
    @Column(name = "comment", columnDefinition = "VARCHAR(255) COMMENT '备注(款打到谁的卡)'")
    private String comment;

    /**
     * 地区审核人
     */
    @Column(name = "areaAuditor", columnDefinition = "VARCHAR(255) COMMENT '地区审核人'")
    private String areaAuditor;

    /**
     * 审核状态
     */
    @Column(name = "auditState", columnDefinition = "TINYINT(2) COMMENT '审核状态'")
    private AuditState auditState;

    /**
     * 审核意见
     */
    @Column(name = "auditOpinion", columnDefinition = "VARCHAR(255) COMMENT '审核意见'")
    private String auditOpinion;


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

    public String getRequisitioner() {
        return requisitioner;
    }

    public void setRequisitioner(String requisitioner) {
        this.requisitioner = requisitioner;
    }

    public LocalDate getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(LocalDate subscribeDate) {
        this.subscribeDate = subscribeDate;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBuyReason() {
        return buyReason;
    }

    public void setBuyReason(String buyReason) {
        this.buyReason = buyReason;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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

    public String getBuyAddress() {
        return buyAddress;
    }

    public void setBuyAddress(String buyAddress) {
        this.buyAddress = buyAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrowManner() {
        return borrowManner;
    }

    public void setBorrowManner(String borrowManner) {
        this.borrowManner = borrowManner;
    }

    public Boolean getIfReplaceBorrow() {
        return ifReplaceBorrow;
    }

    public void setIfReplaceBorrow(Boolean ifReplaceBorrow) {
        this.ifReplaceBorrow = ifReplaceBorrow;
    }

    public LocalDateTime getPlanBorrowTime() {
        return planBorrowTime;
    }

    public void setPlanBorrowTime(LocalDateTime planBorrowTime) {
        this.planBorrowTime = planBorrowTime;
    }

    public Boolean getIfPayment() {
        return ifPayment;
    }

    public void setIfPayment(Boolean ifPayment) {
        this.ifPayment = ifPayment;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAreaAuditor() {
        return areaAuditor;
    }

    public void setAreaAuditor(String areaAuditor) {
        this.areaAuditor = areaAuditor;
    }

    public AuditState getAuditState() {
        return auditState;
    }

    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
}