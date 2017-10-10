package com.bjike.goddess.message.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 消息推送
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.616 ]
 * @Description: [ 消息推送 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "message")
public class Message extends BaseEntity {

    /**
     * 消息标题String
     */
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '消息标题'")
    private String title;
    /**
     * 消息内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "MEDIUMTEXT   COMMENT '消息内容'")
    private String content;

    /**
     * 发送人id
     */
    @Column(name = "senderId", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '发送人id' ")
    private String senderId;
    /**
     * 发送人姓名
     */
    @Column(name = "senderName", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '发送人姓名' ")
    private String senderName;


    /**
     * 消息类型
     */
    @Column(name = "msgType", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '消息类型' ", nullable = false, insertable = false)
    private MsgType msgType;
    /**
     * 发送类型
     */
    @Column(name = "sendType", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '发送类型' ", nullable = false, insertable = false)
    private SendType sendType;
    /**
     * 消息范围
     */
    @Column(name = "rangeType", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '消息范围' ", nullable = false, insertable = false)
    private RangeType rangeType;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public SendType getSendType() {
        return sendType;
    }

    public void setSendType(SendType sendType) {
        this.sendType = sendType;
    }

    public RangeType getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeType rangeType) {
        this.rangeType = rangeType;
    }
}