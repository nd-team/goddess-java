package com.bjike.goddess.equipmentprepared.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 等待付款
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-13 02:27 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "equipmentprepared_waitpay")
public class WaitPay extends BaseEntity {

    /**
     * 申购时间
     */
    @Column(name = "subscribeDate", nullable = false, columnDefinition = "DATE   COMMENT '申购时间'")
    private LocalDate subscribeDate;

    /**
     * 申购人
     */
    @Column(name = "requisitioner", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '申购人'")
    private String requisitioner;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 部门
     */
    @Column(name = "projectTeam", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '部门'")
    private String projectTeam;

    /**
     * 设备名称
     */
    @Column(name = "deviceName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '设备名称'")
    private String deviceName;

    /**
     * 型号
     */
    @Column(name = "model", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '型号'")
    private String model;

    /**
     * 购买单价
     */
    @Column(name = "unitPrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '购买单价'")
    private Double unitPrice;

    /**
     * 数量
     */
    @Column(name = "quantity", nullable = false, columnDefinition = "INT(11)   COMMENT '数量'")
    private Integer quantity;

    /**
     * 合计金额
     */
    @Column(name = "totalSum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合计金额'")
    private Double totalSum;

    /**
     * 购买途径
     */
    @Column(name = "approach", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '购买途径'")
    private String approach;

    /**
     * 备注
     */
    @Column(name = "comment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String comment;

    /**
     * 是否付款
     */
    @Column(name = "is_ifPayment", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否付款'", insertable = false)
    private Boolean ifPayment;

    /**
     * 付款时间
     */
    @Column(name = "payTime", columnDefinition = "DATETIME   COMMENT '付款时间'")
    private LocalDateTime payTime;

    /**
     * 付款人
     */
    @Column(name = "payMan", columnDefinition = "VARCHAR(255)   COMMENT '付款人'")
    private String payMan;

    /**
     * 年份
     */
    @Column(name = "year", columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;

    /**
     * 月份
     */
    @Column(name = "month", columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

    /**
     * 等待付款id
     */
    @Column(name = "wait_id", unique = true, nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '等待付款id'")
    private String waitId;

    public String getWaitId() {
        return waitId;
    }

    public void setWaitId(String waitId) {
        this.waitId = waitId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getPayMan() {
        return payMan;
    }

    public void setPayMan(String payMan) {
        this.payMan = payMan;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

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
}