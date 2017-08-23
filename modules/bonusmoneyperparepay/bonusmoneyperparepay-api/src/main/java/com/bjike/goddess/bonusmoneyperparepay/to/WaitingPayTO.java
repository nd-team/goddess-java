package com.bjike.goddess.bonusmoneyperparepay.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 等待付款
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitingPayTO extends BaseTO {

    public interface testPay {
    }

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
     * 准备金
     */
    private Double reserve;

    /**
     * 支付金额
     */
    @NotNull(groups = {WaitingPayTO.testPay.class}, message = "支付金额不能为空")
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

    /**
     * 科目
     */
    private String subjects;

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