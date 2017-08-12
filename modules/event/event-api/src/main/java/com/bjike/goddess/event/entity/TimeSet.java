package com.bjike.goddess.event.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.event.enums.IntervalType;
import com.bjike.goddess.event.enums.Permissions;
import com.bjike.goddess.event.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 提醒间隔时间设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 04:11 ]
 * @Description: [ 提醒间隔时间设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "event_timeset")
public class TimeSet extends BaseEntity {

    /**
     * 设置人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '设置人'")
    private String name;

    /**
     * 权限
     */
    @Column(name = "permissions", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '权限'")
    private Permissions permissions;

    /**
     * 间隔类型
     */
    @Column(name = "intervalType",  columnDefinition = "TINYINT(2)   COMMENT '间隔类型'")
    private IntervalType intervalType;

    /**
     * 间隔时长
     */
    @Column(name = "time",  columnDefinition = "INT(11)   COMMENT '间隔时长'")
    private Integer interval;

    /**
     * 上次提醒时间
     */
    @Column(name = "lastTime",  columnDefinition = "DATETIME   COMMENT '上次提醒时间'")
    private LocalDateTime lastTime;

    /**
     * 设置时间
     */
    @Column(name = "setTime", columnDefinition = "DATETIME   COMMENT '设置时间'")
    private LocalDateTime setTime;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    /**
     * 颜色
     */
    @Column(name = "color",  columnDefinition = "VARCHAR(255)   COMMENT '颜色'")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public IntervalType getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(IntervalType intervalType) {
        this.intervalType = intervalType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public LocalDateTime getSetTime() {
        return setTime;
    }

    public void setSetTime(LocalDateTime setTime) {
        this.setTime = setTime;
    }
}