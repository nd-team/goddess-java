package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 问卷填写信息表
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnaireOptionUsersTO extends BaseTO {

    /**
     * 选项列表
     */
    private List<SurveyQuestionnaireOptionUserTO> surveyQuestionnaireOptionUserTOs;


    public List<SurveyQuestionnaireOptionUserTO> getSurveyQuestionnaireOptionUserTOs() {
        return surveyQuestionnaireOptionUserTOs;
    }

    public void setSurveyQuestionnaireOptionUserTOs(List<SurveyQuestionnaireOptionUserTO> surveyQuestionnaireOptionUserTOs) {
        this.surveyQuestionnaireOptionUserTOs = surveyQuestionnaireOptionUserTOs;
    }
}