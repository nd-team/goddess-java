package com.bjike.goddess.regularization.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 管理层评分
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-17 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "regularization_managementscore")
public class ManagementScore extends BaseEntity {

    /**
     * 管理层
     */
    @Column(name = "management", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '管理层'")
    private String management;

    /**
     * 管理层意见
     */
    @Column(name = "opinion", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '管理层意见'")
    private String opinion;

    /**
     * 评分等级
     */
    @Column(name = "scoreGrade", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '评分等级'")
    private String scoreGrade;

    /**
     * 具体分数
     */
    @Column(name = "specificScore", nullable = false, columnDefinition = "INT(11) COMMENT '具体分数'")
    private Integer specificScore;

    /**
     * 员工转正id
     */
    @Column(name = "regularizationId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '员工转正id'")
    private String regularizationId;


    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getScoreGrade() {
        return scoreGrade;
    }

    public void setScoreGrade(String scoreGrade) {
        this.scoreGrade = scoreGrade;
    }

    public String getRegularizationId() {
        return regularizationId;
    }

    public void setRegularizationId(String regularizationId) {
        this.regularizationId = regularizationId;
    }

    public Integer getSpecificScore() {
        return specificScore;
    }

    public void setSpecificScore(Integer specificScore) {
        this.specificScore = specificScore;
    }
}