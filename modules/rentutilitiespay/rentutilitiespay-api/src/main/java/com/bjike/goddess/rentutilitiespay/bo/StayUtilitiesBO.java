package com.bjike.goddess.rentutilitiespay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.util.List;
import java.util.Map;

/**
 * 员工住宿水电费业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:44 ]
 * @Description: [ 员工住宿水电费业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StayUtilitiesBO extends BaseBO {

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
     * 住宿地址
     */
    private String address;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工编号
     */
    private String num;

    /**
     * 住宿开始时间
     */
    private String stayStartTime;

    /**
     * 住宿结束时间
     */
    private String stayEndTime;

    /**
     * 住宿天数
     */
    private Double stayDay;

    /**
     * 房租公司缴纳
     */
    private Double rentCompanyPay;

    /**
     * 房租员工缴纳
     */
    private Double rentStaffPay;

    /**
     * 当月应缴水费总额
     */
    private Double waterAmount;

    /**
     * 水费公司缴纳
     */
    private Double waterCompanyPay;

    /**
     * 水费员工缴纳
     */
    private Double waterStaffPay;

    /**
     * 当月应缴电费总额
     */
    private Double energyAmount;

    /**
     * 电费公司缴纳
     */
    private Double energyCompanyPay;

    /**
     * 电费员工缴纳
     */
    private Double energyStaffPay;

    /**
     * 管道燃气费充值额度
     */
    private Double gasRechargeLines;

    /**
     * 燃气费公司缴纳
     */
    private Double gasCompanyPay;

    /**
     * 燃气费员工缴纳
     */
    private Double gasStaffPay;

    /**
     * 员工应缴金额汇总（水费员工缴纳+电费员工缴纳+燃气费员工缴纳）
     */
    private Double staffPayCollect;

    /**
     * 员工核实（确认/有误）
     */
    private String staffVerify;

    /**
     * 综合资源部核实情况（是否需要修改）
     */
    private Boolean comprehensiveVerifySituation;

    /**
     * 扣款情况（运营商务部确认）
     */
    private String deductionSituation;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStayStartTime() {
        return stayStartTime;
    }

    public void setStayStartTime(String stayStartTime) {
        this.stayStartTime = stayStartTime;
    }

    public String getStayEndTime() {
        return stayEndTime;
    }

    public void setStayEndTime(String stayEndTime) {
        this.stayEndTime = stayEndTime;
    }

    public Double getStayDay() {
        return stayDay;
    }

    public void setStayDay(Double stayDay) {
        this.stayDay = stayDay;
    }

    public Double getRentCompanyPay() {
        return rentCompanyPay;
    }

    public void setRentCompanyPay(Double rentCompanyPay) {
        this.rentCompanyPay = rentCompanyPay;
    }

    public Double getRentStaffPay() {
        return rentStaffPay;
    }

    public void setRentStaffPay(Double rentStaffPay) {
        this.rentStaffPay = rentStaffPay;
    }

    public Double getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(Double waterAmount) {
        this.waterAmount = waterAmount;
    }

    public Double getWaterCompanyPay() {
        return waterCompanyPay;
    }

    public void setWaterCompanyPay(Double waterCompanyPay) {
        this.waterCompanyPay = waterCompanyPay;
    }

    public Double getWaterStaffPay() {
        return waterStaffPay;
    }

    public void setWaterStaffPay(Double waterStaffPay) {
        this.waterStaffPay = waterStaffPay;
    }

    public Double getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(Double energyAmount) {
        this.energyAmount = energyAmount;
    }

    public Double getEnergyCompanyPay() {
        return energyCompanyPay;
    }

    public void setEnergyCompanyPay(Double energyCompanyPay) {
        this.energyCompanyPay = energyCompanyPay;
    }

    public Double getEnergyStaffPay() {
        return energyStaffPay;
    }

    public void setEnergyStaffPay(Double energyStaffPay) {
        this.energyStaffPay = energyStaffPay;
    }

    public Double getGasRechargeLines() {
        return gasRechargeLines;
    }

    public void setGasRechargeLines(Double gasRechargeLines) {
        this.gasRechargeLines = gasRechargeLines;
    }

    public Double getGasCompanyPay() {
        return gasCompanyPay;
    }

    public void setGasCompanyPay(Double gasCompanyPay) {
        this.gasCompanyPay = gasCompanyPay;
    }

    public Double getGasStaffPay() {
        return gasStaffPay;
    }

    public void setGasStaffPay(Double gasStaffPay) {
        this.gasStaffPay = gasStaffPay;
    }

    public Double getStaffPayCollect() {
        return staffPayCollect;
    }

    public void setStaffPayCollect(Double staffPayCollect) {
        this.staffPayCollect = staffPayCollect;
    }

    public String getStaffVerify() {
        return staffVerify;
    }

    public void setStaffVerify(String staffVerify) {
        this.staffVerify = staffVerify;
    }

    public Boolean getComprehensiveVerifySituation() {
        return comprehensiveVerifySituation;
    }

    public void setComprehensiveVerifySituation(Boolean comprehensiveVerifySituation) {
        this.comprehensiveVerifySituation = comprehensiveVerifySituation;
    }

    public String getDeductionSituation() {
        return deductionSituation;
    }

    public void setDeductionSituation(String deductionSituation) {
        this.deductionSituation = deductionSituation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}