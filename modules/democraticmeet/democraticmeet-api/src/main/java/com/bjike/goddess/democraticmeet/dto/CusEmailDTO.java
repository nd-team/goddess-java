package com.bjike.goddess.democraticmeet.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 客户邮件发送定制数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.878 ]
 * @Description: [ 客户邮件发送定制数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusEmailDTO extends BaseDTO {

    /**
     * 会议编号
     */
    private String meetNumber;

    /**
     * 层面
     */
    private String meetLevel;

    public String getMeetNumber() {
        return meetNumber;
    }

    public void setMeetNumber(String meetNumber) {
        this.meetNumber = meetNumber;
    }

    public String getMeetLevel() {
        return meetLevel;
    }

    public void setMeetLevel(String meetLevel) {
        this.meetLevel = meetLevel;
    }
}