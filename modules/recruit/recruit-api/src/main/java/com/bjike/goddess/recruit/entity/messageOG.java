package com.bjike.goddess.recruit.entity;

import javax.persistence.Entity;

/**
 * 对赌消息
 */
public class messageOG {
    /**
     * 序号
     */
    private String orderNum;

    /**
     * 对赌人
     */
    private String peopleOG;

    /**
     * 对赌时间
     */
    private String timeOG;

    /**
     * 对赌内容
     */
    private String contentOG;

    /**
     * 对赌结果
     */
    private String resultOG;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTimeOG() {
        return timeOG;
    }

    public void setTimeOG(String timeOG) {
        this.timeOG = timeOG;
    }

    public String getContentOG() {
        return contentOG;
    }

    public void setContentOG(String contentOG) {
        this.contentOG = contentOG;
    }

    public String getResultOG() {
        return resultOG;
    }

    public void setResultOG(String resultOG) {
        this.resultOG = resultOG;
    }

    public String getPeopleOG() {
        return peopleOG;
    }

    public void setPeopleOG(String peopleOG) {
        this.peopleOG = peopleOG;
    }

    @Override
    public String toString() {
        return "messageOG{" +
                "orderNum='" + orderNum + '\'' +
                ", peopleOG='" + peopleOG + '\'' +
                ", timeOG='" + timeOG + '\'' +
                ", contentOG='" + contentOG + '\'' +
                ", resultOG='" + resultOG + '\'' +
                '}';
    }
}
