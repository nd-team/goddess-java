package com.bjike.goddess.bonus.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 绩效指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-08 05:40 ]
 * @Description: [ 绩效指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bonus_performance_indicator")
public class PerformanceIndicator extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 项目类别
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目类别'")
    private String type;

    /**
     * 指标名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标名称'")
    private String name;

    /**
     * 指标描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标描述'")
    private String description;

    /**
     * 计分方式
     */
    @Column(name = "way", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计分方式'")
    private String way;

    /**
     * 参考目标值
     */
    @Column(name = "target", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '参考目标值'")
    private String target;

    /**
     * 获取数据的数据源字段编号
     */
    @Column(name = "serialNumber", columnDefinition = "VARCHAR(255)   COMMENT '获取数据的数据源字段编号'")
    private String serialNumber;

    /**
     * 获取数据的数据源字段
     */
    @Column(name = "field", columnDefinition = "VARCHAR(255)   COMMENT '获取数据的数据源字段'")
    private String field;

    /**
     * 数据源字段路径
     */
    @Column(name = "route", columnDefinition = "VARCHAR(255)   COMMENT '数据源字段路径'")
    private String route;

    /**
     * 系统或人工
     */
    @Column(name = "is_difference", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '系统或人工'")
    private Boolean difference;

    /**
     * 状态
     */
    @Column(name = "is_status", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '状态'")
    private Boolean status;


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Boolean getDifference() {
        return difference;
    }

    public void setDifference(Boolean difference) {
        this.difference = difference;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}