package com.bjike.goddess.annual.entity;

import com.bjike.goddess.annual.enums.AuditType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 年假申请
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "annual_annual_apply")
public class AnnualApply extends BaseEntity {

    /**
     * 年假信息
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "info_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '年假信息'")
    private AnnualInfo info;

    /**
     * 开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME   COMMENT '开始时间'")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME   COMMENT '结束时间'")
    private LocalDateTime endTime;

    /**
     * 请假时间
     */
    @Column(name = "applyTime", nullable = false, columnDefinition = "DATETIME   COMMENT '请假时间'")
    private LocalDateTime applyTime;

    /**
     * 请假天数
     */
    @Column(name = "leaveDays", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '请假天数'")
    private Double leave;

    /**
     * 审核人
     */
    @Column(name = "auditor", columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String auditor;

    /**
     * 审核结果
     */
    @Column(name = "audit", columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '审核结果'")
    private AuditType audit;

    /**
     * 审核意见
     */
    @Column(name = "opinion", columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String opinion;

    /**
     * 审核时间
     */
    @Column(name = "auditTime", columnDefinition = "DATETIME   COMMENT '审核时间'")
    private LocalDateTime auditTime;


    public AnnualInfo getInfo() {
        return info;
    }

    public void setInfo(AnnualInfo info) {
        this.info = info;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
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

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }
}