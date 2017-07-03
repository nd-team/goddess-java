package com.bjike.goddess.marketactivitymanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.marketactivitymanage.type.CycleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 市场招待汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketactivitymanage_marketservesummary")
public class MarketServeSummary extends BaseEntity {

    /**
     * 项目组
     */
    @Column(name = "projectGroups", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目组'")
    private String projectGroups;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "TINYINT(1) COMMENT '类型(计划:true,实际:false)'")
    private Boolean type;

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
    @Column(name = "lastTime", columnDefinition = "DATETIME COMMENT '上次发送时间'")
    private LocalDateTime lastTime;

    /**
     * 备注
     */
    @Column(name = "note", columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String note;

    /**
     * 发送间隔
     */
    @Column(name = "sendInterval", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '发送间隔'")
    private Double sendInterval;

    /**
     * 发送时间格式
     */
    @Column(name = "cycle", nullable = false, columnDefinition = "TINYINT(2) COMMENT '发送时间格式'")
    private CycleType cycle;

    /**
     * 汇总间隔开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME COMMENT '汇总间隔开始时间'")
    private LocalDateTime startTime;

    /**
     * 汇总间隔结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME COMMENT '汇总间隔结束时间'")
    private LocalDateTime endTime;

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

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(Double sendInterval) {
        this.sendInterval = sendInterval;
    }

    public CycleType getCycle() {
        return cycle;
    }

    public void setCycle(CycleType cycle) {
        this.cycle = cycle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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