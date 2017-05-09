package com.bjike.goddess.contractcommunicat.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目外包洽谈
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.795 ]
 * @Description: [ 项目外包洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectOutsourcingTO extends BaseTO {

    /**
     * 合同外部项目名称
     */
    @NotBlank(message = "合同外部项目名称不能为空!", groups = {ADD.class, EDIT.class})
    private String contractExtProject;

    /**
     * 合同外部编号
     */
    @NotBlank(message = "合同外部编号不能为空!", groups = {ADD.class, EDIT.class})
    private String contractExtCode;

    /**
     * 内部项目名称
     */
    @NotBlank(message = "内部项目名称不能为空!", groups = {ADD.class, EDIT.class})
    private String contractInProject;

    /**
     * 内部项目编号
     */
    @NotBlank(message = "内部项目编号不能为空!", groups = {ADD.class, EDIT.class})
    private String contractInCode;

    /**
     * 外包项目名称
     */
    @NotBlank(message = "外包项目名称不能为空!", groups = {ADD.class, EDIT.class})
    private String outsourcingProject;

    /**
     * 外包项目编号
     */
    @NotBlank(message = "外包项目编号不能为空!", groups = {ADD.class, EDIT.class})
    private String outsourcingCode;

    /**
     * 项目外包信息
     */
    @NotBlank(message = "项目外包信息不能为空!", groups = {ADD.class, EDIT.class})
    private String outsourcingInfo;

    /**
     * 洽谈轮次
     */
    @NotBlank(message = "洽谈轮次不能为空!", groups = {ADD.class, EDIT.class})
    private String communicateTimes;

    /**
     * 洽谈目的
     */
    @NotBlank(message = "洽谈目的不能为空!", groups = {ADD.class, EDIT.class})
    private String communicateGoal;

    /**
     * 洽谈时间
     */
    @NotBlank(message = "洽谈时间不能为空!", groups = {ADD.class, EDIT.class})
    private String communicateDate;

    /**
     * 洽谈人
     */
    @NotBlank(message = "洽谈人不能为空!", groups = {ADD.class, EDIT.class})
    private String communicateUser;

    /**
     * 洽谈对象
     */
    @NotBlank(message = "洽谈对象不能为空!", groups = {ADD.class, EDIT.class})
    private String communicateObj;

    /**
     * 洽谈地址
     */
    @NotBlank(message = "洽谈地址不能为空!", groups = {ADD.class, EDIT.class})
    private String communicateAddress;

    /**
     * 洽谈内容
     */
    private String communicateContent;

    /**
     * 费用预算
     */
    @NotNull(message = "费用预算不能为空!", groups = {ADD.class, EDIT.class})
    private Double costBudget;

    /**
     * 项目结果
     */
    @NotNull(message = "项目结果不能为空!", groups = {ADD.class, EDIT.class})
    private CommunicateResult projectResult;

    /**
     * 记录人
     */
    @NotBlank(message = "记录人不能为空!", groups = {ADD.class, EDIT.class})
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

    public String getOutsourcingProject() {
        return outsourcingProject;
    }

    public void setOutsourcingProject(String outsourcingProject) {
        this.outsourcingProject = outsourcingProject;
    }

    public String getOutsourcingCode() {
        return outsourcingCode;
    }

    public void setOutsourcingCode(String outsourcingCode) {
        this.outsourcingCode = outsourcingCode;
    }

    public String getOutsourcingInfo() {
        return outsourcingInfo;
    }

    public void setOutsourcingInfo(String outsourcingInfo) {
        this.outsourcingInfo = outsourcingInfo;
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
}