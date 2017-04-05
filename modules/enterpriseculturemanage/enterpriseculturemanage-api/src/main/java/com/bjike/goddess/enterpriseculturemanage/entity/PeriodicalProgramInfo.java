package com.bjike.goddess.enterpriseculturemanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 刊物方案信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-01 09:07 ]
 * @Description: [ 刊物方案信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "enterpriseculture_periodicalprograminfo")
public class PeriodicalProgramInfo extends BaseEntity {

    /**
     * 方案名称
     */
    @Column(name = "programName", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '方案名称'")
    private String programName;

    /**
     * 刊物主题
     */
    @Column(name = "theme", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '刊物主题'")
    private String theme;

    /**
     * 刊物理念
     */
    @Column(name = "ida", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '刊物理念'")
    private String ida;

    /**
     * 发刊形式
     */
    @Column(name = "way", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发刊形式'")
    private String way;

    /**
     * 发刊规格
     */
    @Column(name = "standard", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发刊规格'")
    private String standard;

    /**
     * 执笔人
     */
    @Column(name = "executer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '执笔人'")
    private String executer;

    /**
     * 刊物名称
     */
    @Column(name = "periodicalName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '刊物名称'")
    private String periodicalName;

    /**
     * 发刊时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATETIME   COMMENT '发刊时间'")
    private LocalDateTime time;

    /**
     * 发刊费用
     */
    @Column(name = "cost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '发刊费用'")
    private Double cost;

    /**
     * 发刊周期
     */
    @Column(name = "cycle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发刊周期'")
    private String cycle;

    /**
     * 审核结果
     */
    @Column(name = "auditResult", columnDefinition = "VARCHAR(255)   COMMENT '审核结果'")
    private AuditResult auditResult;

    /**
     * 审核意见
     */
    @Column(name = "auditSuggestion", columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String auditSuggestion;

    /**
     * 企业文化信息Id
     */
    @Column(name = "infoId", unique = true, nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '企业文化信息Id'")
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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