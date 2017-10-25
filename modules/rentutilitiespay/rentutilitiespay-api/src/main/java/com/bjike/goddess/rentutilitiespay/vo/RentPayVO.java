package com.bjike.goddess.rentutilitiespay.vo;

import com.bjike.goddess.rentutilitiespay.enums.PayParty;

/**
 * 房租缴费表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:10 ]
 * @Description: [ 房租缴费表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RentPayVO {

    /**
     * id
     */
    private String id;
    /**
     * 缴费日期
     */
    private String payDate;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 租房地址
     */
    private String address;

    /**
     * 房东姓名
     */
    private String landlord;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 账户名称
     */
    private String accountTitle;

    /**
     * 银行卡号
     */
    private String bankNumber;

    /**
     * 银行开户行（详细到支行）
     */
    private String bankAccount;

    /**
     * 房屋交租方式
     */
    private String taxesWay;

    /**
     * 房屋交租频率
     */
    private String taxesFequency;

    /**
     * 房租缴费日期
     */
    private String rentDate;

    /**
     * 水电费缴费频率
     */
    private String paymentFrequency;

    /**
     * 水电费缴费日期
     */
    private String paymentDate;

    /**
     * 房租（元/月）
     */
    private Double rent;

    /**
     * 管理费，卫生费
     */
    private Double fee;

    /**
     * 房租缴纳开始时间
     */
    private String rentStartTime;

    /**
     * 房租缴纳结束时间
     */
    private String rentEndTime;

    /**
     * 房租缴费方
     */
    private PayParty rentPay;

    /**
     * 水费初期数目
     */
    private Double waterBeginNum;

    /**
     * 水费计价金额（元/吨）
     */
    private Double waterValuationMoney;

    /**
     * 水费期末数目
     */
    private Double waterEndNum;

    /**
     * 用水量（水费期末数目-水费初期数目）
     */
    private Double water;

    /**
     * 水费缴纳金额（水费计价金额（元/吨）*用水量）
     */
    private Double waterPayMoney;

    /**
     * 水费缴纳开始时间
     */
    private String waterStartTime;

    /**
     * 水费缴纳结束时间
     */
    private String waterEndTime;

    /**
     * 水费缴费方
     */
    private PayParty waterPay;

    /**
     * 电费初期数目
     */
    private Double energyBeginNum;

    /**
     * 电费计价金额（元/吨）
     */
    private Double energyValuationMoney;

    /**
     * 电费期末数目
     */
    private Double energyEndNum;

    /**
     * 用电量（电费期末数目-电费初期数目）
     */
    private Double energy;

    /**
     * 电费缴纳金额（电费计价金额（元/吨）*用电量）
     */
    private Double energyPayMoney;

    /**
     * 电费缴纳开始时间
     */
    private String energyStartTime;

    /**
     * 电费缴纳结束时间
     */
    private String energyEndTime;

    /**
     * 电费缴费方
     */
    private PayParty energyPay;

    /**
     * 管道燃气费充值额度
     */
    private Double gasRechargeLines;

    /**
     * 燃气费缴纳开始时间
     */
    private String gasStartTime;

    /**
     * 燃气费缴纳结束时间
     */
    private String gasEndTime;

    /**
     * 燃气费缴费方
     */
    private PayParty gasPay;

    /**
     * 缴纳金额汇总（房租（元/月）+管理费，卫生费+水费缴纳金额+电费缴纳金额+管道燃气费充值额度）
     */
    private Double payMoneyCollect;

    /**
     * 运营财务部确认是否缴费
     */
    private Boolean operatingPay;

    /**
     * 房租收费是否已邮寄到广州
     */
    private Boolean rentMoneyMail;


    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
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

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
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

    public String getRentStartTime() {
        return rentStartTime;
    }

    public void setRentStartTime(String rentStartTime) {
        this.rentStartTime = rentStartTime;
    }

    public String getRentEndTime() {
        return rentEndTime;
    }

    public void setRentEndTime(String rentEndTime) {
        this.rentEndTime = rentEndTime;
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

    public String getWaterStartTime() {
        return waterStartTime;
    }

    public void setWaterStartTime(String waterStartTime) {
        this.waterStartTime = waterStartTime;
    }

    public String getWaterEndTime() {
        return waterEndTime;
    }

    public void setWaterEndTime(String waterEndTime) {
        this.waterEndTime = waterEndTime;
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

    public String getEnergyStartTime() {
        return energyStartTime;
    }

    public void setEnergyStartTime(String energyStartTime) {
        this.energyStartTime = energyStartTime;
    }

    public String getEnergyEndTime() {
        return energyEndTime;
    }

    public void setEnergyEndTime(String energyEndTime) {
        this.energyEndTime = energyEndTime;
    }


    public Double getGasRechargeLines() {
        return gasRechargeLines;
    }

    public void setGasRechargeLines(Double gasRechargeLines) {
        this.gasRechargeLines = gasRechargeLines;
    }

    public String getGasStartTime() {
        return gasStartTime;
    }

    public void setGasStartTime(String gasStartTime) {
        this.gasStartTime = gasStartTime;
    }

    public String getGasEndTime() {
        return gasEndTime;
    }

    public void setGasEndTime(String gasEndTime) {
        this.gasEndTime = gasEndTime;
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


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public PayParty getRentPay() {
        return rentPay;
    }

    public void setRentPay(PayParty rentPay) {
        this.rentPay = rentPay;
    }

    public PayParty getWaterPay() {
        return waterPay;
    }

    public void setWaterPay(PayParty waterPay) {
        this.waterPay = waterPay;
    }

    public PayParty getEnergyPay() {
        return energyPay;
    }

    public void setEnergyPay(PayParty energyPay) {
        this.energyPay = energyPay;
    }

    public PayParty getGasPay() {
        return gasPay;
    }

    public void setGasPay(PayParty gasPay) {
        this.gasPay = gasPay;
    }
}