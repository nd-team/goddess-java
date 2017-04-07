package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 问卷调查历史记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnaireUserTO extends BaseTO {

    /**
     * 调研实施id
     */
    private String questionnaire_id;

    /**
     * 用户
     */
    private String user;


    public String getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(String questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}