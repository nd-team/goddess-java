package com.bjike.goddess.festival.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 节假日礼品福利
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:17 ]
 * @Description: [ 节假日礼品福利 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "festival_welfare")
public class Welfare extends BaseEntity {

    /**
     * 节日名称
     */
    @Column(name = "festivalName",  columnDefinition = "VARCHAR(255)   COMMENT '节日名称'")
    private String festivalName;

    /**
     * 礼品类型
     */
    @Column(name = "giftType",  columnDefinition = "VARCHAR(255)   COMMENT '礼品类型'")
    private String giftType;

    /**
     * 节日礼品
     */
    @Column(name = "gift",  columnDefinition = "VARCHAR(255)   COMMENT '节日礼品'")
    private String gift;

    /**
     * 单价(元)
     */
    @Column(name = "price",  columnDefinition = "DECIMAL(10,2)   COMMENT '单价(元)'")
    private Double price;

    /**
     * 数量(人)
     */
    @Column(name = "number",  columnDefinition = "DECIMAL(10,2)   COMMENT '数量(人)'")
    private Double number;

    /**
     * 总数量(单位)
     */
    @Column(name = "totalNum",  columnDefinition = "DECIMAL(10,2)   COMMENT '总数量(单位)'")
    private Double totalNum;

    /**
     * 总金额(元)
     */
    @Column(name = "totalMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '总金额(元)'")
    private Double totalMoney;

    /**
     * 法定节假日放假方案
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "holidayProgramme_id",  columnDefinition = "VARCHAR(36)   COMMENT '法定节假日放假方案'")
    private HolidayProgramme holidayProgramme;


    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Double totalNum) {
        this.totalNum = totalNum;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public HolidayProgramme getHolidayProgramme() {
        return holidayProgramme;
    }

    public void setHolidayProgramme(HolidayProgramme holidayProgramme) {
        this.holidayProgramme = holidayProgramme;
    }
}