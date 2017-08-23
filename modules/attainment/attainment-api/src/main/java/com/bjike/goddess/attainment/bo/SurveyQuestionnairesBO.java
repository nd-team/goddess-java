package com.bjike.goddess.attainment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 调研表问题
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:23 ]
 * @Description: [ 调研表问题 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnairesBO extends BaseBO {

    /**
     * 调研实施id
     */
    private String actualizeId;

    /**
     * 问题
     */
    private String questionnaire;

    /**
     * 单选多选
     */
    private Boolean multiple;

    /**
     * 题号
     */
    private Integer num;

    /**
     * 调研表问题选项
     */
    private List<SurveyQuestionnaireOptionBO> surveyQuestionnaireOptionBOs;


    public String getActualizeId() {
        return actualizeId;
    }

    public void setActualizeId(String actualizeId) {
        this.actualizeId = actualizeId;
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

    public List<SurveyQuestionnaireOptionBO> getSurveyQuestionnaireOptionBOs() {
        return surveyQuestionnaireOptionBOs;
    }

    public void setSurveyQuestionnaireOptionBOs(List<SurveyQuestionnaireOptionBO> surveyQuestionnaireOptionBOs) {
        this.surveyQuestionnaireOptionBOs = surveyQuestionnaireOptionBOs;
    }
}