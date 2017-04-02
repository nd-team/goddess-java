package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资质办理信息采集
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:33 ]
 * @Description: [ 资质办理信息采集 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_gather")
public class QualificationsGather extends BaseEntity {

    /**
     * 资质类别
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '资质类别'")
    private String type;

    /**
     * 认证条件
     */
    @Column(name = "authentication", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '认证条件'")
    private String authentication;

    /**
     * 申请开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '申请开始时间'")
    private LocalDate startTime;

    /**
     * 申请结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '申请结束时间'")
    private LocalDate endTime;

    /**
     * 认证时间
     */
    @Column(name = "authenticationTime", nullable = false, columnDefinition = "DATE   COMMENT '认证时间'")
    private LocalDate authenticationTime;

    /**
     * 认证要求描述
     */
    @Column(name = "demand", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '认证要求描述'")
    private String demand;

    /**
     * 所需资料描述
     */
    @Column(name = "material", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所需资料描述'")
    private String material;

    /**
     * 证书获取方式
     */
    @Column(name = "way", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证书获取方式'")
    private String way;

    /**
     * 办理费用(元)
     */
    @Column(name = "cost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '办理费用(元)'")
    private Double cost;

    /**
     * 采集信息来源
     */
    @Column(name = "originate", columnDefinition = "VARCHAR(255)   COMMENT '采集信息来源'")
    private String originate;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public LocalDate getAuthenticationTime() {
        return authenticationTime;
    }

    public void setAuthenticationTime(LocalDate authenticationTime) {
        this.authenticationTime = authenticationTime;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getOriginate() {
        return originate;
    }

    public void setOriginate(String originate) {
        this.originate = originate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}