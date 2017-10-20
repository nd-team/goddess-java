package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 内部协助单记录
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:30 ]
 * @Description: [ 内部协助单记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "task_record")
public class Record extends BaseEntity {

    /**
     * 发邮人
     */
    @Column(name = "sender", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发邮人'")
    private String sender;

    /**
     * 发邮时间
     */
    @Column(name = "sendTime", nullable = false, columnDefinition = "DATETIME   COMMENT '发邮时间'")
    private LocalDateTime sendTime;

    /**
     * 内部协助单号
     */
    @Column(name = "num", nullable = false, columnDefinition = "INT(11)   COMMENT '内部协助单号'")
    private Integer num;

    /**
     * 协助说明情况
     */
    @Column(name = "situation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协助说明情况'")
    private String situation;


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}