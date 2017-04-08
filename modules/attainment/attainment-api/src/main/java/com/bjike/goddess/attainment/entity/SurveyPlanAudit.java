package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 调研计划审核记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:47 ]
 * @Description: [ 调研计划审核记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_survey_plan_audit")
public class SurveyPlanAudit extends BaseEntity {

    /**
     * 调研计划
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "plan_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '调研计划'")
    private SurveyPlan plan;

    /**
     * 审核人
     */
    @Column(name = "auditor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String auditor;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 是否通过
     */
    @Column(name = "is_pass", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否通过'", insertable = false)
    private Boolean pass;

    /**
     * 审核意见
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String reason;

    /**
     * 审核时间
     */
    @Column(name = "auditTime", nullable = false, columnDefinition = "DATETIME   COMMENT '审核时间'")
    private LocalDateTime auditTime;


    public SurveyPlan getPlan() {
        return plan;
    }

    public void setPlan(SurveyPlan plan) {
        this.plan = plan;
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

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }
}