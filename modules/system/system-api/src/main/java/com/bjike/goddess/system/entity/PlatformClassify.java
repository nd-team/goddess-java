package com.bjike.goddess.system.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 平台分类
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 01:44 ]
 * @Description: [ 平台分类 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "system_platformclassify")
public class PlatformClassify extends BaseEntity {

    /**
     * 平台名称
     */
    @Column(name = "platformName",  columnDefinition = "VARCHAR(255)   COMMENT '平台名称'")
    private String platformName;

    /**
     * 平台定位
     */
    @Column(name = "platformLocalize",  columnDefinition = "VARCHAR(255)   COMMENT '平台定位'")
    private String platformLocalize;

    /**
     * 平台目的
     */
    @Column(name = "platformPurpose",  columnDefinition = "VARCHAR(255)   COMMENT '平台目的'")
    private String platformPurpose;

    /**
     * 平台特色
     */
    @Column(name = "platformFeatures",  columnDefinition = "VARCHAR(255)   COMMENT '平台特色'")
    private String platformFeatures;

    /**
     * 平台需求描述
     */
    @Column(name = "platformDemand",  columnDefinition = "VARCHAR(255)   COMMENT '平台需求描述'")
    private String platformDemand;

    /**
     * 包含（实现需求）功能
     */
    @Column(name = "containsFunction",  columnDefinition = "VARCHAR(255)   COMMENT '包含（实现需求）功能'")
    private String containsFunction;

    /**
     * 最新更新时间
     */
    @Column(name = "updateTime",  columnDefinition = "DATE   COMMENT '最新更新时间'")
    private LocalDate updateTime;

    /**
     * 项目编号
     */
    @Column(name = "projectNum",  columnDefinition = "VARCHAR(255)   COMMENT '项目编号'")
    private String projectNum;

