package com.bjike.goddess.eggert.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 设置考题
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:09 ]
 * @Description: [ 设置考题 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "eggert_examquestions")
public class ExamQuestions extends BaseEntity {

    /**
     * 问题
     */
    @Column(name = "question", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题'")
    private String question;

    /**
     * 题型
     */
    @Column(name = "topic", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '题型'")
    private String topic;

    /**
     * 是否计分
     */
    @Column(name = "is_scoring", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否计分'", insertable = false)
    private Boolean scoring;

    /**
     * 题型A
     */
    @Column(name = "patternA", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '题型A'")
    private String patternA;

    /**
     * 分值A
     */
    @Column(name = "scoreA", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分值A'")
    private String scoreA;

    /**
     * 题型B
     */
    @Column(name = "patternB", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '题型B'")
    private String patternB;

    /**
     * 分值B
     */
    @Column(name = "scoreB", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分值B'")
    private String scoreB;

    /**
     * 题型C
     */
    @Column(name = "patternC", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '题型C'")
    private String patternC;

    /**
     * 分值C
     */
    @Column(name = "scoreC", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分值C'")
    private String scoreC;

    /**
     * 题型D
     */
    @Column(name = "patternD", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '题型D'")
    private String patternD;

    /**
     * 分值D
     */
    @Column(name = "scoreD", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分值D'")
    private String scoreD;

    /**
     * 题型E
     */
    @Column(name = "patternE", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '题型E'")
    private String patternE;

    /**
     * 分值E
     */
    @Column(name = "scoreE", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分值E'")
    private String scoreE;

    /**
     * 题型F
     */
    @Column(name = "patternF", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '题型F'")
    private String patternF;

    /**
     * 分值F
     */
    @Column(name = "scoreF", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分值F'")
    private String scoreF;

    /**
     * 题号
     */
    @Column(name = "qid", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '题型E'")
    private String qid;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Boolean getScoring() {
        return scoring;
    }

    public void setScoring(Boolean scoring) {
        this.scoring = scoring;
    }

    public String getPatternA() {
        return patternA;
    }

    public void setPatternA(String patternA) {
        this.patternA = patternA;
    }

    public String getScoreA() {
        return scoreA;
    }

    public void setScoreA(String scoreA) {
        this.scoreA = scoreA;
    }

    public String getPatternB() {
        return patternB;
    }

    public void setPatternB(String patternB) {
        this.patternB = patternB;
    }

    public String getScoreB() {
        return scoreB;
    }

    public void setScoreB(String scoreB) {
        this.scoreB = scoreB;
    }

    public String getPatternC() {
        return patternC;
    }

    public void setPatternC(String patternC) {
        this.patternC = patternC;
    }

    public String getScoreC() {
        return scoreC;
    }

    public void setScoreC(String scoreC) {
        this.scoreC = scoreC;
    }

    public String getPatternD() {
        return patternD;
    }

    public void setPatternD(String patternD) {
        this.patternD = patternD;
    }

    public String getScoreD() {
        return scoreD;
    }

    public void setScoreD(String scoreD) {
        this.scoreD = scoreD;
    }

    public String getPatternE() {
        return patternE;
    }

    public void setPatternE(String patternE) {
        this.patternE = patternE;
    }

    public String getScoreE() {
        return scoreE;
    }

    public void setScoreE(String scoreE) {
        this.scoreE = scoreE;
    }

    public String getPatternF() {
        return patternF;
    }

    public void setPatternF(String patternF) {
        this.patternF = patternF;
    }

    public String getScoreF() {
        return scoreF;
    }

    public void setScoreF(String scoreF) {
        this.scoreF = scoreF;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

}