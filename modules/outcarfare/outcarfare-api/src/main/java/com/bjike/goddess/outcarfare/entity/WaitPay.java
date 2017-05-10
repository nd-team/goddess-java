package com.bjike.goddess.outcarfare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 等待付款
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 03:11 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "outcarfare_waitpay")
public class WaitPay extends BaseEntity {

    /**
     * 司机名称
     */
    @Column(name = "driverName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '司机名称'")
    private String driverName;

    /**
     * 出车日期
     */
    @Column(name = "carDate", nullable = false, columnDefinition = "DATE   COMMENT '出车日期'")
    private String carDate;

    /**
     * 单号
     */
    @Column(name = "number", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '单号'")
    private String number;

    /**
     * 地区
     */
    @Column(name = "arrival", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String arrival;

    /**
     * 用车人
     */
    @Column(name = "carUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用车人'")
    private String carUser;

    /**
     * 科目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目'")
    private String project;

    /**
     * 租车单价
     */
    @Column(name = "carPrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '租车单价'")
    private Double carPrice;

    /**
     * 加班时长
     */
    @Column(name = "overtimeHour", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '加班时长'")
    private Double overtimeHour;

    /**
     * 加班单价
     */
    @Column(name = "overtimePrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '加班单价'")
    private Double overtimePrice;

    /**
     * 加班费
     */
    @Column(name = "overtimeFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '加班费'")
    private Double overtimeFee;

    /**
     * 餐费补贴
     */
    @Column(name = "allowance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '餐费补贴'")
    private Double allowance;

    /**
     * 停车/过路费
     */
    @Column(name = "parkFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '停车/过路费'")
    private Double parkFee;

    /**
     * 金额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double amount;

    /**
     * 填单人
     */
    @Column(name = "single", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '填单人'")
    private String single;

    /**
     * 是否付款
     */
    @Column(name = "isPay",columnDefinition = "TINYINT(1)  COMMENT '是否付款'")
    private boolean isPay;


    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCarDate() {
        return carDate;
    }

    public void setCarDate(String carDate) {
        this.carDate = carDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public Double getOvertimeHour() {
        return overtimeHour;
    }

    public void setOvertimeHour(Double overtimeHour) {
        this.overtimeHour = overtimeHour;
    }

    public Double getOvertimePrice() {
        return overtimePrice;
    }

    public void setOvertimePrice(Double overtimePrice) {
        this.overtimePrice = overtimePrice;
    }

    public Double getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(Double overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public Double getParkFee() {
        return parkFee;
    }

    public void setParkFee(Double parkFee) {
        this.parkFee = parkFee;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(boolean isPay) {
        this.isPay = isPay;
    }
}