package com.bjike.goddess.message.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-20 10:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", columnDefinition = "VARCHAR(36) COMMENT '消息id' ")
    private Message message;

    /**
     * 是否已读
     */
    @Column(name = "is_read", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否已读'", nullable = false, insertable = false)
    private Boolean read;

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

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}
