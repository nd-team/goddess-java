package com.bjike.goddess.message.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-20 10:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "message_group_message")
public class GroupMessage extends BaseEntity {
    /**
     * 组
     */
    @Column(name = "group_id", columnDefinition = "VARCHAR(36) COMMENT '组' ", nullable = false)
    private String groupId;

    /**
     * 消息id
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", columnDefinition = "VARCHAR(36) COMMENT '消息id' ")
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
