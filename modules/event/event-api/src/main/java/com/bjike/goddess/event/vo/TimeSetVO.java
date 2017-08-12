package com.bjike.goddess.event.vo;

import com.bjike.goddess.event.enums.IntervalType;
import com.bjike.goddess.event.enums.Permissions;
import com.bjike.goddess.event.enums.Status;

/**
 * 提醒间隔时间设置表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 04:11 ]
 * @Description: [ 提醒间隔时间设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TimeSetVO {

    /**
     * id
     */
    private String id;
    /**
     * 设置人
     */
    private String name;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 所属项目组/部门
     */
    private String depart;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 权限
     */
    private Permissions permissions;
    /**
     * 间隔类型
     */
    private IntervalType intervalType;
    /**
     * 间隔时长
     */
    private Integer interval;
    /**
     * 提醒间隔时间
     */
    private String intervalTime;

    /**
     * 上次提醒时间
     */
    private String lastTime;

    /**
     * 设置时间
     */
    private String setTime;

    /**
     * 状态
     */
    private Status status;

    /**
     * 颜色
     */
    private String color;

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

    public String getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(String intervalTime) {
        this.intervalTime = intervalTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }
}