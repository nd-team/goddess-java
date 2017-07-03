package com.bjike.goddess.rentutilitiespay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 房租缴费
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:10 ]
 * @Description: [ 房租缴费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "rentutilitiespay_rentpay")
public class RentPay extends BaseEntity {

    /**
     * 缴费日期
     */
    @Column(name = "payDate", columnDefinition = "DATE   COMMENT '缴费日期'")
    private LocalDate payDate;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 租房地址
     */
    @Column(name = "address",columnDefinition = "VARCHAR(255)   COMMENT '租房地址'")
    private String address;

    /**
     * 房东姓名
     */
    @Column(name = "landlord", columnDefinition = "VARCHAR(255)   COMMENT '房东姓名'")
    private String landlord;

    /**
     * 联系方式
     */
    @Column(name = "contact", columnDefinition = "VARCHAR(255)   COMMENT '联系方式'")
    private String contact;

    /**
     * 账户名称
     */
    @Column(name = "accountTitle", columnDefinition = "VARCHAR(255)   COMMENT '账户名称'")
    private String accountTitle;

    /**
     * 银行卡号
     */
    @Column(name = "bankNumber", columnDefinition = "VARCHAR(255)   COMMENT '银行卡号'")
    private String bankNumber;

    /**
     * 银行开户行（详细到支行）
     */
    @Column(name = "bankAccount", columnDefinition = "VARCHAR(255)   COMMENT '银行开户行（详细到支行）'")
    private String bankAccount;

    /**
     * 房屋交租方式
     */
    @Column(name = "taxesWay", columnDefinition = "VARCHAR(255)   COMMENT '房屋交租方式'")
    private String taxesWay;

    /**
     * 房屋交租频率
     */
    @Column(name = "taxesFequency", columnDefinition = "VARCHAR(255)   COMMENT '房屋交租频率'")
    private String taxesFequency;

    /**
     * 房租缴费日期
     */
    @Column(name = "rentDate",  columnDefinition = "DATE   COMMENT '房租缴费日期'")
    private LocalDate rentDate;

    /**
     * 水电费缴费频率
     */
    @Column(name = "paymentFrequency", columnDefinition = "VARCHAR(255)   COMMENT '水电费缴费频率'")
    private String paymentFrequency;

    /**
     * 水电费缴费日期
     */
    @Column(name = "paymentDate", columnDefinition = "DATE   COMMENT '水电费缴费日期'")
    private LocalDate paymentDate;

    /**
     * 房租（元/月）
     */
    @Column(name = "rent", columnDefinition = "DECIMAL(10,2)   COMMENT '房租（元/月）'")
    private Double rent;

    /**
     * 管理费，卫生费
     */
    @Column(name = "fee", columnDefinition = "DECIMAL(10,2)   COMMENT '管理费，卫生费'")
    private Double fee;

    /**
     * 房租缴纳开始时间
     */
    @Column(name = "rentStartTime", columnDefinition = "DATE   COMMENT '房租缴纳开始时间'")
    private LocalDate rentStartTime;

    /**
     * 房租缴纳结束时间
     */
    @Column(name = "rentEndTime", columnDefinition = "DATE   COMMENT '房租缴纳结束时间'")
    private LocalDate rentEndTime;

    /**
     * 房租缴费方
     */
    @Column(name = "rentPay", columnDefinition = "VARCHAR(255)   COMMENT '房租缴费方'")
    private String rentPay;

    /**
     * 水费初期数目
     */
    @Column(name = "waterBeginNum",columnDefinition = "DECIMAL(10,2)   COMMENT '水费初期数目'")
    private Double waterBeginNum;

    /**
     * 水费计价金额（元/吨）
     */
    @Column(name = "waterValuationMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '水费计价金额（元/吨）'")
    private Double waterValuationMoney;

    /**
     * 水费期末数目
     */
    @Column(name = "waterEndNum", columnDefinition = "DECIMAL(10,2)   COMMENT '水费期末数目'")
    private Double waterEndNum;

    /**
     * 用水量（水费期末数目-水费初期数目）
     */
    @Column(name = "water", columnDefinition = "DECIMAL(10,2)   COMMENT '用水量（水费期末数目-水费初期数目）'")
    private Double water;

    /**
     * 水费缴纳金额（水费计价金额（元/吨）*用水量）
     */
    @Column(name = "waterPayMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '水费缴纳金额（水费计价金额（元/吨）*用水量）'")
    private Double waterPayMoney;

    /**
     * 水费缴纳开始时间
     */
    @Column(name = "waterStartTime",  columnDefinition = "DATE   COMMENT '水费缴纳开始时间'")
    private LocalDate waterStartTime;

