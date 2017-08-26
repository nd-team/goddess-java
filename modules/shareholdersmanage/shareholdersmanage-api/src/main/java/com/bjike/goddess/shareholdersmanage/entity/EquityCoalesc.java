package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 股权合并
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:24 ]
 * @Description: [ 股权合并 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_equitycoalesc")
public class EquityCoalesc extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 合并方
     */
    @Column(name = "combined", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合并方'")
    private String combined;

    /**
     * 被合并方
     */
    @Column(name = "beCombined", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '被合并方'")
    private String beCombined;

    /**
     * 合并方式
     */
    @Column(name = "coalescWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合并方式'")
    private String coalescWay;

    /**
     * 合并日期
     */
    @Column(name = "coalescDate", nullable = false, columnDefinition = "DATE   COMMENT '合并日期'")
    private LocalDate coalescDate;

    /**
     * 占股比例
     */
    @Column(name = "percentage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '占股比例'")
    private Double percentage;

    /**
     * 金额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double amount;

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

    public String getCombined() {
        return combined;
    }

    public void setCombined(String combined) {
        this.combined = combined;
    }

    public String getBeCombined() {
        return beCombined;
    }

    public void setBeCombined(String beCombined) {
        this.beCombined = beCombined;
    }

    public String getCoalescWay() {
        return coalescWay;
    }

    public void setCoalescWay(String coalescWay) {
        this.coalescWay = coalescWay;
    }

    public LocalDate getCoalescDate() {
        return coalescDate;
    }

    public void setCoalescDate(LocalDate coalescDate) {
        this.coalescDate = coalescDate;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}