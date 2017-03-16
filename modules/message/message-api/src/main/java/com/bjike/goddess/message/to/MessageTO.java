package com.bjike.goddess.message.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.SendType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 消息推送
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.619 ]
 * @Description: [ 消息推送 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MessageTO extends BaseTO {
    public MessageTO() {

    }

    public MessageTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * 消息标题
     */
    @NotBlank(message = "消息标题不能为空", groups = ADD.class)
    private String title;
    /**
     * 发送内容
     */
    @NotBlank(message = "消息内容不能为空", groups = ADD.class)
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
    @NotBlank(message = "消息类型不能为空", groups = ADD.class)
    private MsgType msgType;

    /**
     * 发送类型
     */
    @NotBlank(message = "发送类型不能为空", groups = ADD.class)
    private SendType sendType;

    /**
     * 接收人,邮箱
     */
    @NotBlank(message = "接收人不能为空", groups = ADD.class)
    private String[] receivers;

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

    public String[] getReceivers() {
        return receivers;
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
    }
}