package com.bjike.goddess.contractquotemanager.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 合同节点标准信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.712 ]
 * @Description: [ 合同节点标准信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contractquotemanager_contractnodestandard")
public class ContractNodeStandard extends BaseEntity {

    /**
     * 日期
     */
    @Column(nullable = false, columnDefinition = "DATE COMMENT '日期'")
    private LocalDate date;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;

    /**
     * 项目内部名称
     */
    @Column(name = "projectInner", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目内部名称'")
    private String projectInner;

    /**
     * 派工项目名称
     */
    @Column(name = "projectDispatch", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工项目名称'")
    private String projectDispatch;

    /**
     * 派工单号
     */
    @Column(name = "dispatchCode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工单号'")
    private String dispatchCode;

    /**
     * 类别
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类别'")
    private String type;

    /**
     * 节点
     */
    @Column(name = "node", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String node;

    /**
     * 单位
     */
    @Column(name = "unit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '单位'")
    private String unit;

    /**
     * 节点标准
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '节点标准'")
    private Integer nodeStandard;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectInner() {
        return projectInner;
    }

    public void setProjectInner(String projectInner) {
        this.projectInner = projectInner;
    }

    public String getProjectDispatch() {
        return projectDispatch;
    }

    public void setProjectDispatch(String projectDispatch) {
        this.projectDispatch = projectDispatch;
    }

    public String getDispatchCode() {
        return dispatchCode;
    }

    public void setDispatchCode(String dispatchCode) {
        this.dispatchCode = dispatchCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNodeStandard() {
        return nodeStandard;
    }

    public void setNodeStandard(Integer nodeStandard) {
        this.nodeStandard = nodeStandard;
    }
}