package com.bjike.goddess.projectmarketfee.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 预警设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:53 ]
 * @Description: [ 预警设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmarketfee_warn")
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