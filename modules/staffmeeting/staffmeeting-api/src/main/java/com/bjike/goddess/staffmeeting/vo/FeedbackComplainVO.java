package com.bjike.goddess.staffmeeting.vo;

/**
 * 通告反馈投诉表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 04:23 ]
 * @Description: [ 通告反馈投诉表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FeedbackComplainVO {

    /**
     * id
     */
    private String id;
    /**
     * 公司
     */
    private String meetingNum;

    /**
     * 异议人
     */
    private String dissentUser;

    /**
     * 异议人员工编号
     */
    private String dissentUserNum;

    /**
     * 异议内容
     */
    private String dissentContent;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDissentContent() {
        return dissentContent;
    }

    public void setDissentContent(String dissentContent) {
        this.dissentContent = dissentContent;
    }

    public String getDissentUserNum() {
        return dissentUserNum;
    }

    public void setDissentUserNum(String dissentUserNum) {
        this.dissentUserNum = dissentUserNum;
    }
}