package com.bjike.goddess.enterpriseculturemanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;

/**
 * 刊物方案信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-01 09:07 ]
 * @Description: [ 刊物方案信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PeriodicalProgramInfoBO extends BaseBO {

    /**
     * 方案名称
     */
    private String programName;

    /**
     * 刊物主题
     */
    private String theme;

    /**
     * 刊物理念
     */
    private String ida;

    /**
     * 发刊形式
     */
    private String way;

    /**
     * 发刊规格
     */
    private String standard;

    /**
     * 执笔人
     */
    private String executer;

    /**
     * 刊物名称
     */
    private String periodicalName;

    /**
     * 发刊时间
     */
    private String time;

    /**
     * 发刊费用
     */
    private Double cost;

    /**
     * 发刊周期
     */
    private String cycle;

    /**
     * 审核结果
     */
    private AuditResult auditResult;

    /**
     * 审核意见
     */
    private String auditSuggestion;

    /**
     * 企业文化信息id
     */
    private String infoId;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getIda() {
        return ida;
    }

    public void setIda(String ida) {
        this.ida = ida;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getExecuter() {
        return executer;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }

    public String getPeriodicalName() {
        return periodicalName;
    }

    public void setPeriodicalName(String periodicalName) {
        this.periodicalName = periodicalName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
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

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }
}