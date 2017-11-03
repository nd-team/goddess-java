package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 亲密度因素层权重
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:09 ]
 * @Description: [ 亲密度因素层权重 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_closenessfoactorweight")
public class ClosenessFoactorWeight extends BaseEntity {

    /**
     * 亲密度类型名称
     */
    @Column(name = "closenessName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '亲密度类型名称'")
    private String closenessName;

    /**
     * 亲密度类型权重
     */
    @Column(name = "closenessWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '亲密度类型权重'")
    private Double closenessWeight;


    public String getClosenessName() {
        return closenessName;
    }

    public void setClosenessName(String closenessName) {
        this.closenessName = closenessName;
    }

    public Double getClosenessWeight() {
        return closenessWeight;
    }

    public void setClosenessWeight(Double closenessWeight) {
        this.closenessWeight = closenessWeight;
    }
}