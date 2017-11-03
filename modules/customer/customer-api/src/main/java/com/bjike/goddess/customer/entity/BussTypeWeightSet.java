package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务类型权重设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:24 ]
 * @Description: [ 业务类型权重设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_busstypeweightset")
public class BussTypeWeightSet extends BaseEntity {

    /**
     * 业务类型
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 业务类型权重
     */
    @Column(name = "businessTypeWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '业务类型权重'")
    private Double businessTypeWeight;

    /**
     * 业务方向
     */
    @Column(name = "businessWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向'")
    private String businessWay;

    /**
     * 业务方向权重
     */
    @Column(name = "businessWayWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '业务方向权重'")
    private Double businessWayWeight;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Double getBusinessTypeWeight() {
        return businessTypeWeight;
    }

    public void setBusinessTypeWeight(Double businessTypeWeight) {
        this.businessTypeWeight = businessTypeWeight;
    }

    public String getBusinessWay() {
        return businessWay;
    }

    public void setBusinessWay(String businessWay) {
        this.businessWay = businessWay;
    }

    public Double getBusinessWayWeight() {
        return businessWayWeight;
    }

    public void setBusinessWayWeight(Double businessWayWeight) {
        this.businessWayWeight = businessWayWeight;
    }
}