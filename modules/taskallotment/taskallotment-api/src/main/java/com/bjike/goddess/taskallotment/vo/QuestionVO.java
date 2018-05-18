package com.bjike.goddess.taskallotment.vo;

/**
 * 问题表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-16 10:36 ]
 * @Description: [ 问题表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QuestionVO {

    /**
     * id
     */
    private String id;
    /**
     * 问题审核人
     */
    private String audit;

    /**
     * 受理人
     */
    private String receiver;

    /**
     * 事件
     */
    private String event;

    /**
     * 问题描述
     */
    private String description;

    /**
     * 问题类型
     */
    private String type;

    /**
     * 期望处理时间
     */
    private String expectTime;

    /**
     * 协助部门
     */
    private String assistDepart;

    /**
     * 协助模块
     */
    private String assistModule;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
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