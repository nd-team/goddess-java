package com.bjike.goddess.message.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;

/**
 * 消息推送业务传输对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.618 ]
 * @Description: [ 消息推送业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MessageBO extends BaseBO {

    /**
     * 消息标题
     */
    private String title;
    /**
     * 发送内容
     */
    private String content;

    /**
     * 发送人id,无需初始化，默认当前用户
     */
    private String senderId;
    /**
     * 发送人姓名，无需初始化，默认当前用户
     */
    private String senderName;

    /**
     * 发送时间，无需初始化，默认当前时间
     */
    private String createTime;

    /**
     * 消息类型
     */
    private MsgType msgType;

    /**
     * 发送类型
     */
    private SendType sendType;

    /**
     * 消息范围
     */
    private RangeType rangeType;

    public MessageBO() {
    }

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