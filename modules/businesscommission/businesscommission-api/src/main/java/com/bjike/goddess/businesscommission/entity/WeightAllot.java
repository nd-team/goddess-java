package com.bjike.goddess.businesscommission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:34 ]
 * @Description: [ 业务提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businesscommission_weightallot")
public class WeightAllot extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 信息提供占比
     */
    @Column(name = "messageProportion", nullable = true, columnDefinition = "DECIMAL(10,2)  COMMENT '信息提供占比'")
    private Double messageProportion;

    /**
     * 业务揽接占比
     */
    @Column(name = "businessProportion", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '业务揽接占比'")
    private Double businessProportion;

    /**
     * 业务洽谈占比
     */
    @Column(name = "talkProportion", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '业务洽谈占比'")
    private Double talkProportion;

    /**
     * 维护占比
     */
    @Column(name = "maintainProportion", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '维护占比'")
    private Double maintainProportion;

    /**
     * 剩余占比
     */
    @Column(name = "surplusProportion", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余占比'")
    private Double surplusProportion;

    /**
     * 总比例
     */
    @Column(name = "totalProportion", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '总比例'")
    private Double totalProportion;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getMessageProportion() {
        return messageProportion;
    }

    public void setMessageProportion(Double messageProportion) {
        this.messageProportion = messageProportion;
    }

    public Double getBusinessProportion() {
        return businessProportion;
    }

    public void setBusinessProportion(Double businessProportion) {
        this.businessProportion = businessProportion;
    }

    public Double getTalkProportion() {
        return talkProportion;
    }

    public void setTalkProportion(Double talkProportion) {
        this.talkProportion = talkProportion;
    }

    public Double getMaintainProportion() {
        return maintainProportion;
    }

    public void setMaintainProportion(Double maintainProportion) {
        this.maintainProportion = maintainProportion;
    }

    public Double getSurplusProportion() {
        return surplusProportion;
    }

    public void setSurplusProportion(Double surplusProportion) {
        this.surplusProportion = surplusProportion;
    }

    public Double getTotalProportion() {
        return totalProportion;
    }

    public void setTotalProportion(Double totalProportion) {
        this.totalProportion = totalProportion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}