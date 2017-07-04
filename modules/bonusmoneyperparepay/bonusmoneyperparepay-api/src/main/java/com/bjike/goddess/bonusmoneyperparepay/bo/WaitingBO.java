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
public class WaitingBO extends BaseBO {

    private String yearsMonth;

    /**
     * 支付状态
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
     * 付款时间
     */
    private String difference;

    /**
     * 付款人
     */
    private String payAuthor;

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

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getPayAuthor() {
        return payAuthor;
    }

    public void setPayAuthor(String payAuthor) {
        this.payAuthor = payAuthor;
    }

    public String getYearsMonth() {
        return yearsMonth;
    }

    public void setYearsMonth(String yearsMonth) {
        this.yearsMonth = yearsMonth;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}