package com.bjike.goddess.contractcommunicat.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;

/**
 * 项目外包洽谈业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.793 ]
 * @Description: [ 项目外包洽谈业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectOutsourcingCollectBO extends BaseBO {


    /**
     * 外包项目名称
     */
    private String outsourcingProject;

    /**
     * 合同外部项目名称
     */
    private String contractExtProject;

    /**
     * 内部项目名称
     */
    private String contractInProject;

    /**
     * 洽谈轮次
     */
    private String communicateTimes;

    /**
     * 洽谈人
     */
    private String communicateUser;

    /**
     * 洽谈对象
     */
    private String communicateObj;

    /**
     * 费用预算
     */
    private Double costBudget;

    /**
     * 项目和合作
     */
    private String cooperate;
    /**
     * 项目跟进
     */
    private String trail;
    /**
     * 项目丢弃
     */
    private String abandon;

    //项目结果
    private CommunicateResult projectResult;

    public String getContractExtProject() {
        return contractExtProject;
    }

    public void setContractExtProject(String contractExtProject) {
        this.contractExtProject = contractExtProject;
    }

    public String getContractInProject() {
        return contractInProject;
    }

    public void setContractInProject(String contractInProject) {
        this.contractInProject = contractInProject;
    }

    public String getCommunicateTimes() {
        return communicateTimes;
    }

    public void setCommunicateTimes(String communicateTimes) {
        this.communicateTimes = communicateTimes;
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

    public Double getCostBudget() {
        return costBudget;
    }

    public void setCostBudget(Double costBudget) {
        this.costBudget = costBudget;
    }

    public String getCooperate() {
        return cooperate;
    }

    public void setCooperate(String cooperate) {
        this.cooperate = cooperate;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }

    public String getAbandon() {
        return abandon;
    }

    public void setAbandon(String abandon) {
        this.abandon = abandon;
    }

    public CommunicateResult getProjectResult() {
        return projectResult;
    }

    public void setProjectResult(CommunicateResult projectResult) {
        this.projectResult = projectResult;
    }

    public String getOutsourcingProject() {
        return outsourcingProject;
    }

    public void setOutsourcingProject(String outsourcingProject) {
        this.outsourcingProject = outsourcingProject;
    }

    public ProjectOutsourcingCollectBO() {
    }

    public ProjectOutsourcingCollectBO(String contractExtProject, String outsourcingProject,
                                       String contractInProject, String communicateTimes, String communicateUser,
                                       String communicateObj, Double costBudget, String cooperate, String trail,
                                       String abandon) {
        this.contractExtProject = contractExtProject;
        this.outsourcingProject = outsourcingProject;
        this.contractInProject = contractInProject;
        this.communicateTimes = communicateTimes;
        this.communicateUser = communicateUser;
        this.communicateObj = communicateObj;
        this.costBudget = costBudget;
        this.cooperate = cooperate;
        this.trail = trail;
        this.abandon = abandon;
    }
}