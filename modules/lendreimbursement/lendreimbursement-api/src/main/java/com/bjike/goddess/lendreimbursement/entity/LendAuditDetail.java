package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 借款审核人员
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:06 ]
 * @Description: [ 借款审核人员 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_lendauditdetail")
public class LendAuditDetail extends BaseEntity {

    /**
     * 职位名称
     */
    @Column(name = "position",  columnDefinition = "VARCHAR(255)   COMMENT '职位名称'")
    private String position;
    /**
     * 员工编号
     */
    @Column(name = "empNumber",  columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String empNumber;
    /**
     * 审核身份（负责人/财务/总经办）
     */
    @Column(name = "auditIdentity",  columnDefinition = "VARCHAR(255)   COMMENT '审核身份（负责人/财务/总经办）'")
    private String auditIdentity;

    /**
     * 审核人
     */
    @Column(name = "auditor",  columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String auditor;

    /**
     * 审核意见
     */
    @Column(name = "auditSuggest",  columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String auditSuggest;

    /**
     * 审核时间
     */
    @Column(name = "auditDate",  columnDefinition = "DATE   COMMENT '审核时间'")
    private LocalDate auditDate;

    /**
     * 是否通过(是/否)
     */
    @Column(name = "passOr",  columnDefinition = "VARCHAR(255)   COMMENT '是否通过(是/否)'")
    private String passOr;

    /**
     * 借款id
     */
    @Column(name = "applyLendId",  columnDefinition = "VARCHAR(255)   COMMENT '借款id'")
    private String applyLendId;


    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAuditIdentity() {
        return auditIdentity;
    }

    public void setAuditIdentity(String auditIdentity) {
        this.auditIdentity = auditIdentity;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditSuggest() {
        return auditSuggest;
    }

    public void setAuditSuggest(String auditSuggest) {
        this.auditSuggest = auditSuggest;
    }

    public LocalDate getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
    }

    public String getPassOr() {
        return passOr;
    }

    public void setPassOr(String passOr) {
        this.passOr = passOr;
    }

    public String getApplyLendId() {
        return applyLendId;
    }

    public void setApplyLendId(String applyLendId) {
        this.applyLendId = applyLendId;
    }
}