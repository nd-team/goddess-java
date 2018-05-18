package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 考核指标内容
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-14 02:46 ]
 * @Description: [ 考核指标内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_indexcontent")
public class IndexContent extends BaseEntity {

    /**
     * 对赌标签名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对赌标签名'")
    private String name;

    /**
     * 标签权重
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '标签权重'")
    private Integer weight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}