package com.bjike.goddess.attainment.vo;

/**
 * 调研计划审核记录表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:47 ]
 * @Description: [ 调研计划审核记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyPlanAuditVO {

    /**
     * id
     */
    private String id;

    /**
     * 调研计划
     */
    private String planId;

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
    private Boolean pass;

    /**
     * 审核意见
     */
    private String reason;

    /**
     * 审核时间
     */
    private String auditTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
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

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
}