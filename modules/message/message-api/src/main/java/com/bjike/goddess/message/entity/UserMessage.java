package com.bjike.goddess.message.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 用户消息
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21 09:40 ]
 * @Description: [ 用户消息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "message_user_message")
public class UserMessage extends BaseEntity {
    /**
     * 用户
     */
    @Column(name = "user_id", columnDefinition = "VARCHAR(36) COMMENT '订阅用户' ", nullable = false)
    private String userId;

    /**
     * 消息id
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,optional=false)
    @JoinColumn(name = "message_id", columnDefinition = "VARCHAR(36) COMMENT '消息id' ",nullable = false)
    private Message message;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}