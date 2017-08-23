package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.supplier.enums.TimeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 供应商汇总
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-14 11:48 ]
 * @Description: [ 供应商汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_collectsend")
public class CollectSend extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 间隔
     */
    @Column(name = "time", nullable = false, columnDefinition = "INT(11)   COMMENT '间隔'")
    private Integer interval;

    /**
     * 间隔时间类型
     */
    @Column(name = "timeInterval", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '间隔时间类型'")
    private TimeType timeInterval;

    /**
     * 汇总间隔
     */
    @Column(name = "collect", nullable = false, columnDefinition = "INT(11)   COMMENT '汇总间隔'")
    private Integer collect;

    /**
     * 汇总间隔时间类型
     */
    @Column(name = "collectInterval", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '汇总间隔时间类型'")
    private TimeType collectInterval;

    /**
     * 描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 发送邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(255)   COMMENT '发送邮箱'")
    private String email;

    /**
     * 是否全部发送
     */
    @Column(name = "is_all", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否全部发送'")
    private Boolean all;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '状态'", insertable = false)
    private Status status;

    /**
     * 最后执行时间
     */
    @Column(name = "lastTime", columnDefinition = "DATETIME COMMENT '上次执行时间'", nullable = false)
    private LocalDateTime lastTime;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }
}