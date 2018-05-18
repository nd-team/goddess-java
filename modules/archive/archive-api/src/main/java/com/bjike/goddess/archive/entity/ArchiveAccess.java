package com.bjike.goddess.archive.entity;

import com.bjike.goddess.archive.enums.AuditType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 档案调阅
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "archive_access")
public class ArchiveAccess extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 开始日期
     */
    @Column(name = "start", nullable = false, columnDefinition = "DATE   COMMENT '开始日期'")
    private LocalDate start;

    /**
     * 结束日期
     */
    @Column(name = "end", nullable = false, columnDefinition = "DATE   COMMENT '结束日期'")
    private LocalDate end;

    /**
     * 调阅人
     */
    @Column(name = "access", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调阅人'")
    private String access;

    /**
     * 原因
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '原因'")
    private String reason;

    /**
     * 福利模块
     */
    @Column(name = "welfare", columnDefinition = "VARCHAR(255)   COMMENT '福利模块'")
    private String welfare;

    /**
     * 福利审核意见
     */
    @Column(name = "welfareOpinion", columnDefinition = "VARCHAR(255)   COMMENT '福利审核意见'")
    private String welfareOpinion;

    /**
     * 总经办
     */
    @Column(name = "manage", columnDefinition = "VARCHAR(255)   COMMENT '总经办'")
    private String manage;

    /**
     * 总经办审核意见
     */
    @Column(name = "manageOpinion", columnDefinition = "VARCHAR(255)   COMMENT '总经办审核意见'")
    private String manageOpinion;

    /**
     * 审核状态
     */
    @Column(name = "audit", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '审核状态'", insertable = false)
    private AuditType audit;

    /**
     * 是否到期
     */
    @Column(name = "is_overdue", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否到期'", insertable = false)
    private Boolean overdue;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getWelfareOpinion() {
        return welfareOpinion;
    }

    public void setWelfareOpinion(String welfareOpinion) {
        this.welfareOpinion = welfareOpinion;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getManageOpinion() {
        return manageOpinion;
    }

    public void setManageOpinion(String manageOpinion) {
        this.manageOpinion = manageOpinion;
    }

    public AuditType getAudit() {
        return audit;
    }

    public void setAudit(AuditType audit) {
        this.audit = audit;
    }

    public Boolean getOverdue() {
        return overdue;
    }

    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }
}