    /**
     * 水费缴纳结束时间
     */
    @Column(name = "waterEndTime",  columnDefinition = "DATE   COMMENT '水费缴纳结束时间'")
    private LocalDate waterEndTime;

    /**
     * 水费缴费方
     */
    @Column(name = "waterPay", columnDefinition = "VARCHAR(255)   COMMENT '水费缴费方'")
    private String waterPay;

    /**
     * 电费初期数目
     */
    @Column(name = "energyBeginNum", columnDefinition = "DECIMAL(10,2)   COMMENT '电费初期数目'")
    private Double energyBeginNum;

    /**
     * 电费计价金额（元/吨）
     */
    @Column(name = "energyValuationMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '电费计价金额（元/吨）'")
    private Double energyValuationMoney;

    /**
     * 电费期末数目
     */
    @Column(name = "energyEndNum", columnDefinition = "DECIMAL(10,2)   COMMENT '电费期末数目'")
    private Double energyEndNum;

    /**
     * 用电量（电费期末数目-电费初期数目）
     */
    @Column(name = "energy", columnDefinition = "DECIMAL(10,2)   COMMENT '用电量（电费期末数目-电费初期数目）'")
    private Double energy;

    /**
     * 电费缴纳金额（电费计价金额（元/吨）*用电量）
     */
    @Column(name = "energyPayMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '电费缴纳金额（电费计价金额（元/吨）*用电量）'")
    private Double energyPayMoney;

    /**
     * 电费缴纳开始时间
     */
    @Column(name = "energyStartTime", columnDefinition = "DATE   COMMENT '电费缴纳开始时间'")
    private LocalDate energyStartTime;

    /**
     * 电费缴纳结束时间
     */
    @Column(name = "energyEndTime", columnDefinition = "DATE   COMMENT '电费缴纳结束时间'")
    private LocalDate energyEndTime;

    /**
     * 电费缴费方
     */
    @Column(name = "energyPay", columnDefinition = "VARCHAR(255)   COMMENT '电费缴费方'")
    private String energyPay;

    /**
     * 管道燃气费充值额度
     */
    @Column(name = "gasRechargeLines", columnDefinition = "DECIMAL(10,2)   COMMENT '管道燃气费充值额度'")
    private Double gasRechargeLines;

    /**
     * 燃气费缴纳开始时间
     */
    @Column(name = "gasStartTime", columnDefinition = "DATE   COMMENT '燃气费缴纳开始时间'")
    private LocalDate gasStartTime;

    /**
     * 燃气费缴纳结束时间
     */
    @Column(name = "gasEndTime", columnDefinition = "DATE   COMMENT '燃气费缴纳结束时间'")
    private LocalDate gasEndTime;

    /**
     * 燃气费缴费方
     */
    @Column(name = "gasPay", columnDefinition = "VARCHAR(255)   COMMENT '燃气费缴费方'")
    private String gasPay;

    /**
     * 缴纳金额汇总（房租（元/月）+管理费，卫生费+水费缴纳金额+电费缴纳金额+管道燃气费充值额度）
     */
    @Column(name = "payMoneyCollect", columnDefinition = "DECIMAL(10,2)   COMMENT '缴纳金额汇总（房租（元/月）+管理费，卫生费+水费缴纳金额+电费缴纳金额+管道燃气费充值额度）'")
    private Double payMoneyCollect;

    /**
     * 运营财务部确认是否缴费
     */
    @Column(name = "is_operatingPay", columnDefinition = "TINYINT(2) COMMENT '运营财务部确认是否缴费'")
    private Boolean operatingPay;

    /**
     * 房租收费是否已邮寄到广州
     */
    @Column(name = "is_rentMoneyMail", columnDefinition = "TINYINT(2)  COMMENT '房租收费是否已邮寄到广州'")
    private Boolean rentMoneyMail;

    /**
     * 房租收据附件
     */
    @Column(name = "rentReceiptAccessory", columnDefinition = "VARCHAR(255)   COMMENT '房租收据附件'")
    private String rentReceiptAccessory;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTaxesWay() {
        return taxesWay;
    }

    public void setTaxesWay(String taxesWay) {
        this.taxesWay = taxesWay;
    }

    public String getTaxesFequency() {
        return taxesFequency;
    }

