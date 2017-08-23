package com.bjike.goddess.system.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 问题
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "system_question")
public class Question extends BaseEntity {

    /**
     * 问题提出人
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '问题提出人'")
    private String name;

    /**
     * 问题描述
     */
    @Column(name = "rate", columnDefinition = "VARCHAR(255)   COMMENT '问题描述'")
    private String rate;
    /**
     * 功能列表
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "featureList_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '功能列表'")
    private FeatureList featureList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public FeatureList getFeatureList() {
        return featureList;
    }

    public void setFeatureList(FeatureList featureList) {
        this.featureList = featureList;
    }
}