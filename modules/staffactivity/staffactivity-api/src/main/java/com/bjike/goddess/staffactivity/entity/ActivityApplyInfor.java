package com.bjike.goddess.staffactivity.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 活动申请信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffactivity_activityapplyinfor")
public class ActivityApplyInfor extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '姓名'")
    private String username;

    /**
     * 所属地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '所属地区'")
    private String area;

    /**
     * 所属项目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '所属项目'")
    private String project;

    /**
     * 岗位层级
     */
    @Column(name = "postHierarchy", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '岗位层级'")
    private String postHierarchy;

    /**
     * 原因类型
     */
    @Column(name = "reasonType", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '原因类型'")
    private String reasonType;

    /**
     * 原因详情
     */
    @Column(name = "reasonDetail", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '原因详情'")
    private String reasonDetail;

    /**
     * 活动目的
     */
    @Column(name = "activityGoal", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动目的'")
    private String activityGoal;

    /**
     * 活动类型
     */
    @Column(name = "activityType", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动类型'")
    private String activityType;

    /**
     * 活动周期
     */
    @Column(name = "activityCycle", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动周期'")
    private String activityCycle;

    /**
     * 活动开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME COMMENT '活动开始时间'")
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME COMMENT '活动结束时间'")
    private LocalDateTime endTime;

    /**
     * 需要参与人数
     */
    @Column(name = "needAttendNo", columnDefinition = "INT(11) COMMENT '需要参与人数'")
    private Integer needAttendNo;

    /**
     * 实际参与人数
     */
    @Column(name = "actualAttendNo", columnDefinition = "INT(11) COMMENT '实际参与人数'")
    private Integer actualAttendNo;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPostHierarchy() {
        return postHierarchy;
    }

    public void setPostHierarchy(String postHierarchy) {
        this.postHierarchy = postHierarchy;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public String getReasonDetail() {
        return reasonDetail;
    }

    public void setReasonDetail(String reasonDetail) {
        this.reasonDetail = reasonDetail;
    }

    public String getActivityGoal() {
        return activityGoal;
    }

    public void setActivityGoal(String activityGoal) {
        this.activityGoal = activityGoal;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityCycle() {
        return activityCycle;
    }

    public void setActivityCycle(String activityCycle) {
        this.activityCycle = activityCycle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getNeedAttendNo() {
        return needAttendNo;
    }

    public void setNeedAttendNo(Integer needAttendNo) {
        this.needAttendNo = needAttendNo;
    }

    public Integer getActualAttendNo() {
        return actualAttendNo;
    }

    public void setActualAttendNo(Integer actualAttendNo) {
        this.actualAttendNo = actualAttendNo;
    }
}