package com.bjike.goddess.democraticmeet.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 客户邮件发送定制业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.877 ]
 * @Description: [ 客户邮件发送定制业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusEmailVO {

    /**
     * id
     */
    private String id;
    /**
     * 会议编号
     */
    private String meetNumber;

    /**
     * 层面（公司层面/模块）
     */
    private String meetLevel;



    /**
     * 发送对象
     */
    private String[] sendObject;


    /**
     * 创建人
     */
    private String createPersion;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String[] getSendObject() {
        return sendObject;
    }

    public void setSendObject(String[] sendObject) {
        this.sendObject = sendObject;
    }

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}