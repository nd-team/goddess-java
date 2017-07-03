package com.bjike.goddess.balancecard.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 维度设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 维度设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "balancecard_dimensionset")
public class DimensionSet extends BaseEntity {

    /**
     * 维度名称
     */
    @Column(name = "typeName", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '维度名称'")
    private String typeName;

    /**
     * 描述
     */
    @Column(name = "describtion",  columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String describtion;

    /**
     * 维度添加人
     */
    @Column(name = "person", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '维度添加人'")
    private String person;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

}