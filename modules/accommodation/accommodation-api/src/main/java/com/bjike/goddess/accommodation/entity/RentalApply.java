package com.bjike.goddess.accommodation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [租房申请]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "accommodation_rentalapply")
public class RentalApply extends BaseEntity {
    /**
     * 租房编号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房编号'",unique = true)
    private String rentNum;
    /**
     * 姓名（用户名称）
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '姓名（用户名称）'")
    private String name;
    /**
     * 员工编号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '员工编号'")
    private String employeeNum;
    /**
     * 地区
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;
    /**
     * 岗位
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '岗位'")
    private String jobs;
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
     * 住宿人
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '住宿人'")
    private String stayPeople;
    /**
     * 申请原因
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '申请原因'")
    private String reason;
    /**
     * 租房用途
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房用途'")
    private String purpose;
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
     * 房租缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '房租缴费方'")
    private String rentPay;
    /**
     * 房租管理费
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '房租管理费'")
    private Double rentFee;
    /**
     * 房租管理费缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '房租管理费缴费方'")
    private String rentFeePay;
    /**
     * 卫生费
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '卫生费'")
    private Double sanitation;
    /**
     * 卫生费缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '卫生费缴费方'")
    private String sanitationPay;
    /**
     * 水费计价额
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '水费计价'")
    private Double water;
    /**
     * 水费缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '水费缴费方'")
    private String waterPay;
    /**
     * 电费计价额
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '电费计价'")
    private Double energy;
    /**
     * 电费缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '电费缴费方'")
    private String energyPay;
    /**
     * 网络套餐费用
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '网络套餐费用'")
    private Double network;
    /**
     * 网络套餐费用缴费方
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '网络套餐费用缴费方'")
    private String networkPay;
    /**
     * 项目经理
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目经理'")
    private String manage;
    /**
     * 项目经理意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目经理意见'")
    private String manageOpinion;
    /**
     * 项目经理是否通过(是/否)
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目经理是否通过(是/否)'")
    private String managePass;
    /**
     * 商务发展部意见
     */
    @Column( columnDefinition = "VARCHAR(255) COMMENT '商务发展部意见'")
    private String commerceRemark;
    /**
     * 综合资源部意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '综合资源部意见'")
    private String comprehensiveRemark;
    /**
     * 运营财务部意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '运营财务部意见'")
    private String operatingRemark;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
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

    public String getStayPeople() {
        return stayPeople;
    }

    public void setStayPeople(String stayPeople) {
        this.stayPeople = stayPeople;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getManageOpinion() {
        return manageOpinion;
    }

    public void setManageOpinion(String manageOpinion) {
        this.manageOpinion = manageOpinion;
    }

    public String getManagePass() {
        return managePass;
    }

    public void setManagePass(String managePass) {
        this.managePass = managePass;
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

    public Double getRentFee() {
        return rentFee;
    }

    public void setRentFee(Double rentFee) {
        this.rentFee = rentFee;
    }

    public Double getSanitation() {
        return sanitation;
    }

    public void setSanitation(Double sanitation) {
        this.sanitation = sanitation;
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

    public Double getNetwork() {
        return network;
    }

    public void setNetwork(Double network) {
        this.network = network;
    }


    public String getCommerceRemark() {
        return commerceRemark;
    }

    public void setCommerceRemark(String commerceRemark) {
        this.commerceRemark = commerceRemark;
    }

    public String getComprehensiveRemark() {
        return comprehensiveRemark;
    }

    public void setComprehensiveRemark(String comprehensiveRemark) {
        this.comprehensiveRemark = comprehensiveRemark;
    }

    public String getOperatingRemark() {
        return operatingRemark;
    }

    public void setOperatingRemark(String operatingRemark) {
        this.operatingRemark = operatingRemark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRentPay() {
        return rentPay;
    }

    public void setRentPay(String rentPay) {
        this.rentPay = rentPay;
    }

    public String getRentFeePay() {
        return rentFeePay;
    }

    public void setRentFeePay(String rentFeePay) {
        this.rentFeePay = rentFeePay;
    }

    public String getSanitationPay() {
        return sanitationPay;
    }

    public void setSanitationPay(String sanitationPay) {
        this.sanitationPay = sanitationPay;
    }

    public String getWaterPay() {
        return waterPay;
    }

    public void setWaterPay(String waterPay) {
        this.waterPay = waterPay;
    }

    public String getEnergyPay() {
        return energyPay;
    }

    public void setEnergyPay(String energyPay) {
        this.energyPay = energyPay;
    }

    public String getNetworkPay() {
        return networkPay;
    }

    public void setNetworkPay(String networkPay) {
        this.networkPay = networkPay;
    }
}
