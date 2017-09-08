package com.bjike.goddess.organize.vo;

import com.bjike.goddess.organize.enums.IntervalType;

/**
 * Created by ike on 17-9-7.
 */
public class EmailVO {
    /**
     * id
     */
    private String id;
    /**
     * 项目组/部门
     */
    private String depart;
    /**
     * 是否发送至本项目组所有人
     */
    private Boolean all;
    /**
     * 间隔类型
     */
    private IntervalType intervalType;
    /**
     * 间隔时长
     */
    private Integer interval;

    /**
     * 发送时间
     */
    private String setTime;
    /**
     * 发送对象
     */
    private String sendObject;
    /**
     * 上次发送时间
     */
    private String lastTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
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

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
