package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 月计划省市方向
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:29 ]
 * @Description: [ 月计划省市方向 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_monthsubjectprovincial")
public class MonthSubjectProvincial extends BaseEntity {

    /**
     * 业务方向类型id
     */
    @Column(name = "businessDataId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向类型id'")
    private String businessDataId;

    /**
     * 省市分类
     */
    @Column(name = "provincial", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '省市分类'")
    private String provincial;

    /**
     * 各省市工作量权重(%)
     */
    @Column(name = "provincialWeight", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '各省市工作量权重(%)'")
    private String provincialWeight;

//    /**
//     * 各地区分类
//     */
//    @Column(name = "areaClassify", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '各地区分类'")
//    private String areaClassify;
//
//    /**
//     * 各地区工作量权重(%)
//     */
//    @Column(name = "areaWeight", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '各地区工作量权重(%)'")
//    private String areaWeight;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0   COMMENT '状态'",insertable = false)
    private Status status;

    public String getBusinessDataId() {
        return businessDataId;
    }

    public void setBusinessDataId(String businessDataId) {
        this.businessDataId = businessDataId;
    }

    public String getProvincial() {
        return provincial;
    }

    public void setProvincial(String provincial) {
        this.provincial = provincial;
    }

    public String getProvincialWeight() {
        return provincialWeight;
    }

    public void setProvincialWeight(String provincialWeight) {
        this.provincialWeight = provincialWeight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}