package com.bjike.goddess.event.vo;

import com.bjike.goddess.event.entity.Event;
import com.bjike.goddess.event.enums.EventTime;
import com.bjike.goddess.event.enums.IntervalType;
import com.bjike.goddess.event.enums.Status;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-12-29 15:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EventTimeSetVO {

    /**
     * 设置人
     */
    private String name;

    /**
     * 间隔类型
     */
    private IntervalType intervalType;

    /**
     * 间隔时长
     */
    private Integer interval;

    /**
     * 状态
     *    是否重复
     *      是 - 正常
     *      否 - 冻结
     */
    private Status status;

    /**
     * 提前多少分钟
     */
    private EventTime eventTime;

    /**
     * 父表信息
     */
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
