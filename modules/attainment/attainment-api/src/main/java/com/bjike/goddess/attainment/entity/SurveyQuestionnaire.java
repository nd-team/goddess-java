package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 调研表 -> 问题
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:23 ]
 * @Description: [ 调研表 -> 问题 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_survey_questionnaire")
public class SurveyQuestionnaire extends BaseEntity {

    /**
     * 调研实施
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "actualize_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '调研实施'")
    private SurveyActualize actualize;

    /**
     * 问题
     */
    @Column(name = "questionnaire", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题'")
    private String questionnaire;

    /**
     * 单选/多选
     */
    @Column(name = "is_multiple", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '单选/多选'", insertable = false)
    private Boolean multiple;

    /**
     * 题号
     */
    @Column(name = "num", nullable = false, columnDefinition = "INT(11)  COMMENT '题号'")
    private Integer num;


    public SurveyActualize getActualize() {
        return actualize;
    }

    public void setActualize(SurveyActualize actualize) {
        this.actualize = actualize;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}