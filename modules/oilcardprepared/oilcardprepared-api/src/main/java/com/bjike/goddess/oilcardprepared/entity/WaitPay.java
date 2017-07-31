package com.bjike.goddess.oilcardprepared.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 等待付款
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-12 11:05 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "oilcardprepared_waitpay")
public class WaitPay extends BaseEntity {

    /**
     * 油卡编号
     */
    @Column(name = "oilCardCode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '油卡编号'")
    private String oilCardCode;

    /**
     * 卡号
     */
    @Column(name = "oilCardNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '卡号'")
    private String oilCardNumber;

    /**
     * 充值日期
     */
    @Column(name = "rechargeDate", columnDefinition = "DATETIME   COMMENT '充值日期'")
    private LocalDateTime rechargeDate;

    /**
     * 充值金额
     */
    @Column(name = "rechargeMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '充值金额'")
    private Double rechargeMoney;

    /**
     * 充值人
     */
    @Column(name = "rechargeUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '充值人'")
    private String rechargeUser;

    /**
     * 充值方式
     */
    @Column(name = "rechargeWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '充值方式'")
    private String rechargeWay;

    /**
     * 初始余额
     */
    @Column(name = "oilCardBalance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '初始余额'")
    private Double oilCardBalance;

    /**
     * 是否付款
     */
    @Column(name = "is_pay",  columnDefinition = "TINYINT(1) COMMENT '是否付款'")
    private Boolean pay;

    /**
     * 出车等待付款id
     */
    @Column(name = "wait_id", unique = true, columnDefinition = "VARCHAR(36)   COMMENT '出车等待付款id'")
    private String waitId;

    /**
     * 月份
     */
    @Column(name = "month", columnDefinition = "INT(11) COMMENT '月份'")
    private Integer month;

    /**
     * 年份
     */
    @Column(name = "year", columnDefinition = "INT(11) COMMENT '年份'")
    private Integer year;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getOilCardCode() {
        return oilCardCode;
    }

    public void setOilCardCode(String oilCardCode) {
        this.oilCardCode = oilCardCode;
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public LocalDateTime getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(LocalDateTime rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getRechargeUser() {
        return rechargeUser;
    }

    public void setRechargeUser(String rechargeUser) {
        this.rechargeUser = rechargeUser;
    }

    public String getRechargeWay() {
        return rechargeWay;
    }

    public void setRechargeWay(String rechargeWay) {
        this.rechargeWay = rechargeWay;
    }

    public Double getOilCardBalance() {
        return oilCardBalance;
    }

    public void setOilCardBalance(Double oilCardBalance) {
        this.oilCardBalance = oilCardBalance;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public String getWaitId() {
        return waitId;
    }

    public void setWaitId(String waitId) {
        this.waitId = waitId;
    }
}