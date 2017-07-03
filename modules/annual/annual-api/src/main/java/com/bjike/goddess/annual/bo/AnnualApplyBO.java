package com.bjike.goddess.annual.bo;

import com.bjike.goddess.annual.enums.AuditType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 年假申请业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualApplyBO extends BaseBO {

    /**
     * 年假信息
     */
    private String infoId;

    /**
     * 请假人
     */
    private String infoUsername;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 请假时间
     */
    private String applyTime;

    /**
     * 请假天数
     */
    private Double leave;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 审核结果
     */
    private AuditType audit;

    /**
     * 审核意见
     */
    private String opinion;

    /**
     * 审核时间
     */
    private String auditTime;


    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getInfoUsername() {
        return infoUsername;
    }

    public void setInfoUsername(String infoUsername) {
        this.infoUsername = infoUsername;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public Double getLeave() {
        return leave;
    }

    public void setLeave(Double leave) {
        this.leave = leave;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public AuditType getAudit() {
        return audit;
    }

    public void setAudit(AuditType audit) {
        this.audit = audit;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
}