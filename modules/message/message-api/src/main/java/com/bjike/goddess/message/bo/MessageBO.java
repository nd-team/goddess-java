package com.bjike.goddess.message.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.user.entity.User;

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
     * 消息标题String
     */
    private String title;

    /**
     * 发送人
     */
    private User sender;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}