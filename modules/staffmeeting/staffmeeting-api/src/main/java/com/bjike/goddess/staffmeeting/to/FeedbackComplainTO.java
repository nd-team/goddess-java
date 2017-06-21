package com.bjike.goddess.staffmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通告反馈投诉
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 04:23 ]
 * @Description: [ 通告反馈投诉 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FeedbackComplainTO extends BaseTO {

    /**
     * 会议编号
     */
    @NotBlank(message = "会议编号不能为空",groups = {ADD.class, EDIT.class})
    private String meetingNum;

    /**
     * 异议人
     */
    @NotBlank(message = "会议编号不能为空",groups = {ADD.class, EDIT.class})
    private String dissentUser;

    /**
     * 异议人员工编号
     */
    @NotBlank(message = "异议人员工编号不能为空",groups = {ADD.class, EDIT.class})
    private String dissentUserNum;

    /**
     * 异议内容
     */
    @NotBlank(message = "异议内容不能为空",groups = {ADD.class, EDIT.class})
    private String dissentContent;


    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getDissentUser() {
        return dissentUser;
    }

    public void setDissentUser(String dissentUser) {
        this.dissentUser = dissentUser;
    }

    public String getDissentUserNum() {
        return dissentUserNum;
    }

    public void setDissentUserNum(String dissentUserNum) {
        this.dissentUserNum = dissentUserNum;
    }

    public String getDissentContent() {
        return dissentContent;
    }

    public void setDissentContent(String dissentContent) {
        this.dissentContent = dissentContent;
    }
}