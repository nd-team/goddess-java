package com.bjike.goddess.contractcommunicat.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 项目承包洽谈
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.907 ]
 * @Description: [ 项目承包洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contractcommunicate_projectcontract")
public class ProjectContract extends BaseEntity {

    /**
     * 合同外部项目名称
     */
    @Column(name = "contractExtProject", nullable = false,  columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String contractExtProject;

    /**
     * 合同外部编号
     */
    @Column(name = "contractExtCode", nullable = false,  columnDefinition = "VARCHAR(255)   COMMENT '合同外部编号'")
    private String contractExtCode;

    /**
     * 内部项目名称
     */
    @Column(name = "contractInProject", nullable = false,  columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String contractInProject;

    /**
     * 内部项目编号
     */
    @Column(name = "contractInCode", nullable = false,  columnDefinition = "VARCHAR(255)   COMMENT '内部项目编号'")
    private String contractInCode;

    /**
     * 洽谈轮次
     */
    @Column(name = "communicateTimes", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈轮次'")
    private String communicateTimes;

    /**
     * 洽谈目的
     */
    @Column(name = "communicateGoal", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈目的'")
    private String communicateGoal;

    /**
     * 洽谈时间
     */
    @Column(name = "communicateDate", nullable = false, columnDefinition = "DATETIME   COMMENT '洽谈时间'")
    private LocalDateTime communicateDate;

    /**
     * 洽谈人
     */
    @Column(name = "communicateUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈人'")
    private String communicateUser;

    /**
     * 洽谈对象
     */
    @Column(name = "communicateObj", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈对象'")
    private String communicateObj;

    /**
     * 洽谈地址
     */
    @Column(name = "communicateAddress", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈地址'")
    private String communicateAddress;

    /**
     * 洽谈内容
     */
    @Column(name = "communicateContent", columnDefinition = "text   COMMENT '洽谈内容'")
    private String communicateContent;

    /**
     * 费用预算
     */
    @Column(name = "costBudget", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '费用预算'")
    private Double costBudget;

    /**
     * 项目结果
     */
    @Column(name = "projectResult", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '项目结果'")
    private CommunicateResult projectResult;

    /**
     * 记录人
     */
    @Column(name = "recordUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '记录人'")
    private String recordUser;


    public String getContractExtProject() {
        return contractExtProject;
    }

    public void setContractExtProject(String contractExtProject) {
        this.contractExtProject = contractExtProject;
    }

    public String getContractExtCode() {
        return contractExtCode;
    }

    public void setContractExtCode(String contractExtCode) {
        this.contractExtCode = contractExtCode;
    }

    public String getContractInProject() {
        return contractInProject;
    }

    public void setContractInProject(String contractInProject) {
        this.contractInProject = contractInProject;
    }

    public String getContractInCode() {
        return contractInCode;
    }

    public void setContractInCode(String contractInCode) {
        this.contractInCode = contractInCode;
    }

    public String getCommunicateTimes() {
        return communicateTimes;
    }

    public void setCommunicateTimes(String communicateTimes) {
        this.communicateTimes = communicateTimes;
    }

    public String getCommunicateGoal() {
        return communicateGoal;
    }

    public void setCommunicateGoal(String communicateGoal) {
        this.communicateGoal = communicateGoal;
    }

    public LocalDateTime getCommunicateDate() {
        return communicateDate;
    }

    public void setCommunicateDate(LocalDateTime communicateDate) {
        this.communicateDate = communicateDate;
    }

    public String getCommunicateUser() {
        return communicateUser;
    }

    public void setCommunicateUser(String communicateUser) {
        this.communicateUser = communicateUser;
    }

    public String getCommunicateObj() {
        return communicateObj;
    }

    public void setCommunicateObj(String communicateObj) {
        this.communicateObj = communicateObj;
    }

    public String getCommunicateAddress() {
        return communicateAddress;
    }

    public void setCommunicateAddress(String communicateAddress) {
        this.communicateAddress = communicateAddress;
    }

    public String getCommunicateContent() {
        return communicateContent;
    }

    public void setCommunicateContent(String communicateContent) {
        this.communicateContent = communicateContent;
    }

    public Double getCostBudget() {
        return costBudget;
    }

    public void setCostBudget(Double costBudget) {
        this.costBudget = costBudget;
    }

    public CommunicateResult getProjectResult() {
        return projectResult;
    }

    public void setProjectResult(CommunicateResult projectResult) {
        this.projectResult = projectResult;
    }

    public String getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }
}