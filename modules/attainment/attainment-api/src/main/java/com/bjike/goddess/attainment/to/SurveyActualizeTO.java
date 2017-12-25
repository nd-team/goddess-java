package com.bjike.goddess.attainment.to;

import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 调研实施记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyActualizeTO extends BaseTO {

    /**
     * 调研计划id
     */
    @NotNull(message = "调研计划id不能为空", groups = {ADD.class, EDIT.class})
    private String planId;

    /**
     * 调研表制作实际完成时间
     */
    @NotBlank(message = "调研表制作实际完成时间不能为空", groups = {ADD.class, EDIT.class})
    private String finishTime;

    /**
     * 调研表
     */
    private String questionnaire;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {ADD.class, EDIT.class})
    private String remark;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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
}