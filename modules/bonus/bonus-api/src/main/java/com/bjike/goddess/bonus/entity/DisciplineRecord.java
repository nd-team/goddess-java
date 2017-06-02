package com.bjike.goddess.bonus.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 奖罚记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-10 11:54 ]
 * @Description: [ 奖罚记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bonus_discipline_record")
public class DisciplineRecord extends BaseEntity {

    /**
     * 发生时间
     */
    @Column(name = "occurrence", nullable = false, columnDefinition = "DATETIME   COMMENT '发生时间'")
    private LocalDateTime occurrence;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "project", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 工号
     */
    @Column(name = "serialNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工号'")
    private String serialNumber;

    /**
     * 指标名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '指标名称'")
    private String name;

    /**
     * 原因
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '原因'")
    private String reason;

    /**
     * 分数
     */
    @Column(name = "ballot", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '分数'")
    private Double ballot;

    /**
     * 发起方
     */
    @Column(name = "launch", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发起方'")
    private String launch;

    /**
     * 奖励或处罚
     */
    @Column(name = "is_status", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '奖励或处罚'")
    private Boolean status;


    public LocalDateTime getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(LocalDateTime occurrence) {
        this.occurrence = occurrence;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getBallot() {
        return ballot;
    }

    public void setBallot(Double ballot) {
        this.ballot = ballot;
    }

    public String getLaunch() {
        return launch;
    }

    public void setLaunch(String launch) {
        this.launch = launch;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}