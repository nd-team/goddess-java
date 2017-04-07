package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 问卷调查历史记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_survey_questionnaire_user")
public class SurveyQuestionnaireUser extends BaseEntity {

    /**
     * 问卷
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "actualize_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '问卷'")
    private SurveyActualize questionnaire;

    /**
     * 用户
     */
    @Column(name = "user", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用户'")
    private String user;


    public SurveyActualize getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(SurveyActualize questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}