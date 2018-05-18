package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 新增股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:17 ]
 * @Description: [ 新增股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_newequity")
public class NewEquity extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 股东姓名
     */
    @Column(name = "shareholderName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股东姓名'")
    private String shareholderName;

    /**
     * 股权类型
     */
    @Column(name = "equityType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股权类型'")
    private String equityType;

    /**
     * 新增股数
     */
    @Column(name = "newHoldNum", nullable = false, columnDefinition = "INT(11)   COMMENT '新增股数'")
    private Integer newHoldNum;

    /**
     * 每股价格/元
     */
    @Column(name = "perSharePrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '每股价格/元'")
    private Double perSharePrice;

    /**
     * 金额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double amount;

    /**
     * 新增时间
     */
    @Column(name = "newSaveDate", nullable = false, columnDefinition = "DATE   COMMENT '新增时间'")
    private LocalDate newSaveDate;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getNewHoldNum() {
        return newHoldNum;
    }

    public void setNewHoldNum(Integer newHoldNum) {
        this.newHoldNum = newHoldNum;
    }

    public Double getPerSharePrice() {
        return perSharePrice;
    }

    public void setPerSharePrice(Double perSharePrice) {
        this.perSharePrice = perSharePrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getNewSaveDate() {
        return newSaveDate;
    }

    public void setNewSaveDate(LocalDate newSaveDate) {
        this.newSaveDate = newSaveDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}