package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.reportmanagement.enums.AssetType;
import com.bjike.goddess.reportmanagement.enums.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author: [董添添]
 * @Date: [2018-05-02 18:08]
 * @Description: [存储资产表查询临时表 ]
 * @Version: [1.0.0]
 * @Copy: [com.gitgeek]
 */
@Entity
@Table(name = "reportmanagement_assetemp")
public class AsseTemp extends BaseEntity {
    /**
     * 开始时间
     */
    @Column(name = "startTime",  columnDefinition = "DATE   COMMENT '开始时间'")
    private LocalDate startTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime",  columnDefinition = "DATE   COMMENT '结束时间'")
    private LocalDate endTime;

    /**
     * 项目
     */
    @Column(name = "project",  columnDefinition = "VARCHAR(30)   COMMENT '项目'")
    private String project;

    /**
     * 行次
     */
    @Column(name = "num",  columnDefinition = "VARCHAR(8)   COMMENT '行次'")
    private Integer num;

    /**
     * 资产年初数
     */
    @Column(name = "beginAsset",  columnDefinition = "DOUBLE(10,2)   COMMENT '资产年初数'")
    private Double beginAsset;

    /**
     * 资产期末数
     */
    @Column(name = "endAsset",  columnDefinition = "DOUBLE(10,2)   COMMENT '资产期末数'")
    private Double endAsset;

    /**
     * 资产类型
     */
    @Column(name = "assetType", columnDefinition = "TINYINT(2)   COMMENT '资产类型'")
    private AssetType assetType;

    /**
     * 运算类型
     */
    @Column(name = "type", columnDefinition = "TINYINT(2)   COMMENT '运算类型'")
    private Type type;

    /**
     * 项目id
     */
    @Column(name = "projectId", columnDefinition = "VARCHAR(50)   COMMENT '项目id'")
    private String projectId;

    private String systemId;

    public AsseTemp() {
    }

    public AsseTemp(LocalDate startTime, LocalDate endTime, String project, Integer num, double beginAsset, double endAsset, AssetType assetType, Type type, String systemId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.project = project;
        this.num = num;
        this.beginAsset = beginAsset;
        this.endAsset = endAsset;
        this.assetType = assetType;
        this.type = type;
        this.systemId = systemId;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public double getBeginAsset() {
        return beginAsset;
    }

    public void setBeginAsset(double beginAsset) {
        this.beginAsset = beginAsset;
    }

    public double getEndAsset() {
        return endAsset;
    }

    public void setEndAsset(double endAsset) {
        this.endAsset = endAsset;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "AsseTemp{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", project='" + project + '\'' +
                ", num=" + num +
                ", beginAsset=" + beginAsset +
                ", endAsset=" + endAsset +
                ", assetType=" + assetType +
                ", type=" + type +
                ", projectId='" + projectId + '\'' +
                ", systemId='" + systemId + '\'' +
                '}';
    }
}
