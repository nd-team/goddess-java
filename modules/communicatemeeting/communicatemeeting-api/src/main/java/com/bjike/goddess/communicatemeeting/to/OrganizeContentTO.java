package com.bjike.goddess.communicatemeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 交流会组织内容
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:16 ]
 * @Description: [ 交流会组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OrganizeContentTO extends BaseTO {

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
     * 议题包含内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "议题包含内容不能为空")
    private String topicContent;

    /**
     * 会议内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议内容不能为空")
    private String meetingContent;

    /**
     * 计划参会岗位数组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "计划参会岗位不能为空")
    private String[] planJobs;

    /**
     * 计划参会人员数组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "计划参会人员不能为空")
    private String[] planPeoples;

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

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getMeetingContent() {
        return meetingContent;
    }

    public void setMeetingContent(String meetingContent) {
        this.meetingContent = meetingContent;
    }

    public String[] getPlanJobs() {
        return planJobs;
    }

    public void setPlanJobs(String[] planJobs) {
        this.planJobs = planJobs;
    }

    public String[] getPlanPeoples() {
        return planPeoples;
    }

    public void setPlanPeoples(String[] planPeoples) {
        this.planPeoples = planPeoples;
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
}