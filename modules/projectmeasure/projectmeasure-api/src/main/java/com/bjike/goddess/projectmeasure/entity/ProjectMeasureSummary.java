package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.projectmeasure.type.CycleType;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 项目测算汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 05:24 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_projectmeasuresummary")
public class ProjectMeasureSummary extends BaseEntity {

    /**
     * 项目组
     */
    @Column(name = "projectGroups", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目组'")
    private String projectGroups;

    /**
     * 创建/修改人
     */
    @Column(name = "createUser", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '创建/修改人'")
    private String createUser;

    /**
     * 更新时间
     */
    @Column(name = "updateTime", nullable = false, columnDefinition = "DATETIME COMMENT '更新时间'")
    private LocalDateTime updateTime;

    /**
     * 上次发送时间
     */
    @Column(name = "lastTime", nullable = false, columnDefinition = "DATETIME COMMENT '上次发送时间'")
    private LocalDateTime lastTime;

    /**
     * 发送间隔
     */
    @Column(name = "sendInterval", nullable = false, columnDefinition = "INT(11) COMMENT '发送间隔'")
    private Integer sendInterval;

    /**
     * 发送时间格式
     */
    @Column(name = "cycle", nullable = false, columnDefinition = "TINYINT(2) COMMENT '发送时间格式'")
    private CycleType cycle;

    /**
     * 汇总间隔
     */
    @Column(name = "detailInterval", nullable = false, columnDefinition = "INT(11) COMMENT '汇总间隔'")
    private Integer detailInterval;

    /**
     * 汇总时间格式
     */
    @Column(name = "detailCycle", nullable = false, columnDefinition = "TINYINT(2) COMMENT '汇总时间格式'")
    private CycleType detailCycle;

    /**
     * 发送对象
     */
    @Column(name = "emails", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '发送对象'")
    private String emails;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) COMMENT '状态'")
    private Status status;


    public String getProjectGroups() {
        return projectGroups;
    }

    public void setProjectGroups(String projectGroups) {
        this.projectGroups = projectGroups;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(Integer sendInterval) {
        this.sendInterval = sendInterval;
    }

    public CycleType getCycle() {
        return cycle;
    }

    public void setCycle(CycleType cycle) {
        this.cycle = cycle;
    }

    public Integer getDetailInterval() {
        return detailInterval;
    }

    public void setDetailInterval(Integer detailInterval) {
        this.detailInterval = detailInterval;
    }

    public CycleType getDetailCycle() {
        return detailCycle;
    }

    public void setDetailCycle(CycleType detailCycle) {
        this.detailCycle = detailCycle;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}