package com.bjike.goddess.projectroyalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 难易度
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:47 ]
 * @Description: [ 难易度 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectroyalty_facility")
public class Facility extends BaseEntity {

    /**
     * 难易度
     */
    @Column(name = "facility", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '难易度'", unique = true)
    private Double facility;

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
     * 管理能力
     */
    @Column(name = "manage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理能力'")
    private Double manage;


    public Double getFacility() {
        return facility;
    }

    public void setFacility(Double facility) {
        this.facility = facility;
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

    public Double getManage() {
        return manage;
    }

    public void setManage(Double manage) {
        this.manage = manage;
    }
}