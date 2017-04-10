package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 调研表问题选项
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:26 ]
 * @Description: [ 调研表问题选项 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_survey_questionnaire_option")
public class SurveyQuestionnaireOption extends BaseEntity {

    /**
     * 问题
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "questionnaire_id", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题'")
    private SurveyQuestionnaire questionnaire;

    /**
     * 选项
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '选项'")
    private String content;

    /**
     * 票数
     */
    @Column(name = "ballot", nullable = false, columnDefinition = "INT(11) DEFAULT '0'   COMMENT '票数'")
    private Integer ballot;


    public SurveyQuestionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(SurveyQuestionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBallot() {
        return ballot;
    }

    public void setBallot(Integer ballot) {
        this.ballot = ballot;
    }
}