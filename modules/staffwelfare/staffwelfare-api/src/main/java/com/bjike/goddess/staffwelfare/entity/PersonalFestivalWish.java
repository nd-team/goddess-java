package com.bjike.goddess.staffwelfare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 节日祝福语
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 03:08 ]
 * @Description: [ 节日祝福语 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffwelfare_personalfestivalwish")
public class PersonalFestivalWish extends BaseEntity {

    /**
     * 节日Id
     */
    @Column(name = "festivalId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '节日Id'")
    private String festivalId;

    /**
     * 祝福语
     */
    @Column(name = "wishStatement", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '祝福语'")
    private String wishStatement;

    /**
     * 发送人
     */
    @Column(name = "sendUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送人'")
    private String sendUser;

    /**
     * 发送人Id
     */
    @Column(name = "sendUserId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送人Id'")
    private String sendUserId;

    /**
     * 接收人
     */
    @Column(name = "receiveUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '接收人'")
    private String receiveUser;

    /**
     * 接收人Id
     */
    @Column(name = "receiveUserId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '接收人Id'")
    private String receiveUserId;


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
}