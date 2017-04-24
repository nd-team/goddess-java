package com.bjike.goddess.annual.to;

import com.bjike.goddess.annual.enums.AuditType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 年假申请
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualApplyTO extends BaseTO {
    /**
     * 年假信息
     */
    @NotNull(message = "年假信息id不能为空", groups = {EDIT.class, ADD.class})
    private String infoId;

    /**
     * 请假人
     */
    private String infoUsername;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = {EDIT.class, ADD.class})
    private String startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = {EDIT.class, ADD.class})
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

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
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

}