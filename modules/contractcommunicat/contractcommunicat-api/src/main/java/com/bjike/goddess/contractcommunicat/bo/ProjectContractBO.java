package com.bjike.goddess.contractcommunicat.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;

/**
 * 项目承包洽谈业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.910 ]
 * @Description: [ 项目承包洽谈业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectContractBO extends BaseBO {

    /**
     * 合同外部项目名称
     */
    private String contractExtProject;

    /**
     * 合同外部编号
     */
    private String contractExtCode;

    /**
     * 内部项目名称
     */
    private String contractInProject;

    /**
     * 内部项目编号
     */
    private String contractInCode;

    /**
     * 洽谈轮次
     */
    private String communicateTimes;

    /**
     * 洽谈目的
     */
    private String communicateGoal;

    /**
     * 洽谈时间
     */
    private String communicateDate;

    /**
     * 洽谈人
     */
    private String communicateUser;

    /**
     * 洽谈对象
     */
    private String communicateObj;

    /**
     * 洽谈地址
     */
    private String communicateAddress;

    /**
     * 洽谈内容
     */
    private String communicateContent;

    /**
     * 费用预算
     */
    private Double costBudget;

    /**
     * 项目结果
     */
    private CommunicateResult projectResult;

    /**
     * 记录人
     */
    private String recordUser;


    //汇总返回字段
    private Integer totalCooperate;
    private Integer totalTrail;
    private Integer totalAbandon;

    //汇总返回字段
    private CommunicateResult cooperate;
    private CommunicateResult trail;
    private CommunicateResult abandon;

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

    public String getCommunicateDate() {
        return communicateDate;
    }

    public void setCommunicateDate(String communicateDate) {
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

    public Integer getTotalCooperate() {
        return totalCooperate;
    }

    public void setTotalCooperate(Integer totalCooperate) {
        this.totalCooperate = totalCooperate;
    }

    public Integer getTotalTrail() {
        return totalTrail;
    }

    public void setTotalTrail(Integer totalTrail) {
        this.totalTrail = totalTrail;
    }

    public Integer getTotalAbandon() {
        return totalAbandon;
    }

    public void setTotalAbandon(Integer totalAbandon) {
        this.totalAbandon = totalAbandon;
    }

    public CommunicateResult getCooperate() {
        return cooperate;
    }

    public void setCooperate(CommunicateResult cooperate) {
        this.cooperate = cooperate;
    }

    public CommunicateResult getTrail() {
        return trail;
    }

    public void setTrail(CommunicateResult trail) {
        this.trail = trail;
    }

    public CommunicateResult getAbandon() {
        return abandon;
    }

    public void setAbandon(CommunicateResult abandon) {
        this.abandon = abandon;
    }

    public ProjectContractBO() {

    }

    public ProjectContractBO(String contractExtProject, String contractInProject, String communicateTimes,
                             String communicateUser, String communicateObj, Double costBudget, Integer totalCooperate,
                             Integer totalTrail, Integer totalAbandon) {
        this.contractExtProject = contractExtProject;
        this.contractInProject = contractInProject;
        this.communicateTimes = communicateTimes;
        this.communicateUser = communicateUser;
        this.communicateObj = communicateObj;
        this.costBudget = costBudget;
        this.totalCooperate = totalCooperate;
        this.totalTrail = totalTrail;
        this.totalAbandon = totalAbandon;
    }
}