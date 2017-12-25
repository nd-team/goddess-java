package com.bjike.goddess.accommodation.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-13 10:16]
 * @Description: [租房申请]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RentalApplyTO extends BaseTO {
    public interface TestBusiness{}
    public interface TestFinance{}
    public interface TestResource{}
    public interface TestManage{}
    /**
     * 租房编号
     */
    private String rentNum;
    /**
     * 姓名（用户名称）
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String name;
    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空", groups = {ADD.class, EDIT.class})
    private String employeeNum;
    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;
    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空", groups = {ADD.class, EDIT.class})
    private String jobs;
    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空", groups = {ADD.class, EDIT.class})
    private String projectGroup;
    /**
     * 项目名称
     */
    @NotNull(message = "项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String[] projectName;
    /**
     * 租赁人
     */
    @NotBlank(message = "租赁人不能为空", groups = {ADD.class, EDIT.class})
    private String lessee;
    /**
     * 住宿人
     */
    @NotBlank(message = "住宿人不能为空", groups = {ADD.class, EDIT.class})
    private String stayPeople;
    /**
     * 申请原因
     */
    @NotBlank(message = "申请原因不能为空", groups = {ADD.class, EDIT.class})
    private String reason;
    /**
     * 租房用途
     */
    @NotBlank(message = "租房用途不能为空", groups = {ADD.class, EDIT.class})
    private String purpose;
    /**
     * 租房地址
     */
    @NotBlank(message = "租房地址不能为空", groups = {ADD.class, EDIT.class})
    private String address;
    /**
     * 房东姓名
     */
    @NotBlank(message = "房东姓名不能为空", groups = {ADD.class, EDIT.class})
    private String landlord;
    /**
     * 联系方式
     */
    @NotBlank(message = "联系方式不能为空", groups = {ADD.class, EDIT.class})
    private String contact;
    /**
     * 中介费
     */
    @NotNull(message = "中介费不能为空", groups = {ADD.class, EDIT.class})
    private Double agency;
    /**
     * 押金
     */
    @NotNull(message = "押金不能为空", groups = {ADD.class, EDIT.class})
    private Double deposit;
    /**
     * 房租
     */
    @NotNull(message = "房租不能为空", groups = {ADD.class, EDIT.class})
    private Double rent;
    /**
     * 房租缴费方
     */
    @NotBlank(message = "房租缴费方不能为空", groups = {ADD.class, EDIT.class})
    private String rentPay;
    /**
     * 房租管理费
     */
    @NotNull(message = "房租管理费不能为空", groups = {ADD.class, EDIT.class})
    private Double rentFee;
    /**
     * 房租管理费缴费方
     */
    @NotBlank(message = "房租管理费缴费方不能为空", groups = {ADD.class, EDIT.class})
    private String rentFeePay;
    /**
     * 卫生费
     */
    @NotNull(message = "卫生费不能为空", groups = {ADD.class, EDIT.class})
    private Double sanitation;
    /**
     * 卫生费缴费方
     */
    @NotBlank(message = "卫生费缴费方不能为空", groups = {ADD.class, EDIT.class})
    private String sanitationPay;
    /**
     * 水费计价额
     */
    @NotNull(message = "水费计价额不能为空", groups = {ADD.class, EDIT.class})
    private Double water;
    /**
     * 水费缴费方
     */
    @NotBlank(message = "水费缴费方不能为空", groups = {ADD.class, EDIT.class})
    private String waterPay;
    /**
     * 电费计价额
     */
    @NotNull(message = "电费计价额不能为空", groups = {ADD.class, EDIT.class})
    private Double energy;
    /**
     * 电费缴费方
     */
    @NotBlank(message = "电费缴费方不能为空", groups = {ADD.class, EDIT.class})
    private String energyPay;
    /**
     * 网络套餐费用
     */
    @NotNull(message = "网络套餐费用不能为空", groups = {ADD.class, EDIT.class})
    private Double network;
    /**
     * 网络套餐费用缴费方
     */
    @NotBlank(message = "网络套餐费用缴费方不能为空", groups = {ADD.class, EDIT.class})
    private String networkPay;
    /**
     * 项目经理
     */
    private String manage;
    /**
     * 项目经理意见
     */
    private String manageOpinion;
    /**
     * 项目经理是否通过(通过/不通过)
     */
    @NotBlank(message = "项目经理是否通过不能为空",groups = {RentalApplyTO.TestManage.class})
    private String managePass;
    /**
     * 商务发展部意见(通过/不通过)
     */
    @NotBlank(message = "商务发展部意见不能为空",groups = {RentalApplyTO.TestBusiness.class})
    private String commerceRemark;
    /**
     * 综合资源部意见(通过/不通过)
     */
    @NotBlank(message = "综合资源部意见不能为空",groups = {RentalApplyTO.TestResource.class})
    private String comprehensiveRemark;
    /**
     * 运营财务部意见(通过/不通过)
     */
    @NotBlank(message = "运营财务部意见不能为空",groups = {RentalApplyTO.TestFinance.class})
    private String operatingRemark;
    /**
     * 备注
     */
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

    public String[] getProjectName() {
        return projectName;
    }

    public void setProjectName(String[] projectName) {
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
