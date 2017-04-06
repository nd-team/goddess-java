package com.bjike.goddess.eggert.vo;

import java.time.LocalDate;

/**
 * 答题信息表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:20 ]
 * @Description: [ 答题信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExamVO {

    /**
     * id
     */
    private String id;
    /**
     * 答题时间
     */
    private LocalDate answerTime;

    /**
     * 计分项总分
     */
    private String scoringTotalScore;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(LocalDate answerTime) {
        this.answerTime = answerTime;
    }

    public String getScoringTotalScore() {
        return scoringTotalScore;
    }

    public void setScoringTotalScore(String scoringTotalScore) {
        this.scoringTotalScore = scoringTotalScore;
    }
}