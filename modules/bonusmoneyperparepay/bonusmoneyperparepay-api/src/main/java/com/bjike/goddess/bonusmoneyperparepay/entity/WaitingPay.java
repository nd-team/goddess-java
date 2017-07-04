package com.bjike.goddess.bonusmoneyperparepay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 等待付款
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bonusmoneyperparepay_waitingpay")
public class WaitingPay extends BaseEntity {

    /**
     * 年份
     */
    @Column(name = "years", nullable = false, columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer years;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

    /**
     * 支付状态
     */
    @Column(name = "payStatus", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '支付状态'")
    private String payStatus;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 科目
     */
    @Column(name = "subjects", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目'")
    private String subjects;

    /**
     * 准备金
     */
    @Column(name = "reserve", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '准备金'")
    private Double reserve;

    /**
     * 支付金额
     */
    @Column(name = "payMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '支付金额'")
    private Double payMoney;

    /**
     * 是否付款
     */
    @Column(name = "turntable", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否付款'")
    private String turntable;

    /**
     * 付款时间
     */
    @Column(name = "difference", columnDefinition = "DATETIME   COMMENT '付款时间'")
    private LocalDateTime difference;

    /**
     * 付款人
     */
    @Column(name = "payAuthor",  columnDefinition = "VARCHAR(255)   COMMENT '付款人'")
    private String payAuthor;

    /**
     * 准备金id
     */
    @Column(name = "perpareId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '准备金id'")
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

    public LocalDateTime getDifference() {
        return difference;
    }

    public void setDifference(LocalDateTime difference) {
        this.difference = difference;
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