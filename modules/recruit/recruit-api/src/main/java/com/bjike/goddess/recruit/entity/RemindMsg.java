package com.bjike.goddess.recruit.entity;


/**
 * 智能消息提醒
 */
public class RemindMsg {
    /**
     * 序号
     */
    private String orderNum;
    /**
     * 发送时间
     */
    private String sendTime;
    /**
     * 消息内容
     */
    private String messageCon;

    public String getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessageCon() {
        return messageCon;
    }
    public void setMessageCon(String messageCon) {
        this.messageCon = messageCon;
    }

    @Override
    public String toString() {
        return "RemindMsg{" +
                "orderNum='" + orderNum + '\'' +
                ", sendTimel='" + sendTime + '\'' +
                ", messageCon='" + messageCon + '\'' +
                '}';
    }
}
