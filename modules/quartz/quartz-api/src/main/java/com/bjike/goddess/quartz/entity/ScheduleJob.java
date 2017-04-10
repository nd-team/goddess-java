package com.bjike.goddess.quartz.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 任务调度
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:24 ]
 * @Description: [ 任务调度 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "quartz_schedulejob")
public class ScheduleJob extends BaseEntity {

    /**
     * 制定人
     */
    @Column(name = "userId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '制定人'")
    private String userId;

    /**
     * 执行类
     */
    @Column(name = "clazz", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '执行类'")
    private String clazz;

    /**
     * 任务名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务名'")
    private String name;

    /**
     * 执行方法
     */
    @Column(name = "method", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '执行方法'")
    private String method;

    /**
     * 表达式
     */
    @Column(name = "expression", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '表达式'")
    private String expression;

    /**
     * 描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 是否启用
     */
    @Column(name = "is_enable", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '是否启用'", insertable = false)
    private Boolean enable;

    /**
     * 任务调度组
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleJobGroup",nullable = false ,columnDefinition = "VARCHAR(36) COMMENT '任务调度组' ")
    private ScheduleJobGroup scheduleJobGroup;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public ScheduleJobGroup getScheduleJobGroup() {
        return scheduleJobGroup;
    }

    public void setScheduleJobGroup(ScheduleJobGroup scheduleJobGroup) {
        this.scheduleJobGroup = scheduleJobGroup;
    }
}