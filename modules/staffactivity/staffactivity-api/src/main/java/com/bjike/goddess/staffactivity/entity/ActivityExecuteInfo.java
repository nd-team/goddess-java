package com.bjike.goddess.staffactivity.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 活动执行信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:09 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffactivity_activityexecuteinfo")
public class ActivityExecuteInfo extends BaseEntity {

    /**
     * 人员名单
     */
    @Column(name = "name", nullable = false, columnDefinition = "TEXT COMMENT '人员名单'")
    private String name;

    /**
     * 活动类型
     */
    @Column(name = "activityType", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动类型'")
    private String activityType;

    /**
     * 活动名称
     */
    @Column(name = "activityName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动名称'")
    private String activityName;

    /**
     * 活动方案
     */
    @Column(name = "activityScheme", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动方案'")
    private String activityScheme;

    /**
     * 活动角色
     */
    @Column(name = "activityRole", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动角色'")
    private String activityRole;

    /**
     * 负责内容
     */
    @Column(name = "duty", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '负责内容'")
    private String duty;

    /**
     * 是否完成准备
     */
    @Column(name = "ifFinishPlan", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0  COMMENT '是否完成准备'")
    private Boolean ifFinishPlan;

    /**
     * 完成情况
     */
    @Column(name = "completion", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '完成情况'")
    private String completion;

    /**
     * 所遇问题
     */
    @Column(name = "meetIssues", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '所遇问题'")
    private String meetIssues;

    /**
     * 心得总结
     */
    @Column(name = "summary", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '心得总结'")
    private String summary;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityScheme() {
        return activityScheme;
    }

    public void setActivityScheme(String activityScheme) {
        this.activityScheme = activityScheme;
    }

    public String getActivityRole() {
        return activityRole;
    }

    public void setActivityRole(String activityRole) {
        this.activityRole = activityRole;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Boolean getIfFinishPlan() {
        return ifFinishPlan;
    }

    public void setIfFinishPlan(Boolean ifFinishPlan) {
        this.ifFinishPlan = ifFinishPlan;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getMeetIssues() {
        return meetIssues;
    }

    public void setMeetIssues(String meetIssues) {
        this.meetIssues = meetIssues;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}