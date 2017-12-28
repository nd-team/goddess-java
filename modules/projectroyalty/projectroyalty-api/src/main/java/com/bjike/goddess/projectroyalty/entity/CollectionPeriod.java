package com.bjike.goddess.projectroyalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 回款周期
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:55 ]
 * @Description: [ 回款周期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectroyalty_collectionperiod")
public class CollectionPeriod extends BaseEntity {

    /**
     * 回款周期(月)
     */
    @Column(name = "collection", nullable = false, columnDefinition = "VARCHAR(11)   COMMENT '回款周期(月)'", unique = true)
    private Integer collection;

    /**
     * 重要性
     */
    @Column(name = "importance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '重要性'")
    private Double importance;

    /**
     * 提成比例
     */
    @Column(name = "rate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '提成比例'")
    private Double rate;

    /**
     * 资金方
     */
    @Column(name = "capital", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金方'")
    private Double capital;


    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }
}