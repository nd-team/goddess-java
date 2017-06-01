package com.bjike.goddess.balancecard.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 指标名称设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:02 ]
 * @Description: [ 指标名称设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "balancecard_indexnameset")
public class IndexNameSet extends BaseEntity {

    /**
     * 指标名称
     */
    @Column(name = "typeName", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标名称'")
    private String typeName;

    /**
     * 描述
     */
    @Column(name = "describtion", columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String describtion;

    /**
     * 指标名称添加人
     */
    @Column(name = "person", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标名称添加人'")
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