package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 分红管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_shareoutbonusmanage")
public class ShareOutBonusManage extends BaseEntity {

    /**
     * 分红日期
     */
    @Column(name = "shareOutBonusDate", nullable = false, columnDefinition = "DATE   COMMENT '分红日期'")
    private LocalDate shareOutBonusDate;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 分红名称
     */
    @Column(name = "ShareOutBonusName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分红名称'")
    private String shareOutBonusName;

    /**
     * 股权类型
     */
    @Column(name = "equityType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股权类型'")
    private String equityType;

    /**
     * 共派股Double
     */
    @Column(name = "totalSentStocks", nullable = false, columnDefinition = "INT(11)   COMMENT '共派股'")
    private Integer totalSentStocks;

    /**
     * 每股分红
     */
    @Column(name = "perShareDividends", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '每股分红'")
    private Double perShareDividends;
    /**
     * 共分红
     */
    @Column(name = "totalShareOutBonus", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '共分红'")
    private Double totalShareOutBonus;

    /**
     * 共缴所得税
     */
    @Column(name = "totalIncomeTax",nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '共缴所得税'")
    private Double totalIncomeTax;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public LocalDate getShareOutBonusDate() {
        return shareOutBonusDate;
    }

    public void setShareOutBonusDate(LocalDate shareOutBonusDate) {
        this.shareOutBonusDate = shareOutBonusDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getShareOutBonusName() {
        return shareOutBonusName;
    }

    public void setShareOutBonusName(String shareOutBonusName) {
        this.shareOutBonusName = shareOutBonusName;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getTotalSentStocks() {
        return totalSentStocks;
    }

    public void setTotalSentStocks(Integer totalSentStocks) {
        this.totalSentStocks = totalSentStocks;
    }

    public Double getTotalShareOutBonus() {
        return totalShareOutBonus;
    }

    public void setTotalShareOutBonus(Double totalShareOutBonus) {
        this.totalShareOutBonus = totalShareOutBonus;
    }

    public Double getTotalIncomeTax() {
        return totalIncomeTax;
    }

    public void setTotalIncomeTax(Double totalIncomeTax) {
        this.totalIncomeTax = totalIncomeTax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getPerShareDividends() {
        return perShareDividends;
    }

    public void setPerShareDividends(Double perShareDividends) {
        this.perShareDividends = perShareDividends;
    }
}