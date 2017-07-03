package com.bjike.goddess.budget.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 预警
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-17 10:35 ]
 * @Description: [ 预警 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "budget_warn")
public class Warn extends BaseEntity {

    /**
     * 预警的值
     */
    @Column(name = "warnValue", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预警的值'")
    private Double warnValue;


    public Double getWarnValue() {
        return warnValue;
    }

    public void setWarnValue(Double warnValue) {
        this.warnValue = warnValue;
    }
}