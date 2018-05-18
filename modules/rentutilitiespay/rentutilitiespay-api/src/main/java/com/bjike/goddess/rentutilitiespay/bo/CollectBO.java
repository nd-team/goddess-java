package com.bjike.goddess.rentutilitiespay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 汇总业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-20 03:59 ]
* @Description:	[ 汇总业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CollectBO extends BaseBO {

    /**
     * 租房地址
     */
    private String  address;

    /**
     * 房租缴付总金额
     */
    private Double  rent;

    /**
     * 水费缴付总金额
     */
    private Double  waterPayMoney;

    /**
     * 电费缴付总金额
     */
    private Double  energyPayMoney;

    /**
     * 燃气费缴付总金额
     */
    private Double  gasRechargeLines;

    /**
     * 管理费、卫生费总金额
     */
    private Double fee;

    /**
     * 平摊水费金额
     */
    private Double waterStaffPay;

    /**
     * 平摊电费金额
     */
    private Double energyStaffPay;

    /**
     * 平摊燃气费金额
     */
    private Double gasStaffPay;

    /**
     * 预缴费用金额
     */
    private Double waterStaffPrepay;

    /**
     * 预缴水费金额
     */
    private Double energyStaffPrepay;

    /**
     * 预缴燃气费金额
     */
    private Double gasStaffPrepay;

    /**
     * 员工应缴金额
     */
    private Double staffPayCollect;

    /**
     * 员工核实确认数
     */
    private Integer staffVerifySureNumber;

    /**
     * 员工核实有误数
     */
    private Integer staffVerifyWrongNumber;

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

    public Double getStaffPayCollect() {
        return staffPayCollect;
    }

    public void setStaffPayCollect(Double staffPayCollect) {
        this.staffPayCollect = staffPayCollect;
    }

    public Integer getStaffVerifySureNumber() {
        return staffVerifySureNumber;
    }

    public void setStaffVerifySureNumber(Integer staffVerifySureNumber) {
        this.staffVerifySureNumber = staffVerifySureNumber;
    }

    public Integer getStaffVerifyWrongNumber() {
        return staffVerifyWrongNumber;
    }

    public void setStaffVerifyWrongNumber(Integer staffVerifyWrongNumber) {
        this.staffVerifyWrongNumber = staffVerifyWrongNumber;
    }
}