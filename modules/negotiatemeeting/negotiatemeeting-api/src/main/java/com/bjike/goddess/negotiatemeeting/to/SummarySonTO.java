package com.bjike.goddess.negotiatemeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 协商会议纪要子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-01 05:14 ]
 * @Description: [ 协商会议纪要子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummarySonTO extends BaseTO {

    /**
     * 参会人
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "参会人不能为空")
    private String attend;

    /**
     * 协商意见
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "协商意见不能为空")
    private String opinion;

    /**
     * 会议编号
     */
    @NotBlank(groups = {ADD.class}, message = "会议编号不能为空")
    private String meetingNumber;

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }
}