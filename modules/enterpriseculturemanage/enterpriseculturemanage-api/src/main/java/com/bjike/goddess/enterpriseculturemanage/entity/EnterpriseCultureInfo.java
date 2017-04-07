package com.bjike.goddess.enterpriseculturemanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 企业文化信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:05 ]
 * @Description: [ 企业文化信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "enterpriseculture_info")
public class EnterpriseCultureInfo extends BaseEntity {

    /**
     * 主题
     */
    @Column(name = "theme", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '主题'")
    private String theme;

    /**
     * 执行阶段
     */
    @Column(name = "executeStage", columnDefinition = "VARCHAR(255)   COMMENT '执行阶段'")
    private String executeStage;

    /**
     * 执行问题
     */
    @Column(name = "executeQuestion", columnDefinition = "TEXT   COMMENT '执行问题'")
    private String executeQuestion;

    /**
     * 解决方案
     */
    @Column(name = "solution", columnDefinition = "TEXT   COMMENT '解决方案'")
    private String solution;

    /**
     * 员工意见
     */
    @Column(name = "employeeSuggest", columnDefinition = "TEXT   COMMENT '员工意见'")
    private String employeeSuggest;

    /**
     * 宣传方案Id
     */
    @Column(name = "publicizeId", columnDefinition = "VARCHAR(36)   COMMENT '宣传方案Id'")
    private String publicizeId;

    /**
     * 刊物方案Id
     */
    @Column(name = "periodicalId", columnDefinition = "VARCHAR(36)   COMMENT '刊物方案Id'")
    private String periodicalId;

    /**
     * 数据状态
     */
    @Column(name = "status", columnDefinition = "TINYINT(2)   COMMENT '数据状态'")
    private Status status;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getExecuteStage() {
        return executeStage;
    }

    public void setExecuteStage(String executeStage) {
        this.executeStage = executeStage;
    }

    public String getExecuteQuestion() {
        return executeQuestion;
    }

    public void setExecuteQuestion(String executeQuestion) {
        this.executeQuestion = executeQuestion;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getEmployeeSuggest() {
        return employeeSuggest;
    }

    public void setEmployeeSuggest(String employeeSuggest) {
        this.employeeSuggest = employeeSuggest;
    }

    public String getPublicizeId() {
        return publicizeId;
    }

    public void setPublicizeId(String publicizeId) {
        this.publicizeId = publicizeId;
    }

    public String getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(String periodicalId) {
        this.periodicalId = periodicalId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}