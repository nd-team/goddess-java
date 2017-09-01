package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 冻结股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:39 ]
 * @Description: [ 冻结股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_freezeequity")
public class FreezeEquity extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 股权类型
     */
    @Column(name = "equityType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股权类型'")
    private String equityType;

    /**
     * 股份数量
     */
    @Column(name = "totalHoldNum", nullable = false, columnDefinition = "INT(11)   COMMENT '股份数量'")
    private Integer totalHoldNum;

    /**
     * 金额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double amount;

    /**
     * 冻结日期
     */
    @Column(name = "freezeDate", nullable = false, columnDefinition = "DATE   COMMENT '冻结日期'")
    private LocalDate freezeDate;

    /**
     * 冻结原因
     */
    @Column(name = "freezeWhy", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '冻结原因'")
    private String freezeWhy;

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

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getTotalHoldNum() {
        return totalHoldNum;
    }

    public void setTotalHoldNum(Integer totalHoldNum) {
        this.totalHoldNum = totalHoldNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(LocalDate freezeDate) {
        this.freezeDate = freezeDate;
    }

    public String getFreezeWhy() {
        return freezeWhy;
    }

    public void setFreezeWhy(String freezeWhy) {
        this.freezeWhy = freezeWhy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}