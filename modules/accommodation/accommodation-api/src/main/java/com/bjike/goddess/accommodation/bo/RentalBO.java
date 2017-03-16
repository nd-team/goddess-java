package com.bjike.goddess.accommodation.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 16:16]
 * @Description: [租房信息  业务传输]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RentalBO extends BaseBO {
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
     * 租赁人
     */
    private String lessee;
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
     * 租房用途
     */
    private String purpose;
    /**
     * 租房合同
     */
    private String tenancyAgreement;
    /**
     * 租房开始时间
     */
    private LocalDate rentBeginTime;
    /**
     * 租房截止时间
     */
    private LocalDate rentEndTime;
    /**
     * 房屋交租方式（转账，现金）
     */
    private String taxesWay;
    /**
     * 房租交租频率
     */
    private String taxesFequency;
    /**
     * 房租缴费日期
     */
    private LocalDate rentTime;
    /**
     * 水电费缴费频率
     */
    private String paymentFrequency;
    /**
     * 中介费
     */
    private String agency;
    /**
     * 押金
     */
    private String deposit;
    /**
     * 房租
     */
    private String rent;
    /**
     * 水费计价
     */
    private String water;
    /**
     * 电费计价
     */
    private String energy;
    /**
     * 网络套餐费用
     */
    private String network;
    /**
     * 燃气费
     */
    private String gas;
    /**
     * 备注
     */
    private String remark;

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

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
