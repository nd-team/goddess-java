package com.bjike.goddess.enterpriseculturemanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.enums.PublicizeWay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 宣传方案信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:28 ]
 * @Description: [ 宣传方案信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "enterpriseculture_publicizeprograminfo")
public class PublicizeProgramInfo extends BaseEntity {

    /**
     * 方案名称
     */
    @Column(name = "name", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '方案名称'")
    private String name;

    /**
     * 宣传主题
     */
    @Column(name = "theme", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '宣传主题'")
    private String theme;

    /**
     * 宣传形式
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '宣传形式'")
    private PublicizeWay way;

    /**
     * 执行人
     */
    @Column(name = "executer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '执行人'")
    private String executer;

    /**
     * 执行周期
     */
    @Column(name = "executeCycle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '执行周期'")
    private String executeCycle;

    /**
     * 执行费用
     */
    @Column(name = "executeCost", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '执行费用'")
    private String executeCost;

    /**
     * 审核结果
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '审核结果'")
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

    public String getExecuteCost() {
        return executeCost;
    }

    public void setExecuteCost(String executeCost) {
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

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }
}