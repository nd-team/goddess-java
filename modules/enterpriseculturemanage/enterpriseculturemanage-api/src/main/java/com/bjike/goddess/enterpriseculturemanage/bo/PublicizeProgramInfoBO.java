package com.bjike.goddess.enterpriseculturemanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.enums.PublicizeWay;

/**
 * 宣传方案信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:28 ]
 * @Description: [ 宣传方案信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PublicizeProgramInfoBO extends BaseBO {

    /**
     * 方案名称
     */
    private String name;

    /**
     * 宣传主题
     */
    private String theme;

    /**
     * 宣传形式
     */
    private PublicizeWay way;

    /**
     * 执行人
     */
    private String executer;

    /**
     * 执行周期
     */
    private String executeCycle;

    /**
     * 执行费用
     */
    private Double executeCost;

    /**
     * 审核结果
     */
    private AuditResult auditResult;

    /**
     * 审核意见
     */
    private String auditSuggestion;

    /**
     * 企业文化信息Id
     */
    private String infoId;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public PublicizeWay getWay() {
        return way;
    }

    public void setWay(PublicizeWay way) {
        this.way = way;
    }

    public String getExecuter() {
        return executer;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }

    public String getExecuteCycle() {
        return executeCycle;
    }

    public void setExecuteCycle(String executeCycle) {
        this.executeCycle = executeCycle;
    }

    public Double getExecuteCost() {
        return executeCost;
    }

    public void setExecuteCost(Double executeCost) {
        this.executeCost = executeCost;
    }

    public AuditResult getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(AuditResult auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditSuggestion() {
        return auditSuggestion;
    }

    public void setAuditSuggestion(String auditSuggestion) {
        this.auditSuggestion = auditSuggestion;
    }
}