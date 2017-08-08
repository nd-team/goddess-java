package com.bjike.goddess.staffshares.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 干股分红表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 11:14 ]
 * @Description: [ 干股分红表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffshares_dividends")
public class Dividends extends BaseEntity {

    /**
     * 方案代码
     */
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '方案代码'")
    private String code;

    /**
     * 方案名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '方案名称'")
    private String name;

    /**
     * 税后利润
     */
    @Column(name = "taxProfit", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税后利润'")
    private Double taxProfit;

    /**
     * 持股数
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '持股数'")
    private int num;

    /**
     * 总股本
     */
    @Column(name = "totalEquity", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总股本'")
    private Double totalEquity;

    /**
     * 每股收益
     */
    @Column(name = "earnings", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '每股收益'")
    private Double earnings;

    /**
     * 总收益/分红
     */
    @Column(name = "totalEarnings", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总收益/分红'")
    private Double totalEarnings;

    /**
     * 购入时间
     */
    @Column(name = "buyTime", nullable = false, columnDefinition = "DATETIME   COMMENT '购入时间'")
    private LocalDateTime buyTime;

    /**
     * 持股时长
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '持股时长'")
    private int duration;

    /**
     * 分红发放时间
     */
    @Column(name = "dividendTime", nullable = false, columnDefinition = "DATE   COMMENT '分红发放时间'")
    private LocalDate dividendTime;

    /**
     * 本次红利收益时间段
     */
    @Column(name = "time", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '本次红利收益时间段'")
    private String time;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 持股人确认情况
     */
    @Column(name = "is_situation", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '持股人确认情况'", insertable = false)
    private Boolean situation;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTaxProfit() {
        return taxProfit;
    }

    public void setTaxProfit(Double taxProfit) {
        this.taxProfit = taxProfit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(Double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public LocalDateTime getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(LocalDateTime buyTime) {
        this.buyTime = buyTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDividendTime() {
        return dividendTime;
    }

    public void setDividendTime(LocalDate dividendTime) {
        this.dividendTime = dividendTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getSituation() {
        return situation;
    }

    public void setSituation(Boolean situation) {
        this.situation = situation;
    }
}