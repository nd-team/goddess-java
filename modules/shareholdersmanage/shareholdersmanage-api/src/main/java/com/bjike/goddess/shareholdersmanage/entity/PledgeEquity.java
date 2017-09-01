package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 质押股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:32 ]
 * @Description: [ 质押股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_pledgeequity")
public class PledgeEquity extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 出资人
     */
    @Column(name = "investor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '出资人'")
    private String investor;

    /**
     * 债权人
     */
    @Column(name = "creditor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '债权人'")
    private String creditor;

    /**
     * 担保人
     */
    @Column(name = "guarantor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '担保人'")
    private String guarantor;

    /**
     * 股权类型
     */
    @Column(name = "equityType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股权类型'")
    private String equityType;

    /**
     * 股权数量
     */
    @Column(name = "holdNum", nullable = false, columnDefinition = "INT(11)   COMMENT '股权数量'")
    private Integer holdNum;

    /**
     * 金额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double amount;

    /**
     * 出资日期
     */
    @Column(name = "pledgeDate", nullable = false, columnDefinition = "DATE   COMMENT '出资日期'")
    private LocalDate pledgeDate;

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

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPledgeDate() {
        return pledgeDate;
    }

    public void setPledgeDate(LocalDate pledgeDate) {
        this.pledgeDate = pledgeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}