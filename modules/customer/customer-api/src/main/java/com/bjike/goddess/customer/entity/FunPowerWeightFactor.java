package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 职权因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:37 ]
 * @Description: [ 职权因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_funpowerweightfactor")
public class FunPowerWeightFactor extends BaseEntity {

    /**
     * 低低比
     */
    @Column(name = "lowLow", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '低低比'")
    private Double lowLow;

    /**
     * 低中比
     */
    @Column(name = "lowMedium", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '低中比'")
    private Double lowMedium;

    /**
     * 低高比
     */
    @Column(name = "lowHeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '低高比'")
    private Double lowHeight;

    /**
     * 中低比
     */
    @Column(name = "mediumLow", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '中低比'")
    private Double mediumLow;

    /**
     * 中中比
     */
    @Column(name = "mediumMedium", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '中中比'")
    private Double mediumMedium;

    /**
     * 中高比
     */
    @Column(name = "mediumHeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '中高比'")
    private Double mediumHeight;

    /**
     * 高低比
     */
    @Column(name = "heightLow", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '高低比'")
    private Double heightLow;

    /**
     * 高中比
     */
    @Column(name = "heightMedium", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '高中比'")
    private Double heightMedium;

    /**
     * 高高比
     */
    @Column(name = "heightHeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '高高比'")
    private Double heightHeight;


    public Double getLowLow() {
        return lowLow;
    }

    public void setLowLow(Double lowLow) {
        this.lowLow = lowLow;
    }

    public Double getLowMedium() {
        return lowMedium;
    }

    public void setLowMedium(Double lowMedium) {
        this.lowMedium = lowMedium;
    }

    public Double getLowHeight() {
        return lowHeight;
    }

    public void setLowHeight(Double lowHeight) {
        this.lowHeight = lowHeight;
    }

    public Double getMediumLow() {
        return mediumLow;
    }

    public void setMediumLow(Double mediumLow) {
        this.mediumLow = mediumLow;
    }

    public Double getMediumMedium() {
        return mediumMedium;
    }

    public void setMediumMedium(Double mediumMedium) {
        this.mediumMedium = mediumMedium;
    }

    public Double getMediumHeight() {
        return mediumHeight;
    }

    public void setMediumHeight(Double mediumHeight) {
        this.mediumHeight = mediumHeight;
    }

    public Double getHeightLow() {
        return heightLow;
    }

    public void setHeightLow(Double heightLow) {
        this.heightLow = heightLow;
    }

    public Double getHeightMedium() {
        return heightMedium;
    }

    public void setHeightMedium(Double heightMedium) {
        this.heightMedium = heightMedium;
    }

    public Double getHeightHeight() {
        return heightHeight;
    }

    public void setHeightHeight(Double heightHeight) {
        this.heightHeight = heightHeight;
    }
}