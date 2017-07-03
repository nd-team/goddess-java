package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 调研表问题
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:23 ]
 * @Description: [ 调研表问题 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnaireTO extends BaseTO {

    /**
     * 调研实施id
     */
    @NotNull(message = "调研实施id不能为空", groups = {ADD.class, EDIT.class})
    private String actualizeId;

    /**
     * 问题
     */
    @NotNull(message = "问题不能为空", groups = {ADD.class, EDIT.class})
    private String questionnaire;

    /**
     * 单选多选
     */
    @NotNull(message = "单选/多选不能为空", groups = {ADD.class, EDIT.class})
    private Boolean multiple;

    /**
     * 题号
     */
    @NotNull(message = "题号不能为空", groups = {ADD.class, EDIT.class})
    private Integer num;


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


}