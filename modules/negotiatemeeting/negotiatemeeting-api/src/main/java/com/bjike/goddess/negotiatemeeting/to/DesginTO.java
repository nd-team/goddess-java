package com.bjike.goddess.negotiatemeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 协商会议组织内容设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 05:39 ]
 * @Description: [ 协商会议组织内容设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DesginTO extends BaseTO {

    /**
     * 会议层面
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议层面不能为空")
    private String meetingLevel;

    /**
     * 议题包含内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "议题包含内容不能为空")
    private String content;

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

    /**
     * 会议议题
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议议题不能为空")
    private String meetingTopic;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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