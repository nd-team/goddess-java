package com.bjike.goddess.allmeeting.vo;

/**
 * 会议讨论意见表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 03:10 ]
 * @Description: [ 会议讨论意见表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingDiscussionVO {

    /**
     * id
     */
    private String id;
    /**
     * 一轮意见
     */
    private String firstDis;

    /**
     * 二轮意见
     */
    private String secondDis;

    /**
     * 最终意见
     */
    private String finalDis;

    /**
     * 发言人
     */
    private String user;

    /**
     * 发言人员工编号
     */
    private String userNum;

    /**
     * 纪要Id
     */
    private String summaryId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstDis() {
        return firstDis;
    }

    public void setFirstDis(String firstDis) {
        this.firstDis = firstDis;
    }

    public String getSecondDis() {
        return secondDis;
    }

    public void setSecondDis(String secondDis) {
        this.secondDis = secondDis;
    }

    public String getFinalDis() {
        return finalDis;
    }

    public void setFinalDis(String finalDis) {
        this.finalDis = finalDis;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }
}