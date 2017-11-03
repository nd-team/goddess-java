package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 时效性因素层权重
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:52 ]
 * @Description: [ 时效性因素层权重 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_timelinessfactorweight")
public class TimelinessFactorWeight extends BaseEntity {

    /**
     * 时效性类型名称
     */
    @Column(name = "timelinessName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '时效性类型名称'")
    private String timelinessName;

    /**
     * 时效性类型权重
     */
    @Column(name = "timelinessWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '时效性类型权重'")
    private Double timelinessWeight;


    public String getTimelinessName() {
        return timelinessName;
    }

    public void setTimelinessName(String timelinessName) {
        this.timelinessName = timelinessName;
    }

    public Double getTimelinessWeight() {
        return timelinessWeight;
    }

    public void setTimelinessWeight(Double timelinessWeight) {
        this.timelinessWeight = timelinessWeight;
    }
}