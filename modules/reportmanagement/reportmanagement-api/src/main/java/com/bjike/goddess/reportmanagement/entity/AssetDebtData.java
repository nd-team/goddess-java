package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * 存储负债表查询的数据
 *
 * @Author: [ caiwenxian ]
 * @date 18-3-26 上午11:26
 * @Description: [ 存储负债表查询的数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_assetdebtdata")
public class AssetDebtData extends BaseEntity {

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
     * 负债和所有者权益年初数
     */
    @Column(name = "beginAsset",  columnDefinition = "DECIMAL(10,2)   COMMENT '负债和所有者权益年初数'")
    private Double beginAsset;


    private String systemId;

    /**
     * 负债和所有者权益年末数
     */
    @Column(name = "endAsset",  columnDefinition = "DECIMAL(10,2)   COMMENT '负债和所有者权益期末数'")
    private Double endAsset;

    /**
     * 项目id
     */
    @Column(name = "projectId",  columnDefinition = "DECIMAL(10,2)   COMMENT '项目id'")
    private String projectId;

    public AssetDebtData() {
    }

    public AssetDebtData(LocalDate startTime, LocalDate endTime, String project, Integer num, Double beginAsset, Double endAsset) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.project = project;
        this.num = num;
        this.beginAsset = beginAsset;
        this.endAsset = endAsset;
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

    public Double getBeginAsset() {
        return beginAsset;
    }

    public void setBeginAsset(Double beginAsset) {
        this.beginAsset = beginAsset;
    }

    public Double getEndAsset() {
        return endAsset;
    }

    public void setEndAsset(Double endAsset) {
        this.endAsset = endAsset;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "AssetDebtData{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", project='" + project + '\'' +
                ", num=" + num +
                ", beginAsset=" + beginAsset +
                ", systemId='" + systemId + '\'' +
                ", endAsset=" + endAsset +
                ", projectId='" + projectId + '\'' +
                '}';
    }

}