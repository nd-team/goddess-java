package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 股权转让
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:32 ]
 * @Description: [ 股权转让 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_equitytransfer")
public class EquityTransfer extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 转让人
     */
    @Column(name = "assignor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '转让人'")
    private String assignor;

    /**
     * 受让人
     */
    @Column(name = "receiving", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '受让人'")
    private String receiving;

    /**
     * 股权类型
     */
    @Column(name = "equityType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股权类型'")
    private String equityType;

    /**
     * 转让股数/万
     */
    @Column(name = "transferHoldNum", nullable = false, columnDefinition = "INT(11)   COMMENT '转让股数/万'")
    private Integer transferHoldNum;

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
     * 转让时间
     */
    @Column(name = "transferDate", nullable = false, columnDefinition = "DATE   COMMENT '转让时间'")
    private LocalDate transferDate;

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

    public String getAssignor() {
        return assignor;
    }

    public void setAssignor(String assignor) {
        this.assignor = assignor;
    }

    public String getReceiving() {
        return receiving;
    }

    public void setReceiving(String receiving) {
        this.receiving = receiving;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getTransferHoldNum() {
        return transferHoldNum;
    }

    public void setTransferHoldNum(Integer transferHoldNum) {
        this.transferHoldNum = transferHoldNum;
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

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}