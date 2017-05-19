package com.bjike.goddess.housepay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.housepay.enums.PayStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 已付款记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:13 ]
 * @Description: [ 已付款记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "housepay_payrecord")
public class PayRecord extends BaseEntity {

    /**
     * 缴费时间
     */
    @Column(name = "payTime", columnDefinition = "DATE   COMMENT '缴费时间'")
    private LocalDate payTime;

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目
     */
    @Column(name = "project", columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String project;

    /**
     * 房租地址
     */
    @Column(name = "rentAddress",  columnDefinition = "VARCHAR(255)   COMMENT '房租地址'")
    private String rentAddress;

    /**
     * 租金
     */
    @Column(name = "rent", columnDefinition = "DECIMAL(10,2)   COMMENT '租金'")
    private Double rent;

    /**
     * 水费
     */
    @Column(name = "water",  columnDefinition = "DECIMAL(10,2)   COMMENT '水费'")
    private Double water;

    /**
     * 电费
     */
    @Column(name = "energy",  columnDefinition = "DECIMAL(10,2)   COMMENT '电费'")
    private Double energy;

    /**
     * 管理费
     */
    @Column(name = "fee",  columnDefinition = "DECIMAL(10,2)   COMMENT '管理费'")
    private Double fee;

    /**
     * 其他费用
     */
    @Column(name = "otherFee",  columnDefinition = "DECIMAL(10,2)   COMMENT '其他费用'")
    private Double otherFee;

    /**
     * 合计（租金+水费+电费+管理费+其他费用）
     */
    @Column(name = "total",  columnDefinition = "DECIMAL(10,2)   COMMENT '合计（租金+水费+电费+管理费+其他费用）'")
    private Double total;

    /**
     * 房东姓名
     */
    @Column(name = "landlord", columnDefinition = "VARCHAR(255)   COMMENT '房东姓名'")
    private String landlord;

    /**
     * 联系电话
     */
    @Column(name = "contact",  columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String contact;

    /**
     * 交租确认
     */
    @Column(name = "taxesConfirm", columnDefinition = "VARCHAR(255)   COMMENT '交租确认'")
    private String taxesConfirm;

    /**
     * 是否付款(是/否)
     */
    @Column(name = "pay",  columnDefinition = "TINYINT(2)   COMMENT '是否付款(是/否)'")
    private PayStatus pay;


    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public LocalDate getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDate payTime) {
        this.payTime = payTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRentAddress() {
        return rentAddress;
    }

    public void setRentAddress(String rentAddress) {
        this.rentAddress = rentAddress;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTaxesConfirm() {
        return taxesConfirm;
    }

    public void setTaxesConfirm(String taxesConfirm) {
        this.taxesConfirm = taxesConfirm;
    }

    public PayStatus getPay() {
        return pay;
    }

    public void setPay(PayStatus pay) {
        this.pay = pay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}