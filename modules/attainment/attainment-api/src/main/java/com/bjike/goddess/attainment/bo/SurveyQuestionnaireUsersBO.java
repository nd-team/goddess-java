package com.bjike.goddess.attainment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 问卷调查历史记录业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnaireUsersBO extends BaseBO {

    /**
     * 调研实施id
     */
    private String actualizeId;

    /**
     * 问卷
     */
    private String questionnaireName;

    /**
     * 用户
     */
    private String user;

    /**
     * 用户选项
     */
    private List<SurveyQuestionnaireOptionUserBO> surveyQuestionnaireOptionUserBOList;


    public String getActualizeId() {
        return actualizeId;
    }

    public void setActualizeId(String actualizeId) {
        this.actualizeId = actualizeId;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<SurveyQuestionnaireOptionUserBO> getSurveyQuestionnaireOptionUserBOList() {
        return surveyQuestionnaireOptionUserBOList;
    }

    public void setSurveyQuestionnaireOptionUserBOList(List<SurveyQuestionnaireOptionUserBO> surveyQuestionnaireOptionUserBOList) {
        this.surveyQuestionnaireOptionUserBOList = surveyQuestionnaireOptionUserBOList;
    }
}