package com.bjike.goddess.taskallotment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.taskallotment.enums.ForObject;
import com.bjike.goddess.taskallotment.enums.Spacing;
import com.bjike.goddess.taskallotment.enums.StandardType;
import com.bjike.goddess.taskallotment.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 标准工时设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 11:23 ]
 * @Description: [ 标准工时设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "taskallotment_timeset")
public class TimeSet extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "depart", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String depart;

    /**
     * 用于对象
     */
    @Column(name = "forObject", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '用于对象'")
    private ForObject forObject;

    /**
     * 标准类型
     */
    @Column(name = "standardType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '标准类型'")
    private StandardType standardType;
    /**
     * 标准工时
     */
    @Column(name = "hour", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '标准工时'")
    private Double hour;

    /**
     * 是否提醒本部门所有人
     */
    @Column(name = "sendAll", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否提醒本部门所有人'")
    private Boolean sendAll;
    /**
     * 提醒频率
     */
    @Column(name = "remind", columnDefinition = "INT(11)   COMMENT '提醒频率'")
    private Integer remind;

    /**
     * 提醒间隔
     */
    @Column(name = "spacing",columnDefinition = "TINYINT(2)   COMMENT '提醒间隔'")
    private Spacing spacing;

    /**
     * 开始提醒时间
     */
    @Column(name = "remindTime", nullable = false, columnDefinition = "DATETIME   COMMENT '开始提醒时间'")
    private LocalDateTime remindTime;

    /**
     * 上次提醒时间
     */
    @Column(name = "lastTime", nullable = false, columnDefinition = "DATETIME   COMMENT '上次提醒时间'")
    private LocalDateTime lastTime;

    /**
     * 提醒对象
     */
    @Column(name = "remindObject", nullable = false, columnDefinition = "TEXT   COMMENT '提醒对象'")
    private String remindObject;

    /**
     * 制定人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '制定人'")
    private String name;

    /**
     * 制定日期
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATE   COMMENT '制定时间'")
    private LocalDate time;
    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    public ForObject getForObject() {
        return forObject;
    }

    public void setForObject(ForObject forObject) {
        this.forObject = forObject;
    }

    public StandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(StandardType standardType) {
        this.standardType = standardType;
    }

    public Boolean getSendAll() {
        return sendAll;
    }

    public void setSendAll(Boolean sendAll) {
        this.sendAll = sendAll;
    }

    public Spacing getSpacing() {
        return spacing;
    }

    public void setSpacing(Spacing spacing) {
        this.spacing = spacing;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Double getHour() {
        return hour;
    }

    public void setHour(Double hour) {
        this.hour = hour;
    }

    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
    }

    public LocalDateTime getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(LocalDateTime remindTime) {
        this.remindTime = remindTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public String getRemindObject() {
        return remindObject;
    }

    public void setRemindObject(String remindObject) {
        this.remindObject = remindObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}