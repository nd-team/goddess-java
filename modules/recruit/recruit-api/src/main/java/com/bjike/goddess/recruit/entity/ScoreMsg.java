package com.bjike.goddess.recruit.entity;

import javax.persistence.Entity;

/**
 * 评分消息
 */
public class ScoreMsg {

    /**
     * 序号
     */
    private String orderNum;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 评分时间
     */
    private String scoringTime;

    /**
     * 评分人
     */
    private String rater;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 评分内容
     */

    private String scoreContent;

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

    public String getScoringTime() {
        return scoringTime;
    }

    public void setScoringTime(String scoringTime) {
        this.scoringTime = scoringTime;
    }

    public String getRater() {
        return rater;
    }

    public void setRater(String rater) {
        this.rater = rater;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getScoreContent() {
        return scoreContent;
    }

    public void setScoreContent(String scoreContent) {
        this.scoreContent = scoreContent;
    }

    @Override
    public String toString() {
        return "ScoreMsg{" +
                "orderNum='" + orderNum + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", scoringTime='" + scoringTime + '\'' +
                ", rater='" + rater + '\'' +
                ", score=" + score +
                ", scoreContent='" + scoreContent + '\'' +
                '}';
    }
}
