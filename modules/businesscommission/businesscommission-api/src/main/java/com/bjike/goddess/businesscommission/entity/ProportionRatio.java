package com.bjike.goddess.businesscommission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务提成分配比例表子表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:26 ]
 * @Description: [ 业务提成分配比例表子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businesscommission_proportionratio")
public class ProportionRatio extends BaseEntity {

    /**
     * 业务提成分配比例表id
     */
    @Column(name = "proportionId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务提成分配比例表id'")
    private String proportionId;

    /**
     * 影响因素
     */
    @Column(name = "factors", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '影响因素'")
    private String factors;

    /**
     * 类型影响因素权重
     */
    @Column(name = "typeFactors", columnDefinition = "DECIMAL(10,2)   COMMENT '类型影响因素权重'")
    private Double typeFactors;

    /**
     * 信息提供人
     */
    @Column(name = "messageProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '信息提供人'")
    private Double messageProportion;

    /**
     * 介绍关系揽接
     */
    @Column(name = "businessProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '介绍关系揽接'")
    private Double businessProportion;

    /**
     * 出面接洽
     */
    @Column(name = "talkProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '出面接洽'")
    private Double talkProportion;

    /**
     * 维护
     */
    @Column(name = "maintainProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '维护'")
    private Double maintainProportion;

    /**
     * 剩余分配比例
     */
    @Column(name = "surplusProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余分配比例'")
    private Double surplusProportion;


    public String getProportionId() {
        return proportionId;
    }

    public void setProportionId(String proportionId) {
        this.proportionId = proportionId;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public Double getTypeFactors() {
        return typeFactors;
    }

    public void setTypeFactors(Double typeFactors) {
        this.typeFactors = typeFactors;
    }

    public Double getMessageProportion() {
        return messageProportion;
    }

    public void setMessageProportion(Double messageProportion) {
        this.messageProportion = messageProportion;
    }

    public Double getBusinessProportion() {
        return businessProportion;
    }

    public void setBusinessProportion(Double businessProportion) {
        this.businessProportion = businessProportion;
    }

    public Double getTalkProportion() {
        return talkProportion;
    }

    public void setTalkProportion(Double talkProportion) {
        this.talkProportion = talkProportion;
    }

    public Double getMaintainProportion() {
        return maintainProportion;
    }

    public void setMaintainProportion(Double maintainProportion) {
        this.maintainProportion = maintainProportion;
    }

    public Double getSurplusProportion() {
        return surplusProportion;
    }

    public void setSurplusProportion(Double surplusProportion) {
        this.surplusProportion = surplusProportion;
    }
}