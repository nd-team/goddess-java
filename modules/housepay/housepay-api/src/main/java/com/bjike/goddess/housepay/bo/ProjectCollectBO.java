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
     * 年份
     */
    private String year;
    /**
     * 月份
     */
    private String month;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
