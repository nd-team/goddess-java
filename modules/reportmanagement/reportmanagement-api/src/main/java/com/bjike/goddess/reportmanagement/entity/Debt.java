package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 负债表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_debt")
public class Debt extends BaseEntity {

    /**
     * 起始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '起始时间'")
    private LocalDate startTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '结束时间'")
    private LocalDate endTime;

    /**
     * 负债和所有者权益(或股东权益)
     */
    @Column(name = "debt", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '负债和所有者权益(或股东权益)'")
    private String debt;

    /**
     * 负债年初数
     */
    @Column(name = "beginDebt", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '负债年初数'")
    private Double beginDebt;

    /**
     * 负债期末数
     */
    @Column(name = "endAsset", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '负债期末数'")
    private Double endAsset;


    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public Double getBeginDebt() {
        return beginDebt;
    }

    public void setBeginDebt(Double beginDebt) {
        this.beginDebt = beginDebt;
    }

    public Double getEndAsset() {
        return endAsset;
    }

    public void setEndAsset(Double endAsset) {
        this.endAsset = endAsset;
    }
}