    /**
     * 项目名称
     */
    @Column(name = "projectName",  columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 最新更新时间（节点）
     */
    @Column(name = "newNodeUpdateTime",  columnDefinition = "DATE   COMMENT '最新更新时间'")
    private LocalDate newNodeUpdateTime;

    /**
     * 新节点编号（新）
     */
    @Column(name = "newNodeNum",  columnDefinition = "VARCHAR(255)   COMMENT '新节点编号（新）'")
    private String newNodeNum;

    /**
     * 节点（新）
     */
    @Column(name = "newNode",  columnDefinition = "VARCHAR(255)   COMMENT '节点（新）'")
    private String newNode;

    /**
     * 最新更新时间（字段）
     */
    @Column(name = "fieldUpdateTime",  columnDefinition = "DATE   COMMENT '最新更新时间'")
    private LocalDate fieldUpdateTime;

    /**
     * 字段编号（新）
     */
    @Column(name = "newFieldNum",  columnDefinition = "VARCHAR(255)   COMMENT '字段编号（新）'")
    private String newFieldNum;

    /**
     * 字段（新）
     */
    @Column(name = "newField",  columnDefinition = "VARCHAR(255)   COMMENT '字段（新）'")
    private String newField;

    /**
     * 平台企业内部需求描述
     */
    @Column(name = "platformInternalDemand",  columnDefinition = "TEXT   COMMENT '平台企业内部需求描述'")
    private String platformInternalDemand;

    /**
     * 企业内部展示数据
     */
    @Column(  columnDefinition = "VARCHAR(255)   COMMENT '企业内部展示数据'")
    private Integer internalData;

    /**
     * 平台企业外部需求描述
     */
    @Column(name = "platformExternalDemand",  columnDefinition = "TEXT   COMMENT '平台企业外部需求描述'")
    private String platformExternalDemand;

    /**
     * 企业外部展示数据
     */
    @Column(  columnDefinition = "VARCHAR(255)   COMMENT '企业外部展示数据'")
    private Integer externalData;

    /**
     * 注册用户需求描述
     */
    @Column(name = "registerDemand",  columnDefinition = "TEXT   COMMENT '注册用户需求描述'")
    private String registerDemand;

    /**
     * 注册展示数据
     */
    @Column(  columnDefinition = "VARCHAR(255)   COMMENT '注册展示数据'")
    private Integer registerData;

    /**
     * 非注册用户需求描述
     */
    @Column(name = "unRegisterDemand",  columnDefinition = "TEXT   COMMENT '非注册用户需求描述'")
    private String unRegisterDemand;

    /**
     * 非注册展示数据
     */
    @Column(  columnDefinition = "VARCHAR(255)   COMMENT '非注册展示数据'")
    private Integer unRegisterData;

    /**
     * 平台链接
     */
    @Column(name = "platformLink",  columnDefinition = "VARCHAR(255)   COMMENT '平台链接'")
    private String platformLink;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformLocalize() {
        return platformLocalize;
    }

    public void setPlatformLocalize(String platformLocalize) {
        this.platformLocalize = platformLocalize;
    }

    public String getPlatformPurpose() {
        return platformPurpose;
    }

    public void setPlatformPurpose(String platformPurpose) {
        this.platformPurpose = platformPurpose;
    }

    public String getPlatformFeatures() {
        return platformFeatures;
    }

    public void setPlatformFeatures(String platformFeatures) {
        this.platformFeatures = platformFeatures;
    }

    public String getPlatformDemand() {
        return platformDemand;
    }

    public void setPlatformDemand(String platformDemand) {
        this.platformDemand = platformDemand;
    }

    public String getContainsFunction() {
        return containsFunction;
    }

    public void setContainsFunction(String containsFunction) {
        this.containsFunction = containsFunction;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getNewNodeUpdateTime() {
        return newNodeUpdateTime;
    }

    public void setNewNodeUpdateTime(LocalDate newNodeUpdateTime) {
        this.newNodeUpdateTime = newNodeUpdateTime;
    }

    public String getNewNodeNum() {
        return newNodeNum;
    }

    public void setNewNodeNum(String newNodeNum) {
        this.newNodeNum = newNodeNum;
    }

    public String getNewNode() {
        return newNode;
    }

    public void setNewNode(String newNode) {
        this.newNode = newNode;
    }

    public LocalDate getFieldUpdateTime() {
        return fieldUpdateTime;
    }

    public void setFieldUpdateTime(LocalDate fieldUpdateTime) {
        this.fieldUpdateTime = fieldUpdateTime;
    }

    public String getNewFieldNum() {
        return newFieldNum;
    }

    public void setNewFieldNum(String newFieldNum) {
        this.newFieldNum = newFieldNum;
    }

    public String getNewField() {
        return newField;
    }

    public void setNewField(String newField) {
        this.newField = newField;
    }

    public String getPlatformInternalDemand() {
        return platformInternalDemand;
    }

    public void setPlatformInternalDemand(String platformInternalDemand) {
        this.platformInternalDemand = platformInternalDemand;
    }

    public Integer getInternalData() {
        return internalData;
    }

    public void setInternalData(Integer internalData) {
        this.internalData = internalData;
    }

    public String getPlatformExternalDemand() {
        return platformExternalDemand;
    }

    public void setPlatformExternalDemand(String platformExternalDemand) {
        this.platformExternalDemand = platformExternalDemand;
    }

    public Integer getExternalData() {
        return externalData;
    }

    public void setExternalData(Integer externalData) {
        this.externalData = externalData;
    }

    public String getRegisterDemand() {
        return registerDemand;
    }

    public void setRegisterDemand(String registerDemand) {
        this.registerDemand = registerDemand;
    }

    public Integer getRegisterData() {
        return registerData;
    }

    public void setRegisterData(Integer registerData) {
        this.registerData = registerData;
    }

    public String getUnRegisterDemand() {
        return unRegisterDemand;
    }

    public void setUnRegisterDemand(String unRegisterDemand) {
        this.unRegisterDemand = unRegisterDemand;
    }

    public Integer getUnRegisterData() {
        return unRegisterData;
    }

    public void setUnRegisterData(Integer unRegisterData) {
        this.unRegisterData = unRegisterData;
    }

    public String getPlatformLink() {
        return platformLink;
    }

    public void setPlatformLink(String platformLink) {
        this.platformLink = platformLink;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}