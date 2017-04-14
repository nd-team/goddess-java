package com.bjike.goddess.archive.to;

import com.bjike.goddess.annual.enums.AuditType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 档案调阅
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArchiveAccessTO extends BaseTO {

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空",groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 开始日期
     */
    @NotNull(message = "开始日期不能为空",groups = {ADD.class, EDIT.class})
    private String start;

    /**
     * 结束日期
     */
    @NotNull(message = "结束日期不能为空",groups = {ADD.class, EDIT.class})
    private String end;

    /**
     * 调阅人
     */
    @NotNull(message = "调阅人不能为空",groups = {ADD.class, EDIT.class})
    private String[] accessNames;

    /**
     * 原因
     */
    @NotNull(message = "原因不能为空",groups = {ADD.class, EDIT.class})
    private String reason;

    /**
     * 福利模块
     */
    private String welfare;

    /**
     * 福利审核意见
     */
    private String welfareOpinion;

    /**
     * 总经办
     */
    private String manage;

    /**
     * 总经办审核意见
     */
    private String manageOpinion;

    /**
     * 审核状态
     */
    private AuditType audit;

    /**
     * 是否到期
     */
    private Boolean overdue;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String[] getAccessNames() {
        return accessNames;
    }

    public void setAccessNames(String[] accessNames) {
        this.accessNames = accessNames;
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