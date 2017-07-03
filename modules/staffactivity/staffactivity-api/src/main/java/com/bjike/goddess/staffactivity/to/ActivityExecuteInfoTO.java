package com.bjike.goddess.staffactivity.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 活动执行信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:09 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ActivityExecuteInfoTO extends BaseTO {

    /**
     * 人员名单
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "人员名单不能为空")
    private String name;

    /**
     * 活动类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动类型不能为空")
    private String activityType;

    /**
     * 活动名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动名称不能为空")
    private String activityName;

    /**
     * 活动方案
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动方案不能为空")
    private String activityScheme;

    /**
     * 活动角色
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动角色不能为空")
    private String activityRole;

    /**
     * 负责内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "负责内容不能为空")
    private String duty;

    /**
     * 是否完成准备
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否完成准备不能为空")
    private Boolean ifFinishPlan;

    /**
     * 完成情况
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "完成情况不能为空")
    private String completion;

    /**
     * 所遇问题
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所遇问题不能为空")
    private String meetIssues;

    /**
     * 心得总结
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "心得总结不能为空")
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