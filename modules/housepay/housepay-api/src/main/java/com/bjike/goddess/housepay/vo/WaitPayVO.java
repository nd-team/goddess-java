package com.bjike.goddess.housepay.vo;

import com.bjike.goddess.housepay.enums.PayStatus;

/**
 * 等待付款表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:11 ]
 * @Description: [ 等待付款表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitPayVO {

    /**
     * id
     */
    private String id;
    /**
     * 缴费时间
     */
    private String payTime;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目
     */
    private String project;

    /**
     * 房租地址
     */
    private String rentAddress;

    /**
     * 租金
     */
    private Double rent;

    /**
     * 水费
     */
    private Double water;

    /**
     * 电费
     */
    private Double energy;

    /**
     * 管理费
     */
    private Double fee;

    /**
     * 其他费用
     */
    private Double otherFee;

    /**
     * 合计（租金+水费+电费+管理费+其他费用）
     */
    private Double total;

    /**
     * 房东姓名
     */
    private String landlord;

    /**
     * 联系电话
     */
    private String contact;

    /**
     * 交租确认
     */
    private String taxesConfirm;

    /**
     * 是否付款
     */
    private PayStatus pay;

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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setRentAddress(String rentAddress) {
        this.rentAddress = rentAddress;
    }

    public String getRentAddress() {
        return rentAddress;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
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

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public String getTaxesConfirm() {
        return taxesConfirm;
    }

    public void setTaxesConfirm(String taxesConfirm) {
        this.taxesConfirm = taxesConfirm;
    }

    public PayStatus getPay() {
        return pay;
    }

    public void setPay(PayStatus pay) {
        this.pay = pay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}