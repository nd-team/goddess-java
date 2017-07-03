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
public class ConciseDiscussionVO {

    /**
     * id
     */
    private String id;
    /**
     * 发言人内容
     */
    private String firstDis;

    /**
     * 发言人
     */
    private String user;

    /**
     * 员工编号
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