package com.bjike.goddess.negotiatemeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 纪要反馈投诉
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:54 ]
 * @Description: [ 纪要反馈投诉 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryFeedbackTO extends BaseTO {

    /**
     * 会议编号
     */
    @NotBlank(groups = {ADD.class}, message = "会议编号不能为空")
    private String meetingNumber;

    /**
     * 异议人
     */
    private String objection;

    /**
     * 异议内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "异议内容不能为空")
    private String objectionContent;


    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }

    public String getObjection() {
        return objection;
    }

    public void setObjection(String objection) {
        this.objection = objection;
    }

    public String getObjectionContent() {
        return objectionContent;
    }

    public void setObjectionContent(String objectionContent) {
        this.objectionContent = objectionContent;
    }
}