package com.bjike.goddess.negotiatemeeting.vo;

/**
 * 协商前准备信息表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:39 ]
 * @Description: [ 协商前准备信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReadyVO {

    /**
     * id
     */
    private String id;
    /**
     * 会议编号
     */
    private String meetingNumber;
    /**
     * 会议议题
     */
    private String meetingTopic;

    /**
     * 议题产生原因
     */
    private String reason;
    /**
     * 参会人
     */
    private String attend;

    /**
     * 需要协商的工作内容
     */
    private String content;

    /**
     * 需要协商的工作时长
     */
    private String time;

    /**
     * 协商的工作内容最晚执行完毕期限
     */
    private String timeLimit;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
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

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }
}