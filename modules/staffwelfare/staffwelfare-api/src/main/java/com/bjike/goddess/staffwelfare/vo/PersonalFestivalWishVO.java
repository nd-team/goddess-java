package com.bjike.goddess.staffwelfare.vo;

/**
 * 节日祝福语表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 03:08 ]
 * @Description: [ 节日祝福语表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PersonalFestivalWishVO {

    /**
     * id
     */
    private String id;
    /**
     * 节日Id
     */
    private String festivalId;

    /**
     * 节日名称
     */
    private String festivalName;

    /**
     * 节日时间
     */
    private String festivalTime;

    /**
     * 感谢语
     */
    private String thankStatement;

    /**
     * 祝福语
     */
    private String wishStatement;

    /**
     * 发送人
     */
    private String sendUser;

    /**
     * 发送人Id
     */
    private String sendUserId;

    /**
     * 接收人
     */
    private String receiveUser;

    /**
     * 接收人Id
     */
    private String receiveUserId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFestivalId() {
        return festivalId;
    }

    public void setFestivalId(String festivalId) {
        this.festivalId = festivalId;
    }

    public String getWishStatement() {
        return wishStatement;
    }

    public void setWishStatement(String wishStatement) {
        this.wishStatement = wishStatement;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getFestivalTime() {
        return festivalTime;
    }

    public void setFestivalTime(String festivalTime) {
        this.festivalTime = festivalTime;
    }

    public String getThankStatement() {
        return thankStatement;
    }

    public void setThankStatement(String thankStatement) {
        this.thankStatement = thankStatement;
    }
}