package com.bjike.goddess.rentutilitiespay.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-5-31.
 */
public class CollectNameVO{
    /**
     * 姓名
     */
    private String name;

    /**
     * 员工编号
     */
    private String num;
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
     * 平摊的水费
     */
    private Double waterStaffPay;

    /**
     * 平摊的电费
     */
    private Double energyStaffPay;
    /**
     * 平摊的燃气费
     */
    private Double gasStaffPay;


    /**
     * 预缴水费
     */
    private Double waterStaffPrepay;

    /**
     * 预缴电费
     */
    private Double energyStaffPrepay;

    /**
     * 预缴燃气费
     */
    private Double gasStaffPrepay;
   
    /**
     * 合计
     */
    private String remark;

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

    public Double getWaterStaffPay() {
        return waterStaffPay;
    }

    public void setWaterStaffPay(Double waterStaffPay) {
        this.waterStaffPay = waterStaffPay;
    }

    public Double getEnergyStaffPay() {
        return energyStaffPay;
    }

    public void setEnergyStaffPay(Double energyStaffPay) {
        this.energyStaffPay = energyStaffPay;
    }

    public Double getGasStaffPay() {
        return gasStaffPay;
    }

    public void setGasStaffPay(Double gasStaffPay) {
        this.gasStaffPay = gasStaffPay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getWaterStaffPrepay() {
        return waterStaffPrepay;
    }

    public void setWaterStaffPrepay(Double waterStaffPrepay) {
        this.waterStaffPrepay = waterStaffPrepay;
    }

    public Double getEnergyStaffPrepay() {
        return energyStaffPrepay;
    }

    public void setEnergyStaffPrepay(Double energyStaffPrepay) {
        this.energyStaffPrepay = energyStaffPrepay;
    }

    public Double getGasStaffPrepay() {
        return gasStaffPrepay;
    }

    public void setGasStaffPrepay(Double gasStaffPrepay) {
        this.gasStaffPrepay = gasStaffPrepay;
    }
}
