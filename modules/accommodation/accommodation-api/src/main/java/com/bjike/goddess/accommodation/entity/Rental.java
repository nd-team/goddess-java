package com.bjike.goddess.accommodation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 16:16]
 * @Description: [租房信息]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "accommodation_rental")
public class Rental extends BaseEntity {
    /**
     * 租房编号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房编号'",unique = true)
    private String rentNum;
    /**
     * 地区
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;
    /**
     * 项目组
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目组'")
    private String projectGroup;
    /**
     * 项目名称
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目名称'")
    private String projectName;
    /**
     * 租赁人
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租赁人'")
    private String lessee;
    /**
     * 租房地址
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房地址'")
    private String address;
    /**
     * 房东姓名
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '房东姓名'")
    private String landlord;
    /**
     * 联系方式
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '联系方式'")
    private String contact;
    /**
     * 账户名称
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '账户名称'")
    private String accountTitle;
    /**
     * 银行卡号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '银行卡号'")
    private String bankNumber;
    /**
     * 银行开户行（详细到支行）
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '银行开户行（详细到支行）'")
    private String bankAccount;
    /**
     * 租房用途
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房用途'")
    private String purpose;
    /**
     * 租房合同
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房合同'")
    private String tenancyAgreement;
    /**
     * 租房开始时间
     */
    @Column(columnDefinition = "DATE COMMENT '租房开始时间'")
    private LocalDate rentBeginTime;
    /**
     * 租房截止时间
     */
    @Column(columnDefinition = "DATE COMMENT '租房截止时间'")
    private LocalDate rentEndTime;
    /**
     * 房屋交租方式（转账，现金）
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '房屋交租方式（转账，现金）'")
    private String taxesWay;
    /**
     * 房租交租频率
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '房租交租频率'")
    private String taxesFequency;
    /**
     * 房租缴费日期
     */
    @Column(columnDefinition = "DATE COMMENT '房租缴费日期'")
    private LocalDate rentTime;
    /**
     * 水电费缴费频率
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '水电费缴费频率'")
    private String paymentFrequency;
    /**
     * 水电费缴费日期
     */
    @Column(columnDefinition = "DATE COMMENT '水电费缴费日期'")
    private LocalDate paymentTime;
    /**
     * 中介费
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '中介费'")
    private Double agency;
    /**
     * 押金
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '押金'")
    private Double deposit;
    /**
     * 房租
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '房租'")
    private Double rent;
    /**
     * 管理费
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '管理费'")
    private Double managementFee;
    /**
     * 卫生费
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '卫生费'")
    private Double healthFee;
    /**
     * 房租缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '房租缴费方'")
    private String rentPay;
    /**
     * 水费期初数目
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '水费期初数目'")
    private Double water;
    /**
     * 水费计价金额(元/吨)
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '水费计价金额(元/吨)'")
    private Double waterMoney;

    /**
     * 水费缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '水费缴费方'")
    private String waterPay;
    /**
     * 电费期初数目
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '电费期初数目'")
    private Double energy;
    /**
     * 电费计价金额(元/吨)
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '电费计价金额(元/吨)'")
    private Double energyMoney;
    /**
     * 电费缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '电费缴费方'")
    private String energyPay;
    /**
     * 网络套餐费用使用期限
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '网络套餐费用使用期限'")
    private String network;
    /**
     * 网络套餐费用缴纳金额
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '网络套餐费用缴纳金额'")
    private Double networkMoney;
    /**
     * 网络套餐费用缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '网络套餐费用缴费方'")
    private String networkPay;
    /**
     * 管道燃气费充值额度
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '管道燃气费充值额度'")
    private Double gas;
    /**
     * 燃气费缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '燃气费缴费方'")
    private String gasPay;
    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String remark;

    public String getRentNum() {
        return rentNum;
    }

    public void setRentNum(String rentNum) {
        this.rentNum = rentNum;
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

    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTenancyAgreement() {
        return tenancyAgreement;
    }

    public void setTenancyAgreement(String tenancyAgreement) {
        this.tenancyAgreement = tenancyAgreement;
    }

    public LocalDate getRentBeginTime() {
        return rentBeginTime;
    }

    public void setRentBeginTime(LocalDate rentBeginTime) {
        this.rentBeginTime = rentBeginTime;
    }

    public LocalDate getRentEndTime() {
        return rentEndTime;
    }

    public void setRentEndTime(LocalDate rentEndTime) {
        this.rentEndTime = rentEndTime;
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

    public LocalDate getRentTime() {
        return rentTime;
    }

    public void setRentTime(LocalDate rentTime) {
        this.rentTime = rentTime;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public LocalDate getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDate paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Double getAgency() {
        return agency;
    }

    public void setAgency(Double agency) {
        this.agency = agency;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public Double getHealthFee() {
        return healthFee;
    }

    public void setHealthFee(Double healthFee) {
        this.healthFee = healthFee;
    }

    public String getRentPay() {
        return rentPay;
    }

    public void setRentPay(String rentPay) {
        this.rentPay = rentPay;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getWaterMoney() {
        return waterMoney;
    }

    public void setWaterMoney(Double waterMoney) {
        this.waterMoney = waterMoney;
    }

    public String getWaterPay() {
        return waterPay;
    }

    public void setWaterPay(String waterPay) {
        this.waterPay = waterPay;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getEnergyMoney() {
        return energyMoney;
    }

    public void setEnergyMoney(Double energyMoney) {
        this.energyMoney = energyMoney;
    }

    public String getEnergyPay() {
        return energyPay;
    }

    public void setEnergyPay(String energyPay) {
        this.energyPay = energyPay;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Double getNetworkMoney() {
        return networkMoney;
    }

    public void setNetworkMoney(Double networkMoney) {
        this.networkMoney = networkMoney;
    }

    public String getNetworkPay() {
        return networkPay;
    }

    public void setNetworkPay(String networkPay) {
        this.networkPay = networkPay;
    }

    public Double getGas() {
        return gas;
    }

    public void setGas(Double gas) {
        this.gas = gas;
    }

    public String getGasPay() {
        return gasPay;
    }

    public void setGasPay(String gasPay) {
        this.gasPay = gasPay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
