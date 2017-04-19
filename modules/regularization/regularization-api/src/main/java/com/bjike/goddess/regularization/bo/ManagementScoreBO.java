package com.bjike.goddess.regularization.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 管理层评分业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-17 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ManagementScoreBO extends BaseBO {

    /**
     * 管理层
     */
    private String management;

    /**
     * 管理层意见
     */
    private String opinion;

    /**
     * 评分等级
     */
    private String scoreGrade;

    /**
     * 具体分数
     */
    private Integer specificScore;

    /**
     * 员工转正id
     */
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

    public Integer getSpecificScore() {
        return specificScore;
    }

    public void setSpecificScore(Integer specificScore) {
        this.specificScore = specificScore;
    }

    public String getRegularizationId() {
        return regularizationId;
    }

    public void setRegularizationId(String regularizationId) {
        this.regularizationId = regularizationId;
    }
}