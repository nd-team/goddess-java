package com.bjike.goddess.businesscommission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 业务提成分配比例表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:34 ]
 * @Description: [ 业务提成分配比例表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businesscommission_weightallot")
public class WeightAllot extends BaseEntity {

    /**
     * 业务提成分配比例协商时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATETIME   COMMENT '业务提成分配比例协商时间'")
    private LocalDateTime time;

    /**
     * 部门/项目组
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门/项目组'")
    private String department;

    /**
     * 影响因素
     */
    @Column(name = "factors", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '影响因素'")
    private String factors;

    /**
     * 类型影响因素权重
     */
    @Column(name = "typeFactors", columnDefinition = "VARCHAR(255)   COMMENT '类型影响因素权重'")
    private Double typeFactors;

    /**
     * 参与协商人
     */
    @Column(name = "consultants", columnDefinition = "TEXT   COMMENT '参与协商人'")
    private String consultants;

    /**
     * 提成分配比例确认单是否全部确认
     */
    @Column(name = "confirm", columnDefinition = "TINYINT(1)   COMMENT '提成分配比例确认单是否全部确认'")
    private Boolean confirm;

    /**
     * 已确认人
     */
    @Column(name = "confirmed", columnDefinition = "TEXT   COMMENT '已确认人'")
    private String confirmed;

    /**
     * 未确认人
     */
    @Column(name = "notConfirmed", columnDefinition = "TEXT   COMMENT '未确认人'")
    private String notConfirmed;


    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 内部项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 信息提供人
     */
    @Column(name = "messageProportion", nullable = false, columnDefinition = "DECIMAL(10,2)  COMMENT '信息提供占比'")
    private Double messageProportion;

    /**
     * 介绍关系揽接
     */
    @Column(name = "businessProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务揽接占比'")
    private Double businessProportion;

    /**
     * 出面接洽
     */
    @Column(name = "talkProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务洽谈占比'")
    private Double talkProportion;

    /**
     * 维护
     */
    @Column(name = "maintainProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '维护占比'")
    private Double maintainProportion;

    /**
     * 剩余分配比例
     */
    @Column(name = "surplusProportion", columnDefinition = "DECIMAL(10,2)   COMMENT '剩余占比'")
    private Double surplusProportion;

//    /**
//     * 总比例
//     */
//    @Column(name = "totalProportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总比例'")
//    private Double totalProportion;
//
//    /**
//     * 备注
//     */
//    @Column(name = "remark", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
//    private String remark;


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public Double getTypeFactors() {
        return typeFactors;
    }

    public void setTypeFactors(Double typeFactors) {
        this.typeFactors = typeFactors;
    }

    public String getConsultants() {
        return consultants;
    }

    public void setConsultants(String consultants) {
        this.consultants = consultants;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getNotConfirmed() {
        return notConfirmed;
    }

    public void setNotConfirmed(String notConfirmed) {
        this.notConfirmed = notConfirmed;
    }

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
}