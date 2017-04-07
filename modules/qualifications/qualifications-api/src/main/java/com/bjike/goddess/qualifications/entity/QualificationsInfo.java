package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.qualifications.enums.AptitudeStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资质信息管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:15 ]
 * @Description: [ 资质信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_info")
public class QualificationsInfo extends BaseEntity {

    /**
     * 资质类别
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '资质类别'")
    private String type;

    /**
     * 办理时间
     */
    @Column(name = "handleTime", nullable = false, columnDefinition = "DATE   COMMENT '办理时间'")
    private LocalDate handleTime;

    /**
     * 审批机构
     */
    @Column(name = "examineAgency", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '审批机构'")
    private String examineAgency;

    /**
     * 资质办理人
     */
    @Column(name = "transactor", columnDefinition = "VARCHAR(255)   COMMENT '资质办理人'")
    private String transactor;

    /**
     * 有效年限
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '有效年限'")
    private Integer year;

    /**
     * 年审时间
     */
    @Column(name = "extendTime", nullable = false, columnDefinition = "DATE   COMMENT '年审时间'")
    private LocalDate extendTime;

    /**
     * 年审间隔时间(月)
     */
    @Column(name = "intervalTime", nullable = false, columnDefinition = "INT(11)   COMMENT '年审间隔时间(月)'")
    private Integer intervalTime;

    /**
     * 办理费用(元)
     */
    @Column(name = "handleCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '办理费用(元)'")
    private Double handleCost;

    /**
     * 年审费用(元)
     */
    @Column(name = "extendCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '年审费用(元)'")
    private Double extendCost;

    /**
     * 资质状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0   COMMENT '资质状态'", insertable = false)
    private AptitudeStatus status;

    /**
     * 原文件存储位置
     */
    @Column(name = "storageSite", columnDefinition = "VARCHAR(255)   COMMENT '原文件存储位置'")
    private String storageSite;

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

    public LocalDate getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDate handleTime) {
        this.handleTime = handleTime;
    }

    public String getExamineAgency() {
        return examineAgency;
    }

    public void setExamineAgency(String examineAgency) {
        this.examineAgency = examineAgency;
    }

    public String getTransactor() {
        return transactor;
    }

    public void setTransactor(String transactor) {
        this.transactor = transactor;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getExtendTime() {
        return extendTime;
    }

    public void setExtendTime(LocalDate extendTime) {
        this.extendTime = extendTime;
    }

    public Integer getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Integer intervalTime) {
        this.intervalTime = intervalTime;
    }

    public Double getHandleCost() {
        return handleCost;
    }

    public void setHandleCost(Double handleCost) {
        this.handleCost = handleCost;
    }

    public Double getExtendCost() {
        return extendCost;
    }

    public void setExtendCost(Double extendCost) {
        this.extendCost = extendCost;
    }

    public AptitudeStatus getStatus() {
        return status;
    }

    public void setStatus(AptitudeStatus status) {
        this.status = status;
    }

    public String getStorageSite() {
        return storageSite;
    }

    public void setStorageSite(String storageSite) {
        this.storageSite = storageSite;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}