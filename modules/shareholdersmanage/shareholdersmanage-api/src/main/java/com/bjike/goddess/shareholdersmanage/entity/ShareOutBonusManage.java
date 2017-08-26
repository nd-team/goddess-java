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
    @Column(name = "ShareOutBonusDate", nullable = false, columnDefinition = "DATE   COMMENT '分红日期'")
    private LocalDate ShareOutBonusDate;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 分红名称
     */
    @Column(name = "ShareOutBonusName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分红名称'")
    private String ShareOutBonusName;

    /**
     * 股权类型
     */
    @Column(name = "equityType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股权类型'")
    private String equityType;

    /**
     * 共派股Double
     */
    @Column(name = "totalSentStocks", nullable = false, columnDefinition = "INT(11)   COMMENT '共派股Double'")
    private Integer totalSentStocks;

    /**
     * 共分红
     */
    @Column(name = "totalShareOutBonus", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '共分红'")
    private Double totalShareOutBonus;

    /**
     * 共缴所得税
     */
    @Column(name = "TotalIncomeTax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '共缴所得税'")
    private Double TotalIncomeTax;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public LocalDate getShareOutBonusDate() {
        return ShareOutBonusDate;
    }

    public void setShareOutBonusDate(LocalDate ShareOutBonusDate) {
        this.ShareOutBonusDate = ShareOutBonusDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getShareOutBonusName() {
        return ShareOutBonusName;
    }

    public void setShareOutBonusName(String ShareOutBonusName) {
        this.ShareOutBonusName = ShareOutBonusName;
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
        return TotalIncomeTax;
    }

    public void setTotalIncomeTax(Double TotalIncomeTax) {
        this.TotalIncomeTax = TotalIncomeTax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}