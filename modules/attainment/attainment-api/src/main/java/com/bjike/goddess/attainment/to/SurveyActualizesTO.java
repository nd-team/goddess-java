package com.bjike.goddess.attainment.to;

import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 调研实施记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyActualizesTO extends BaseTO {

    /**
     * 调研计划id
     */
    @NotNull(message = "调研计划id不能为空", groups = {ADD.class})
    private String planId;

    /**
     * 调研实际开始时间
     */
    private String startTime;

    /**
     * 调研实际结束时间
     */
    private String endTime;

    /**
     * 调研表制作实际完成时间
     */
    private String finishTime;

    /**
     * 调研状态
     */
    private SurveyStatus survey;

    /**
     * 调研表
     */
    private String questionnaire;

    /**
     * 备注
     */
    private String remark;

    /**
     * 调研表问题
     */
    private List<SurveyQuestionnairesTO> surveyQuestionnairesTOs;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
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

    public List<SurveyQuestionnairesTO> getSurveyQuestionnairesTOs() {
        return surveyQuestionnairesTOs;
    }

    public void setSurveyQuestionnairesTOs(List<SurveyQuestionnairesTO> surveyQuestionnairesTOs) {
        this.surveyQuestionnairesTOs = surveyQuestionnairesTOs;
    }
}