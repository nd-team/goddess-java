package com.bjike.goddess.communicatemeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 交流会组织内容设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 03:31 ]
 * @Description: [ 交流会组织内容设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DesginTO extends BaseTO {

    /**
     * 会议议题
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议议题不能为空")
    private String meetingTopic;

    /**
     * 会议层面
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议层面不能为空")
    private String meetingLevel;

    /**
     * 计划参会岗位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "计划参会岗位不能为空")
    private String planJob;

    /**
     * 计划会议时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "计划会议时间不能为空")
    private String planTime;

//    /**
//     * 计划参会岗位状态
//     */
//    @NotNull(groups = {ADD.class,EDIT.class}, message = "计划参会岗位状态不能为空")
//    private PlanJobStatus planJobStatus;
//
//    /**
//     * 计划会议时间状态
//     */
//    @NotNull(groups = {ADD.class,EDIT.class}, message = "计划会议时间状态不能为空")
//    private PlanTimeStatus planTimeStatus;

    /**
     * 议题包含内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "议题包含内容不能为空")
    private String topicContent;
    /**
     * 会议编号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议编号不能为空")
    private String meetingNumber;

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }

//    public PlanJobStatus getPlanJobStatus() {
//        return planJobStatus;
//    }
//
//    public void setPlanJobStatus(PlanJobStatus planJobStatus) {
//        this.planJobStatus = planJobStatus;
//    }
//
//    public PlanTimeStatus getPlanTimeStatus() {
//        return planTimeStatus;
//    }
//
//    public void setPlanTimeStatus(PlanTimeStatus planTimeStatus) {
//        this.planTimeStatus = planTimeStatus;
//    }


    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getMeetingLevel() {
        return meetingLevel;
    }

    public void setMeetingLevel(String meetingLevel) {
        this.meetingLevel = meetingLevel;
    }

    public String getPlanJob() {
        return planJob;
    }

    public void setPlanJob(String planJob) {
        this.planJob = planJob;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }
}