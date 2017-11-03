package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 一层因素层权重
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:28 ]
 * @Description: [ 一层因素层权重 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_firstfactorweight")
public class FirstFactorWeight extends BaseEntity {

    /**
     * 一级因素层因素名
     */
    @Column(name = "firstFactorName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '一级因素层因素名'")
    private String firstFactorName;

    /**
     * 一级因素层因素权重
     */
    @Column(name = "firstFactorWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '一级因素层因素权重'")
    private Double firstFactorWeight;


    public String getFirstFactorName() {
        return firstFactorName;
    }

    public void setFirstFactorName(String firstFactorName) {
        this.firstFactorName = firstFactorName;
    }

    public Double getFirstFactorWeight() {
        return firstFactorWeight;
    }

    public void setFirstFactorWeight(Double firstFactorWeight) {
        this.firstFactorWeight = firstFactorWeight;
    }
}