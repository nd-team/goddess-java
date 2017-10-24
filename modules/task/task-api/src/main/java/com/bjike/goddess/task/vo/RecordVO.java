package com.bjike.goddess.task.vo;

/**
 * 内部协助单记录表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:30 ]
 * @Description: [ 内部协助单记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecordVO {

    /**
     * id
     */
    private String id;
    /**
     * 发邮人
     */
    private String sender;

    /**
     * 发邮时间
     */
    private String sendTime;

    /**
     * 内部协助单号
     */
    private Integer num;

    /**
     * 协助说明情况
     */
    private String situation;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
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