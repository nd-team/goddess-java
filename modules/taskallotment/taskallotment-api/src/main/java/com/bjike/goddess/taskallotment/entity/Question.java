package com.bjike.goddess.taskallotment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 问题
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-16 10:36 ]
 * @Description: [ 问题 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "taskallotment_question")
public class Question extends BaseEntity {

    /**
     * 问题审核人
     */
    @Column(name = "audit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题审核人'")
    private String audit;

    /**
     * 受理人
     */
    @Column(name = "receiver", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '受理人'")
    private String receiver;

    /**
     * 事件
     */
    @Column(name = "event", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '事件'")
    private String event;

    /**
     * 问题描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题描述'")
    private String description;

    /**
     * 问题类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题类型'")
    private String type;

    /**
     * 期望处理时间
     */
    @Column(name = "expectTime", nullable = false, columnDefinition = "DATETIME   COMMENT '期望处理时间'")
    private LocalDateTime expectTime;

    /**
     * 协助部门
     */
    @Column(name = "assistDepart", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协助部门'")
    private String assistDepart;

    /**
     * 协助模块
     */
    @Column(name = "assistModule", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协助模块'")
    private String assistModule;

    /**
     * 任务节点信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taskNode_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '任务节点信息'")
    private TaskNode taskNode;

    public TaskNode getTaskNode() {
        return taskNode;
    }

    public void setTaskNode(TaskNode taskNode) {
        this.taskNode = taskNode;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(LocalDateTime expectTime) {
        this.expectTime = expectTime;
    }

    public String getAssistDepart() {
        return assistDepart;
    }

    public void setAssistDepart(String assistDepart) {
        this.assistDepart = assistDepart;
    }

    public String getAssistModule() {
        return assistModule;
    }

    public void setAssistModule(String assistModule) {
        this.assistModule = assistModule;
    }
}