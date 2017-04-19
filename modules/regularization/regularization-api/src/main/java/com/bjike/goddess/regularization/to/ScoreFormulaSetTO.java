package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 工作表现计分方式设置
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ScoreFormulaSetTO extends BaseTO {

    /**
     * 等级
     */
    private String rank;

    /**
     * 最低分数
     */
    private Integer lowestScore;

    /**
     * 最高分数
     */
    private Integer highestScore;

    /**
     * 分数范围
     */
    private String scoreRange;


    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getLowestScore() {
        return lowestScore;
    }

    public void setLowestScore(Integer lowestScore) {
        this.lowestScore = lowestScore;
    }

    public Integer getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(Integer highestScore) {
        this.highestScore = highestScore;
    }

    public String getScoreRange() {
        return scoreRange;
    }

    public void setScoreRange(String scoreRange) {
        this.scoreRange = scoreRange;
    }
}