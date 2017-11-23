package com.bjike.goddess.event.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.event.enums.EventStatus;
import com.bjike.goddess.event.enums.Permissions;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 事件
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "event_event")
public class Event extends BaseEntity {

    /**
     * 事件内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '事件内容'")
    private String content;

    /**
     * 事件获取时间
     */
    @Column(name = "getTime", nullable = false, columnDefinition = "DATETIME   COMMENT '事件获取时间'")
    private LocalDateTime getTime;

    /**
     * 要求处理时间
     */
    @Column(name = "requestTime", nullable = false, columnDefinition = "DATETIME   COMMENT '要求处理时间'")
    private LocalDateTime requestTime;

    /**
     * 处理人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '处理人'")
    private String name;

    /**
     * 所属地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '所属地区'")
    private String area;

    /**
     * 优先级
     */
    @Column(name = "level", columnDefinition = "INT(11)   COMMENT '优先级'")
    private Integer level;

    /**
     * 实际处理完成时间
     */
    @Column(name = "actualTime",  columnDefinition = "DATETIME   COMMENT '实际处理完成时间'")
    private LocalDateTime actualTime;

    /**
     * 权限
     */
    @Column(name = "permissions", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '权限'")
    private Permissions permissions;

    /**
     * 事件处理状态
     */
    @Column(name = "eventStatus", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '事件处理状态'")
    private EventStatus eventStatus;

    /**
     * 待办事件id
     */
    @Column(name = "event_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '待办事件id'")
    private String eventId;

    /**
     * 父表信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "father_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '父表信息'")
    private Father father;

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;
    /**
     * 待办事件对应状态
     */
    @Column(name = "status",nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '待办事件对应状态'")
    private String status;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getGetTime() {
        return getTime;
    }

    public void setGetTime(LocalDateTime getTime) {
        this.getTime = getTime;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}