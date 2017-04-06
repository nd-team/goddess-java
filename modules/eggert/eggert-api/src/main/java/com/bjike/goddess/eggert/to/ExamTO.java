package com.bjike.goddess.eggert.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 答题信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:20 ]
 * @Description: [ 答题信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExamTO extends BaseTO {

    /**
     * 答题时间
     */
    private String answerTime;

    /**
     * 计分项总分
     */
    private String scoringTotalScore;


    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public String getScoringTotalScore() {
        return scoringTotalScore;
    }

    public void setScoringTotalScore(String scoringTotalScore) {
        this.scoringTotalScore = scoringTotalScore;
    }
}