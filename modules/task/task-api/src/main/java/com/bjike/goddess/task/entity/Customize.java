package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.NoticeType;
import com.bjike.goddess.task.enums.SummaryType;
import com.bjike.goddess.task.enums.TimeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * 定制化汇总
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-25 15:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_customize")
public class Customize extends BaseEntity {
    /**
     * 汇总名
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '汇总名' ", nullable = false)
    private String name;

    /**
     * 项目
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '项目' ", nullable = false)
    private String project;

    /**
     * 创建人
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '创建人' ", nullable = false)
    private String user;


    /**
     * 表
     */
    @Column(columnDefinition = "TEXT COMMENT '表,多个' ", nullable = false)
    private String tables;

    /**
     * 汇总字段
     */
    @Column(columnDefinition = "TEXT COMMENT '汇总字段,多个' ", nullable = false)
    private String fields;
    /**
     * 定时时间类型
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '定时时间类型' ", nullable = false)
    private TimeType timeType;

    /**
     * 定时时间隔值
     */
    @Column(columnDefinition = "INT(8) COMMENT '定时时间隔值'",nullable = false)
    private Integer timeVal;

    /**
     * 通知类型
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '通知类型' ", nullable = false)
    private NoticeType noticeType;
    /**
     * 通知目标(部门,用户等,所有时为空)
     */
    @Column(columnDefinition = "TEXT COMMENT '通知目标' ")
    private String noticeTarget;

    /**
     * 是否启用
     */
    @Column(name = "is_enable", columnDefinition = "TINYINT(1)  COMMENT '是否使用'", nullable = false)
    private Boolean enable;
    /**
     * 汇总类型
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '汇总类型' ", nullable = false)
    private SummaryType summaryType;
    /**
     * 汇总目标(部门,用户等,所有时为空)
     */
    @Column(columnDefinition = "TEXT comment '汇总目标' ")
    private String summaryTarget;
    /**
     * 间隔类型
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '间隔类型' ", nullable = false)
    private DateType dateType;
    /**
     * 间隔值
     */
    @Column(columnDefinition = "INT(8) COMMENT '间隔值'",nullable = false)
    private Integer dateVal;

    /**
     * 上次发送时间
     */
    @Column(columnDefinition = "DATETIME   COMMENT '上次发送时间' ")
    private LocalDateTime lastTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public TimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(TimeType timeType) {
        this.timeType = timeType;
    }

    public Integer getTimeVal() {
        return timeVal;
    }

    public void setTimeVal(Integer timeVal) {
        this.timeVal = timeVal;
    }

    public NoticeType getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(NoticeType noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeTarget() {
        return noticeTarget;
    }

    public void setNoticeTarget(String noticeTarget) {
        this.noticeTarget = noticeTarget;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public SummaryType getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(SummaryType summaryType) {
        this.summaryType = summaryType;
    }

    public String getSummaryTarget() {
        return summaryTarget;
    }

    public void setSummaryTarget(String summaryTarget) {
        this.summaryTarget = summaryTarget;
    }

    public DateType getDateType() {
        return dateType;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }

    public Integer getDateVal() {
        return dateVal;
    }

    public void setDateVal(Integer dateVal) {
        this.dateVal = dateVal;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }
}
