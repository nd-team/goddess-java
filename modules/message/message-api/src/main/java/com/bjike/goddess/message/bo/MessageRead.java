package com.bjike.goddess.message.bo;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-20 11:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MessageRead {
    /**
     * 接收人
     */
    private List<String> userId;
    /**
     * 消息id
     */
    private String messageId;

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
