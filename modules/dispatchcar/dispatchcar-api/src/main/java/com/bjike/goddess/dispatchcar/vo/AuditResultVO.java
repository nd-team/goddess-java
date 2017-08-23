package com.bjike.goddess.dispatchcar.vo;

import java.io.Serializable;

/**
 * @Author: [Jason]
 * @Date: [17-5-6 下午4:33]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AuditResultVO implements Serializable {

    /**
     * 职位
     */
    private String position;

    /**
     * 审核人
     */
    private String auditUser;

    /**
     * 是否通过
     */
    private Boolean auditResult;

    /**
     * 审核时间
     */
    private String auditTime;

    /**
     * 审核意见
     */
    private String suggestion;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public Boolean getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Boolean auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

}
