package com.bjike.goddess.event.vo;

import com.bjike.goddess.event.enums.EventStatus;
import com.bjike.goddess.event.enums.Permissions;

/**
 * 事件表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EventVO {

    /**
     * id
     */
    private String id;
    /**
     * 事件内容
     */
    private String content;

    /**
     * 事件获取时间
     */
    private String getTime;

    /**
     * 要求处理时间
     */
    private String requestTime;

    /**
     * 处理人
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
     * 岗位名称
     */
    private String position;

    /**
     * 权限
     */
    private Permissions permissions;

    /**
     * 优先级
     */
    private Integer level;

    /**
     * 提醒间隔时间
     */
    private String remindTime;

    /**
     * 实际处理完成时间
     */
    private String actualTime;

    /**
     * 事件处理状态
     */
    private EventStatus eventStatus;

    /**
     * 颜色
     */
    private String color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}