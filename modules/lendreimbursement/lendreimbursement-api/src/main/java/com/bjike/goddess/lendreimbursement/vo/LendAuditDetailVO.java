package com.bjike.goddess.lendreimbursement.vo;

/**
 * 借款审核人员表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:06 ]
 * @Description: [ 借款审核人员表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LendAuditDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 职位名称
     */
    private String position;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 审核意见
     */
    private String auditSuggest;

    /**
     * 审核时间
     */
    private String auditDate;

    /**
     * 是否通过
     */
    private String passOr;

    /**
     * 借款id
     */
    private String applyLendId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}