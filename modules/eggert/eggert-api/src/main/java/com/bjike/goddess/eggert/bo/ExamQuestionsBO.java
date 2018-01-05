package com.bjike.goddess.eggert.bo;

import com.bjike.goddess.common.api.bo.BaseBO;


/**
 * 设置考题业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:09 ]
 * @Description: [ 设置考题业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExamQuestionsBO extends BaseBO {

    /**
     * 问题
     */
    private String question;

    /**
     * 题型
     */
    private String topic;

    /**
     * 是否计分
     */
    private Boolean scoring;

    /**
     * 题型A
     */
    private String patternA;

    /**
     * 分值A
     */
    private String scoreA;

    /**
     * 题型B
     */
    private String patternB;

    /**
     * 分值B
     */
    private String scoreB;

    /**
     * 题型C
     */
    private String patternC;

    /**
     * 分值C
     */
    private String scoreC;

    /**
     * 题型D
     */
    private String patternD;

    /**
     * 分值D
     */
    private String scoreD;

    /**
     * 题型E
     */
    private String patternE;

    /**
     * 分值E
     */
    private String scoreE;

    /**
     * 题型F
     */
    private String patternF;

    /**
     * 分值F
     */
    private String scoreF;

    /**
     * 题号
     */
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