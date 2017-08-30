package com.bjike.goddess.attainment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 调研表问题选项业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:26 ]
 * @Description: [ 调研表问题选项业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnaireOptionBO extends BaseBO {

    /**
     * 问题
     */
    private String questionnaireName;

    /**
     * 问题ID
     */
    private String questionnaireId;

    /**
     * 选项
     */
    private String content;

    /**
     * 票数
     */
    private Integer ballot;


    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
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