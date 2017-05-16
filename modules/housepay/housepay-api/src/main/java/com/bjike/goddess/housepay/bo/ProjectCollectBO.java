package com.bjike.goddess.housepay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-5-13.
 */
public class ProjectCollectBO extends BaseBO{
    /**
     * 项目
     */
    private String project;
    /**
     * 时间
     */
    private String payTime;
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
     * 合计
     */
    private Double total;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
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
}
