package com.bjike.goddess.rentutilitiespay.vo;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-23 13:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ImageCollectVO {
    /**
     * 部门
     */
    private String department;

    /**
     * 房租缴付总金额
     */
    private Double rent;

    /**
     * 水费缴付总金额
     */
    private Double waterPayMoney;

    /**
     * 电费缴付总金额
     */
    private Double energyPayMoney;

    /**
     * 燃气费缴付总金额
     */
    private Double gasRechargeLines;

    /**
     * 员工应缴金额
     */
    private Double staffPayCollect;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public Double getStaffPayCollect() {
        return staffPayCollect;
    }

    public void setStaffPayCollect(Double staffPayCollect) {
        this.staffPayCollect = staffPayCollect;
    }
}
