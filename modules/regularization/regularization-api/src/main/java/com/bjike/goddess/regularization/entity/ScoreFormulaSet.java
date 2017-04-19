package com.bjike.goddess.regularization.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 工作表现计分方式设置
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "regularization_scoreformulaset")
public class ScoreFormulaSet extends BaseEntity {

    /**
     * 等级
     */
    @Column(name = "rank", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '等级'")
    private String rank;

    /**
     * 最低分数
     */
    @Column(name = "lowestScore", nullable = false, columnDefinition = "INT(11) COMMENT '最低分数'")
    private Integer lowestScore;

    /**
     * 最高分数
     */
    @Column(name = "highestScore", nullable = false, columnDefinition = "INT(11) COMMENT '最高分数'")
    private Integer highestScore;

    /**
     * 分数范围
     */
    @Column(name = "scoreRange", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '分数范围'")
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