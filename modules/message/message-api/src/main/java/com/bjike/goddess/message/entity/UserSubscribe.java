package com.bjike.goddess.message.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.message.enums.MsgType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户订阅消息类型
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-20 09:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = " user_message_subscribe")
public class UserSubscribe extends BaseEntity {
    /**
     * 消息类型
     */
    @Column(name = "msgType", columnDefinition = "TINYINT(1)  COMMENT '消息类型' ", nullable = false)
    private MsgType msgType;
    /**
     * 订阅用户
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '订阅用户' ", nullable = false)
    private String userId;

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
