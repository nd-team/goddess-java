package com.bjike.goddess.bonusmoneyperparepay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 等待付款业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 等待付款业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitingPayBO extends BaseBO {

    /**
     * 年份
     */
    private Integer years;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 支付状态String
     */
    private String payStatus;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 科目
     */
    private String subjects;

    /**
     * 准备金
     */
    private Double reserve;

    /**
     * 支付金额
     */
    private Double payMoney;

    /**
     * 是否付款
     */
    private String turntable;

    /**
     * 付款时间
     */
    private String differenceTime;

    /**
     * 付款人
     */
    private String payAuthor;
    /**
     * 准备金id
     */
    private String perpareId;

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public String getTurntable() {
        return turntable;
    }

    public void setTurntable(String turntable) {
        this.turntable = turntable;
    }

    public String getDifferenceTime() {
        return differenceTime;
    }

    public void setDifferenceTime(String differenceTime) {
        this.differenceTime = differenceTime;
    }

    public String getPayAuthor() {
        return payAuthor;
    }

    public void setPayAuthor(String payAuthor) {
        this.payAuthor = payAuthor;
    }

    public String getPerpareId() {
        return perpareId;
    }

    public void setPerpareId(String perpareId) {
        this.perpareId = perpareId;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}