package com.bjike.goddess.housepay.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.housepay.enums.PayStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 等待付款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:11 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitPayTO extends BaseTO {

    /**
     * 缴费时间
     */
    @NotBlank(message = "缴费时间不能为空")
    private String payTime;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空")
    private String area;

    /**
     * 项目
     */
    @NotBlank(message = "项目不能为空")
    private String project;

    /**
     * 房租地址
     */
    @NotBlank(message = "房租地址不能为空")
    private String rentAddress;

    /**
     * 租金
     */
    @NotNull(message = "租金不能为空")
    private Double rent;

    /**
     * 水费
     */
    @NotNull(message = "水费不能为空")
    private Double water;

    /**
     * 电费
     */
    @NotNull(message = "电费不能为空")
    private Double energy;

    /**
     * 管理费
     */
    @NotNull(message = "管理费不能为空")
    private Double fee;

    /**
     * 其他费用
     */
    @NotNull(message = "其他费用不能为空")
    private Double otherFee;

    /**
     * 合计（租金+水费+电费+管理费+其他费用）
     */
    @NotNull(message = "合计（租金+水费+电费+管理费+其他费用）不能为空")
    private Double total;

    /**
     * 房东姓名
     */
    @NotBlank(message = "房东姓名不能为空")
    private String landlord;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String contact;

    /**
     * 交租确认
     */
    @NotBlank(message = "交租确认不能为空")
    private String taxesConfirm;

    /**
     * 是否付款
     */
    @NotNull(message = "是否付款不能为空")
    private PayStatus pay;

    /**
     * 备注
     */
    private String remark;


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

    public String getRentAddress() {
        return rentAddress;
    }

    public void setRentAddress(String rentAddress) {
        this.rentAddress = rentAddress;
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