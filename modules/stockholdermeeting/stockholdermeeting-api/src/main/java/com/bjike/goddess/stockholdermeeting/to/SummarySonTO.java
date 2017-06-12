package com.bjike.goddess.stockholdermeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 股东大会纪要子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-07 10:53 ]
 * @Description: [ 股东大会纪要子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummarySonTO extends BaseTO {

    /**
     * 发言部门
     */
    @NotBlank(groups = {EDIT.class, ADD.class}, message = "发言部门不能为空")
    private String speakDepartment;

    /**
     * 发言岗位
     */
    @NotBlank(groups = {EDIT.class, ADD.class}, message = "发言岗位不能为空")
    private String speakJob;

    /**
     * 发言人
     */
    @NotBlank(groups = {EDIT.class, ADD.class}, message = "发言人不能为空")
    private String speaker;

    /**
     * 发言内容
     */
    @NotBlank(groups = {EDIT.class, ADD.class}, message = "发言内容不能为空")
    private String speakContent;

    /**
     * 是否修改发言内容
     */
    @NotNull(groups = {EDIT.class}, message = "是否修改发言内容不能为空")
    private Boolean alter;

    /**
     * 修改后的发言内容
     */
    private String alterContent;

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

    public String getSpeakDepartment() {
        return speakDepartment;
    }

    public void setSpeakDepartment(String speakDepartment) {
        this.speakDepartment = speakDepartment;
    }

    public String getSpeakJob() {
        return speakJob;
    }

    public void setSpeakJob(String speakJob) {
        this.speakJob = speakJob;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getSpeakContent() {
        return speakContent;
    }

    public void setSpeakContent(String speakContent) {
        this.speakContent = speakContent;
    }

    public Boolean getAlter() {
        return alter;
    }

    public void setAlter(Boolean alter) {
        this.alter = alter;
    }

    public String getAlterContent() {
        return alterContent;
    }

    public void setAlterContent(String alterContent) {
        this.alterContent = alterContent;
    }
}