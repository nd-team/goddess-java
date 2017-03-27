package com.bjike.goddess.oilcardmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 油卡充值记录
 * @Author: [Jason]
 * @Date: [17-3-15 上午10:48]
 * @Package:[ com.bjike.goddess.oilcardmanage.entity ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "oilcardmanage_recharge")
public class OilCardRecharge extends BaseEntity{

    /**
     * 数据状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '数据状态'", nullable = false, insertable = false)
    private Status status;

    /**
     * 油卡信息Id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '油卡信息Id'",nullable = false)
    private String oilCardBasicId;

    /**
     * 充值日期
     */
    @Column(columnDefinition = "DATETIME COMMENT '充值日期'",nullable = false)
    private LocalDateTime rechargeDate;

    /**
     * 充值金额
     */
    @Column(columnDefinition = "DECIMAL(10,2) COMMENT '充值金额'",nullable = false)
    private Double rechargeMoney;

    /**
     * 充值人
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '充值人'",nullable = false)
    private String rechargeUser;

    /**
     * 充值方式
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '充值方式'",nullable = false)
    private String rechargeWay;

    /**
     * 期初金额
     */
    @Column(columnDefinition = "DECIMAL(10,2) COMMENT '期初金额'",nullable = false)
    private Double cycleEarlyMoney;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOilCardBasicId() {
        return oilCardBasicId;
    }

    public void setOilCardBasicId(String oilCardBasicId) {
        this.oilCardBasicId = oilCardBasicId;
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

    public Double getCycleEarlyMoney() {
        return cycleEarlyMoney;
    }

    public void setCycleEarlyMoney(Double cycleEarlyMoney) {
        this.cycleEarlyMoney = cycleEarlyMoney;
    }
}
