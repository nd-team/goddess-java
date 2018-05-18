package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 职权因素层权重
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:50 ]
 * @Description: [ 职权因素层权重 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_funpowerweight")
public class FunPowerWeight extends BaseEntity {

    /**
     * 职权类型名称
     */
    @Column(name = "funPowerTypeName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职权类型名称'")
    private String funPowerTypeName;

    /**
     * 职权类型权重
     */
    @Column(name = "funPowerTypeWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '职权类型权重'")
    private Double funPowerTypeWeight;


    public String getFunPowerTypeName() {
        return funPowerTypeName;
    }

    public void setFunPowerTypeName(String funPowerTypeName) {
        this.funPowerTypeName = funPowerTypeName;
    }

    public Double getFunPowerTypeWeight() {
        return funPowerTypeWeight;
    }

    public void setFunPowerTypeWeight(Double funPowerTypeWeight) {
        this.funPowerTypeWeight = funPowerTypeWeight;
    }
}