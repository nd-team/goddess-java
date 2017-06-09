package com.bjike.goddess.projectroyalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 毛利率
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:48 ]
 * @Description: [ 毛利率 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectroyalty_ratio")
public class Ratio extends BaseEntity {

    /**
     * 毛利率
     */
    @Column(name = "ratio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '毛利率'", unique = true)
    private Double ratio;

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


    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
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