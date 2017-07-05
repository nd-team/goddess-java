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
     * 是否通过
     */
    @Column(name = "passOr",  columnDefinition = "VARCHAR(255)   COMMENT '是否通过'")
    private String passOr;

    /**
     * 借款id
     */
    @Column(name = "applyLendId",  columnDefinition = "VARCHAR(255)   COMMENT '借款id'")
    private String applyLendId;


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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