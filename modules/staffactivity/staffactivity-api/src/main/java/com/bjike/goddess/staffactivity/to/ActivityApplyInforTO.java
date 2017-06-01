package com.bjike.goddess.staffactivity.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 活动申请信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ActivityApplyInforTO extends BaseTO {

    /**
     * 姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "姓名不能为空")
    private String username;

    /**
     * 所属地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属地区不能为空")
    private String area;

    /**
     * 所属项目
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属项目不能为空")
    private String project;

    /**
     * 岗位层级
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "岗位层级不能为空")
    private String postHierarchy;

    /**
     * 原因类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "原因类型不能为空")
    private String reasonType;

    /**
     * 原因详情
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "原因详情不能为空")
    private String reasonDetail;

    /**
     * 活动目的
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动目的不能为空")
    private String activityGoal;

    /**
     * 活动类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动类型不能为空")
    private String activityType;

    /**
     * 活动周期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动周期不能为空")
    private String activityCycle;

    /**
     * 活动开始时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动开始时间不能为空")
    private String startTime;

    /**
     * 活动结束时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动结束时间不能为空")
    private String endTime;

    /**
     * 需要参与人数
     */
    private Integer needAttendNo;

    /**
     * 实际参与人数
     */
    private Integer actualAttendNo;

    /**
     * 放弃原因
     */
    private String abandonReason;


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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getAbandonReason() {
        return abandonReason;
    }

    public void setAbandonReason(String abandonReason) {
        this.abandonReason = abandonReason;
    }
}