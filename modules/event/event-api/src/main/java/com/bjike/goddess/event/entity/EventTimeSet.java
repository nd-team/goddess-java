package com.bjike.goddess.event.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.event.enums.EventTime;
import com.bjike.goddess.event.enums.IntervalType;
import com.bjike.goddess.event.enums.Status;
import org.apache.poi.ss.formula.functions.Even;

import javax.persistence.*;

/**
 * 日历代办提醒间隔时间设置
 *
 * @Author: [chenjunhao]
 * @Date: [2017-12-29 14:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "event_eventtimeset")
public class EventTimeSet extends BaseEntity {

    /**
     * 设置人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '设置人'")
    private String name;

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
     * 状态
     *    是否重复
     *      是 - 正常
     *      否 - 冻结
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    /**
     * 提前多少分钟
     */
    @Column(name = "eventTime", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '提前多少分钟'")
    private EventTime eventTime;

    /**
     * 父表信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '父表信息'")
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public EventTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(EventTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IntervalType getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(IntervalType intervalType) {
        this.intervalType = intervalType;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}






