package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 调研计划审核记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:47 ]
 * @Description: [ 调研计划审核记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyPlanAuditTO extends BaseTO {

    /**
     * 调研计划id
     */
    @NotNull(message = "调研计划不能为空", groups = {ADD.class, EDIT.class})
    private String planId;

    /**
     * 是否通过
     */
    @NotNull(message = "是否通过不能为空", groups = {ADD.class, EDIT.class})
    private Boolean pass;

    /**
     * 审核意见
     */
    @NotNull(message = "审核意见不能为空", groups = {ADD.class, EDIT.class})
    private String reason;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Boolean isPass() {
        return pass;
    }

    public void isPass(Boolean pass) {
        this.pass = pass;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }
}