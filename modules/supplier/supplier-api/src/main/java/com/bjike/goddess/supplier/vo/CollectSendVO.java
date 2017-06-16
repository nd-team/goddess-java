package com.bjike.goddess.supplier.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.supplier.enums.TimeType;

import javax.persistence.Column;

/**
 * 供应商汇总表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-14 11:48 ]
 * @Description: [ 供应商汇总表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectSendVO {

    /**
     * id
     */
    private String id;

    /**
     * 地区
     */
    private String area;

    /**
     * 间隔
     */
    private Integer interval;

    /**
     * 间隔时间类型
     */
    private TimeType timeInterval;

    /**
     * 汇总间隔
     */
    private Integer collect;

    /**
     * 汇总间隔时间类型
     */
    private TimeType collectInterval;

    /**
     * 描述
     */
    private String description;

    /**
     * 发送邮箱
     */
    private String email;

    /**
     * 邮箱对象
     */
    private String[] emails;

    /**
     * 是否全部发送
     */
    private Boolean all;

    /**
     * 状态
     */
    private Status status;

    /**
     * 最后执行时间
     */
    private String lastTime;

    /**
     * 地区
     */
    private String[] areas;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public TimeType getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(TimeType timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public TimeType getCollectInterval() {
        return collectInterval;
    }

    public void setCollectInterval(TimeType collectInterval) {
        this.collectInterval = collectInterval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}