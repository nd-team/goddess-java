package com.bjike.goddess.eggert.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 答题信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:20 ]
 * @Description: [ 答题信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "eggert_exam")
public class Exam extends BaseEntity {

    /**
     * 答题时间
     */
    @Column(name = "answerTime", nullable = false, columnDefinition = "DATE   COMMENT '答题时间'")
    private LocalDate answerTime;

    /**
     * 计分项总分
     */
    @Column(name = "scoringTotalScore", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT ''")
    private String scoringTotalScore;

    /**
     * 调研设置信息
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "researchSettingsInfo_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '调研设置信息'")
    private ResearchSettingsInfo researchSettingsInfo;


    /**
     * 分析记录
     */
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "exam", fetch = FetchType.LAZY)
    private Analysis analysis;

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

    public ResearchSettingsInfo getResearchSettingsInfo() {
        return researchSettingsInfo;
    }

    public void setResearchSettingsInfo(ResearchSettingsInfo researchSettingsInfo) {
        this.researchSettingsInfo = researchSettingsInfo;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }
}