package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 调研实施记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_survey_actualize")
public class SurveyActualize extends BaseEntity {

    /**
     * 调研计划
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "plan_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '调研计划'")
    private SurveyPlan plan;

    /**
     * 调研实际开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME   COMMENT '调研实际开始时间'")
    private LocalDateTime startTime;

    /**
     * 调研实际结束时间
     */
    @Column(name = "endTime", columnDefinition = "DATETIME   COMMENT '调研实际结束时间'")
    private LocalDateTime endTime;

    /**
     * 调研表制作实际完成时间
     */
    @Column(name = "finishTime", nullable = false, columnDefinition = "DATETIME   COMMENT '调研表制作实际完成时间'")
    private LocalDateTime finishTime;

    /**
     * 调研状态
     */
    @Column(name = "survey", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '调研状态'", insertable = false)
    private SurveyStatus survey;

    /**
     * 调研表
     */
    @Column(name = "questionnaire", columnDefinition = "VARCHAR(255)   COMMENT '调研表'")
    private String questionnaire;

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public SurveyStatus getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyStatus survey) {
        this.survey = survey;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}