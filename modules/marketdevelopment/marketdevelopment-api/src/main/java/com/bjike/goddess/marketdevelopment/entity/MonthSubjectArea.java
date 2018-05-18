package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 月计划省市方向(市)
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:46 ]
 * @Description: [ 月计划省市方向(市) ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_monthsubjectarea")
public class MonthSubjectArea extends BaseEntity {

    /**
     * 省市分类id
     */
    @Column(name = "provincialId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '省市分类id'")
    private String provincialId;

    /**
     * 各地区分类
     */
    @Column(name = "areaClassify", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '各地区分类'")
    private String areaClassify;

    /**
     * 各地区工作量权重(%)
     */
    @Column(name = "areaWeight", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '各地区工作量权重(%)'")
    private String areaWeight;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0   COMMENT '状态'",insertable = false)
    private Status status;

    public String getProvincialId() {
        return provincialId;
    }

    public void setProvincialId(String provincialId) {
        this.provincialId = provincialId;
    }

    public String getAreaClassify() {
        return areaClassify;
    }

    public void setAreaClassify(String areaClassify) {
        this.areaClassify = areaClassify;
    }

    public String getAreaWeight() {
        return areaWeight;
    }

    public void setAreaWeight(String areaWeight) {
        this.areaWeight = areaWeight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}