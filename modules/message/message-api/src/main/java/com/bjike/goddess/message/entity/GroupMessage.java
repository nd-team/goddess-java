package com.bjike.goddess.message.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 组消息
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:07:50.363 ]
 * @Description: [ 组消息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "group_message")
public class GroupMessage extends BaseEntity {
    /**
     * 组
     */
    @Column(name = "group_id", columnDefinition = "VARCHAR(36) COMMENT '组' ", nullable = false)
    private String groupId;

    /**
     * 消息id
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", columnDefinition = "VARCHAR(36) COMMENT '消息id' ", nullable = false)
    private Message message;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}