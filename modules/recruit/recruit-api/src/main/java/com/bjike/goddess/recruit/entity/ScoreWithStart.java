package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 打分
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-15 10:14 ]
 * @Description: [ 打分 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_scorewithstart")
public class ScoreWithStart extends BaseEntity {

    /**
     * 标签名称
     */
    @Column(name = "label", columnDefinition = "VARCHAR(255)   COMMENT '标签名称'")
    private String label;

    /**
     * 分数
     */
    @Column(name = "score", columnDefinition = "VARCHAR(255)   COMMENT '分数'")
    private String score;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}