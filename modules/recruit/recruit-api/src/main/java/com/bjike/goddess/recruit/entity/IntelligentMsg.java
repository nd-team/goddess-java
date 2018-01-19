package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 智能消息提醒
 *
 * @Author: [ wany ]
 * @Date: [ 2018-01-16 11:07 ]
 * @Description: [ 智能消息提醒 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_intelligentmsg")
public class IntelligentMsg extends BaseEntity {

    /**
     * 发送时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送时间'")
    private String time;

    /**
     * 提醒内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '提醒内容'")
    private String content;

    /**
     * 用户
     */
    @Column(name = "user", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用户'")
    private String user;

    public IntelligentMsg() {

    }

    public IntelligentMsg(String time, String content, String user) {
        this.time = time;
        this.content = content;
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}