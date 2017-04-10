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
     * 调研计划
     */
    @NotNull(message = "调研计划不能为空", groups = {ADD.class, EDIT.class})
    private String plan_id;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 岗位
     */
    private String position;

    /**
     * 部门
     */
    private String department;

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

    /**
     * 审核时间
     */
    private String auditTime;


    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }
}