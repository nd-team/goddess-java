package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 客户接触阶段权重设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:46 ]
 * @Description: [ 客户接触阶段权重设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_customercontactweightset")
public class CustomerContactWeightSet extends BaseEntity {

    /**
     * 客户接触阶段类型
     */
    @Column(name = "customerContactType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户接触阶段类型'")
    private String customerContactType;

    /**
     * 客户接触阶段类型权重
     */
    @Column(name = "customerContactTypeWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '客户接触阶段类型权重'")
    private Double customerContactTypeWeight;


    public String getCustomerContactType() {
        return customerContactType;
    }

    public void setCustomerContactType(String customerContactType) {
        this.customerContactType = customerContactType;
    }

    public Double getCustomerContactTypeWeight() {
        return customerContactTypeWeight;
    }

    public void setCustomerContactTypeWeight(Double customerContactTypeWeight) {
        this.customerContactTypeWeight = customerContactTypeWeight;
    }
}