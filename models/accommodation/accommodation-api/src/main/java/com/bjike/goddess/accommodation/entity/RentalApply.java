package com.bjike.goddess.accommodation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

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
@Table(name = "rentalApply")
public class RentalApply extends BaseEntity{
    /**
     * 姓名（用户名称）
     */
    private String name;
    /**
     * 地区
     */
    private String area;
    /**
     * 岗位
     */
    private String jobs;
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
     * 住宿人
     */
    private String stayPeople;
    /**
     * 申请原因
     */
    private String reason;
    /**
     * 租房用途
     */
    private String purpose;
    /**
     * 租房地址
     */
    private String address;
    /**
     * 房东姓名
     */
    private String landlord ;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 中介费
     */
    private Double agency;
    /**
     * 押金
     */
    private Double deposit;
    /**
     * 房租
     */
    private Double rent;
    /**
     * 房租管理费
     */
    private Double rentFee;
    /**
     * 卫生费
     */
    private Double sanitation;
    /**
     * 水费计价
     */
    private Double water;
    /**
     * 电费计价
     */
    private Double energy;
    /**
     * 网络套餐费用
     */
    private Double network;
    /**
     * 项目经理审批
     */
    private String manageApproval;
    /**
     * 商务发展部意见
     */
    private String commerceRemark;
    /**
     * 综合资源部意见
     */
    private String comprehensiveRemark;
    /**
     * 运营财务部意见
     */
    private String operatingRemark;
    /**
     * 备注
     */
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getManageApproval() {
        return manageApproval;
    }

    public void setManageApproval(String manageApproval) {
        this.manageApproval = manageApproval;
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
}
