package com.bjike.goddess.democraticmeet.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 客户邮件发送定制
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.875 ]
 * @Description: [ 客户邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "democraticmeet_cusemail")
public class CusEmail extends BaseEntity {

    /**
     * 会议编号
     */
    @Column(name = "meetNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetNumber;

    /**
     * 层面
     */
    @Column(name = "meetLevel", nullable = false,  columnDefinition = "VARCHAR(255)   COMMENT '层面'")
    private String meetLevel;



    /**
     * 发送对象
     */
    @Column(name = "sendObject", nullable = false, columnDefinition = "MEDIUMTEXT   COMMENT '发送对象'")
    private String sendObject;


    /**
     * 创建人
     */
    @Column(name = "createPersion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String createPersion;

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

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }

}