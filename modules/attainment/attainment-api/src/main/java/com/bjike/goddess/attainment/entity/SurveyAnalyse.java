package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 调研分析
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:50 ]
 * @Description: [ 调研分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_survey_analyse")
public class SurveyAnalyse extends BaseEntity {

    /**
     * 调研计划
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "plan_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '调研计划'")
    private SurveyPlan plan;

    /**
     * 分析人
     */
    @Column(name = "assayer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分析人'")
    private String assayer;

    /**
     * 分析结果1
     */
    @Column(name = "resultOne", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分析结果1'")
    private String resultOne;

    /**
     * 分析结果2
     */
    @Column(name = "resultTwo", columnDefinition = "VARCHAR(255)   COMMENT '分析结果2'")
    private String resultTwo;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public SurveyPlan getPlan() {
        return plan;
    }

    public void setPlan(SurveyPlan plan) {
        this.plan = plan;
    }

    public String getAssayer() {
        return assayer;
    }

    public void setAssayer(String assayer) {
        this.assayer = assayer;
    }

    public String getResultOne() {
        return resultOne;
    }

    public void setResultOne(String resultOne) {
        this.resultOne = resultOne;
    }

    public String getResultTwo() {
        return resultTwo;
    }

    public void setResultTwo(String resultTwo) {
        this.resultTwo = resultTwo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}