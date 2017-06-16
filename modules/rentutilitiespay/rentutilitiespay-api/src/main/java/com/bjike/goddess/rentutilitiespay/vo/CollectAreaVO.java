package com.bjike.goddess.rentutilitiespay.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-5-28.
 */
public class CollectAreaVO{
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
     * 房租（元/月）
     */
    private Double rent;
    /**
     * 水费缴纳金额（水费计价金额（元/吨）*用水量）
     */
    private Double waterPayMoney;
    /**
     * 电费缴纳金额（电费计价金额（元/吨）*用电量）
     */
    private Double energyPayMoney;
    /**
     * 管道燃气费充值额度
     */
    private Double gasRechargeLines;
    /**
     * 管理费，卫生费
     */
    private Double fee;
    /**
     * 合计
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

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getWaterPayMoney() {
        return waterPayMoney;
    }

    public void setWaterPayMoney(Double waterPayMoney) {
        this.waterPayMoney = waterPayMoney;
    }

    public Double getEnergyPayMoney() {
        return energyPayMoney;
    }

    public void setEnergyPayMoney(Double energyPayMoney) {
        this.energyPayMoney = energyPayMoney;
    }

    public Double getGasRechargeLines() {
        return gasRechargeLines;
    }

    public void setGasRechargeLines(Double gasRechargeLines) {
        this.gasRechargeLines = gasRechargeLines;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
