package com.bjike.goddess.businsurance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 意外险记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 05:43 ]
 * @Description: [ 意外险记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businsurance_insurerecord")
public class InsureRecord extends BaseEntity {

    /**
     * 商业险类型
     */
    @Column(name = "insureNumber",  columnDefinition = "VARCHAR(255)   COMMENT '商业险类型'")
    private String insureNumber;

    /**
     * 商业险名称
     */
    @Column(name = "addr",  columnDefinition = "VARCHAR(255)   COMMENT '商业险名称'")
    private String addr;

    /**
     * 保单生效日
     */
    @Column(name = "startDate",  columnDefinition = "DATE   COMMENT '保单生效日'")
    private LocalDate startDate;

    /**
     * 保单期满日
     */
    @Column(name = "endDate",  columnDefinition = "DATE   COMMENT '保单期满日'")
    private LocalDate endDate;

    /**
     * 投保人名字
     */
    @Column(name = "totalFee",  columnDefinition = "DECIMAL(10,2)   COMMENT '投保人名字'")
    private Double totalFee;

    /**
     * 被保险人名字
     */
    @Column(name = "payWay",  columnDefinition = "VARCHAR(255)   COMMENT '被保险人名字'")
    private String payWay;

    /**
     * 险种名称
     */
    @Column(name = "argueWay",  columnDefinition = "VARCHAR(255)   COMMENT '险种名称'")
    private String argueWay;

    /**
     * 组合名称
     */
    @Column(name = "insurer",  columnDefinition = "VARCHAR(255)   COMMENT '组合名称'")
    private String insurer;

    /**
     * 销售机构名称
     */
    @Column(name = "insureIdCard",  columnDefinition = "VARCHAR(255)   COMMENT '销售机构名称'")
    private String insureIdCard;

    /**
     * 销售员名称
     */
    @Column(name = "insureAddr",  columnDefinition = "VARCHAR(255)   COMMENT '销售员名称'")
    private String insureAddr;

    /**
     * 机构电话
     */
    @Column(name = "tel",  columnDefinition = "VARCHAR(255)   COMMENT '机构电话'")
    private String tel;

    /**
     * 合同存储编号
     */
    @Column(name = "carNumber",  columnDefinition = "VARCHAR(255)   COMMENT '合同存储编号'")
    private String carNumber;

    /**
     * 合同存储路径
     */
    @Column(name = "brand",  columnDefinition = "VARCHAR(255)   COMMENT '合同存储路径'")
    private String brand;

    /**
     * 合同附件
     */
    @Column(name = "priceChoice",  columnDefinition = "VARCHAR(255)   COMMENT '合同附件'")
    private String priceChoice;

    /**
     * 是否已续保
     */
    @Column(name = "ownNature",  columnDefinition = "VARCHAR(255)   COMMENT '是否已续保'")
    private String ownNature;


    public String getInsureNumber() {
        return insureNumber;
    }

    public void setInsureNumber(String insureNumber) {
        this.insureNumber = insureNumber;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getArgueWay() {
        return argueWay;
    }

    public void setArgueWay(String argueWay) {
        this.argueWay = argueWay;
    }

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }

    public String getInsureIdCard() {
        return insureIdCard;
    }

    public void setInsureIdCard(String insureIdCard) {
        this.insureIdCard = insureIdCard;
    }

    public String getInsureAddr() {
        return insureAddr;
    }

    public void setInsureAddr(String insureAddr) {
        this.insureAddr = insureAddr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPriceChoice() {
        return priceChoice;
    }

    public void setPriceChoice(String priceChoice) {
        this.priceChoice = priceChoice;
    }

    public String getOwnNature() {
        return ownNature;
    }

    public void setOwnNature(String ownNature) {
        this.ownNature = ownNature;
    }
}