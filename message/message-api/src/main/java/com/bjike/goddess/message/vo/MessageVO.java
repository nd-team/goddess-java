package com.bjike.goddess.message.vo;

import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.user.entity.User;

/**
 * 消息推送表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.620 ]
 * @Description: [ 消息推送表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MessageVO {

    /**
     * 消息标题
     */
    private String title;
    /**
     * 发送内容
     */
    private String content;

    /**
     * 发送人
     */
    private String senderName;

    /**
     * 发送时间
     */
    private String createTime;

    /**
     * 消息类型
     */
    private MsgType msgType;

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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }
}