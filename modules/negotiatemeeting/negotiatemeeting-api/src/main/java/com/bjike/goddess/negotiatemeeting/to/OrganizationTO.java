package com.bjike.goddess.negotiatemeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 协商会议组织内容
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:30 ]
 * @Description: [ 协商会议组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OrganizationTO extends BaseTO {

    /**
     * 会议类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议类型不能为空")
    private String meetingType;

    /**
     * 会议层面
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议层面不能为空")
    private String meetingLevel;

    /**
     * 会议议题
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议议题不能为空")
    private String meetingTopic;

    /**
     * 议题产生原因
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "议题产生原因不能为空")
    private String reason;

    /**
     * 议题目的
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "议题目的不能为空")
    private String purpose;

    /**
     * 计划参会岗位
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "计划参会岗位不能为空")
    private String[] planJobs;

    /**
     * 计划参会人员
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "计划参会人员不能为空")
    private String[] planAttends;

    /**
     * 计划会议时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "计划会议时间不能为空")
    private String planTime;

    /**
     * 会议形式
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议形式不能为空")
    private String meetingFormat;

    /**
     * 会议地点
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议地点不能为空")
    private String meetingArea;

    /**
     * 会议主持人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议主持人不能为空")
    private String host;

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingLevel() {
        return meetingLevel;
    }

    public void setMeetingLevel(String meetingLevel) {
        this.meetingLevel = meetingLevel;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String[] getPlanJobs() {
        return planJobs;
    }

    public void setPlanJobs(String[] planJobs) {
        this.planJobs = planJobs;
    }

    public String[] getPlanAttends() {
        return planAttends;
    }

    public void setPlanAttends(String[] planAttends) {
        this.planAttends = planAttends;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getMeetingFormat() {
        return meetingFormat;
    }

    public void setMeetingFormat(String meetingFormat) {
        this.meetingFormat = meetingFormat;
    }

    public String getMeetingArea() {
        return meetingArea;
    }

    public void setMeetingArea(String meetingArea) {
        this.meetingArea = meetingArea;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}