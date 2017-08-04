package com.bjike.goddess.staffing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 工资区间
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 01:50 ]
 * @Description: [ 工资区间 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffing_salary")
public class Salary extends BaseEntity {

    /**
     * 区间最低工资
     */
    @Column(name = "min", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '区间最低工资'")
    private Double min;

    /**
     * 区间最高工资
     */
    @Column(name = "max", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '区间最高工资'")
    private Double max;

    /**
     * 说明
     */
    @Column(name = "rate",columnDefinition = "VARCHAR(255)   COMMENT '说明'")
    private String rate;


    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}