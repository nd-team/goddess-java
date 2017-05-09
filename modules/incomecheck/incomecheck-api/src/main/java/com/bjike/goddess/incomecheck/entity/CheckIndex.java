package com.bjike.goddess.incomecheck.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:49 ]
 * @Description: [ 指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "incomecheck_checkindex")
public class CheckIndex extends BaseEntity {

    /**
     * 收入比例差异指标
     */
    @Column(name = "incomeRate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '收入比例差异指标'")
    private Double incomeRate;

    /**
     * 收入比例差异指标
     */
    @Column(name = "completeRate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '收入比例差异指标'")
    private Double completeRate;

    public Double getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(Double incomeRate) {
        this.incomeRate = incomeRate;
    }

    public Double getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(Double completeRate) {
        this.completeRate = completeRate;
    }
}