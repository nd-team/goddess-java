package com.bjike.goddess.regularization.vo;

/**
 * 管理层评分表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-17 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ManagementScoreVO {

    /**
     * id
     */
    private String id;
    /**
     * 管理层
     */
    private String management;

    /**
     * 管理层意见
     */
    private String opinion;

    /**
     * 评分等级Integer
     */
    private String scoreGrade;

    /**
     * 员工转正id
     */
    private String regularizationId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}