    public void setTaxesFequency(String taxesFequency) {
        this.taxesFequency = taxesFequency;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public LocalDate getRentStartTime() {
        return rentStartTime;
    }

    public void setRentStartTime(LocalDate rentStartTime) {
        this.rentStartTime = rentStartTime;
    }

    public LocalDate getRentEndTime() {
        return rentEndTime;
    }

    public void setRentEndTime(LocalDate rentEndTime) {
        this.rentEndTime = rentEndTime;
    }

    public String getRentPay() {
        return rentPay;
    }

    public void setRentPay(String rentPay) {
        this.rentPay = rentPay;
    }

    public Double getWaterBeginNum() {
        return waterBeginNum;
    }

    public void setWaterBeginNum(Double waterBeginNum) {
        this.waterBeginNum = waterBeginNum;
    }

    public Double getWaterValuationMoney() {
        return waterValuationMoney;
    }

    public void setWaterValuationMoney(Double waterValuationMoney) {
        this.waterValuationMoney = waterValuationMoney;
    }

    public Double getWaterEndNum() {
        return waterEndNum;
    }

    public void setWaterEndNum(Double waterEndNum) {
        this.waterEndNum = waterEndNum;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getWaterPayMoney() {
        return waterPayMoney;
    }

    public void setWaterPayMoney(Double waterPayMoney) {
        this.waterPayMoney = waterPayMoney;
    }

    public LocalDate getWaterStartTime() {
        return waterStartTime;
    }

    public void setWaterStartTime(LocalDate waterStartTime) {
        this.waterStartTime = waterStartTime;
    }

    public LocalDate getWaterEndTime() {
        return waterEndTime;
    }

    public void setWaterEndTime(LocalDate waterEndTime) {
        this.waterEndTime = waterEndTime;
    }

    public String getWaterPay() {
        return waterPay;
    }

    public void setWaterPay(String waterPay) {
        this.waterPay = waterPay;
    }

    public Double getEnergyBeginNum() {
        return energyBeginNum;
    }

    public void setEnergyBeginNum(Double energyBeginNum) {
        this.energyBeginNum = energyBeginNum;
    }

    public Double getEnergyValuationMoney() {
        return energyValuationMoney;
    }

    public void setEnergyValuationMoney(Double energyValuationMoney) {
        this.energyValuationMoney = energyValuationMoney;
    }

    public Double getEnergyEndNum() {
        return energyEndNum;
    }

    public void setEnergyEndNum(Double energyEndNum) {
        this.energyEndNum = energyEndNum;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getEnergyPayMoney() {
        return energyPayMoney;
    }

    public void setEnergyPayMoney(Double energyPayMoney) {
        this.energyPayMoney = energyPayMoney;
    }

    public LocalDate getEnergyStartTime() {
        return energyStartTime;
    }

    public void setEnergyStartTime(LocalDate energyStartTime) {
        this.energyStartTime = energyStartTime;
    }

    public LocalDate getEnergyEndTime() {
        return energyEndTime;
    }

    public void setEnergyEndTime(LocalDate energyEndTime) {
        this.energyEndTime = energyEndTime;
    }

    public String getEnergyPay() {
        return energyPay;
    }

    public void setEnergyPay(String energyPay) {
        this.energyPay = energyPay;
    }

    public Double getGasRechargeLines() {
        return gasRechargeLines;
    }

    public void setGasRechargeLines(Double gasRechargeLines) {
        this.gasRechargeLines = gasRechargeLines;
    }

    public LocalDate getGasStartTime() {
        return gasStartTime;
    }

    public void setGasStartTime(LocalDate gasStartTime) {
        this.gasStartTime = gasStartTime;
    }

    public LocalDate getGasEndTime() {
        return gasEndTime;
    }

    public void setGasEndTime(LocalDate gasEndTime) {
        this.gasEndTime = gasEndTime;
    }

    public String getGasPay() {
        return gasPay;
    }

    public void setGasPay(String gasPay) {
        this.gasPay = gasPay;
    }

    public Double getPayMoneyCollect() {
        return payMoneyCollect;
    }

    public void setPayMoneyCollect(Double payMoneyCollect) {
        this.payMoneyCollect = payMoneyCollect;
    }

    public Boolean getOperatingPay() {
        return operatingPay;
    }

    public void setOperatingPay(Boolean operatingPay) {
        this.operatingPay = operatingPay;
    }

    public Boolean getRentMoneyMail() {
        return rentMoneyMail;
    }

    public void setRentMoneyMail(Boolean rentMoneyMail) {
        this.rentMoneyMail = rentMoneyMail;
    }

    public String getRentReceiptAccessory() {
        return rentReceiptAccessory;
    }

    public void setRentReceiptAccessory(String rentReceiptAccessory) {
        this.rentReceiptAccessory = rentReceiptAccessory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}