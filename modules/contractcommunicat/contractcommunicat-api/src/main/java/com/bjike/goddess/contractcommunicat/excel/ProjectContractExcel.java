package com.bjike.goddess.contractcommunicat.excel;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 项目承包洽谈
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.913 ]
 * @Description: [ 项目承包洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectContractExcel extends BaseTO {

    /**
     * 合同外部项目名称
     */
    @ExcelHeader(name = "合同外部项目名称",notNull = true)
    private String contractExtProject;

    /**
     * 合同外部编号
     */
    @ExcelHeader(name = "合同外部编号",notNull = true)
    private String contractExtCode;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称",notNull = true)
    private String contractInProject;

    /**
     * 内部项目编号
     */
    @ExcelHeader(name = "内部项目编号",notNull = true)
    private String contractInCode;

    /**
     * 洽谈轮次
     */
    @ExcelHeader(name = "洽谈轮次",notNull = true)
    private String communicateTimes;

    /**
     * 洽谈目的
     */
    @ExcelHeader(name = "洽谈目的",notNull = true)
    private String communicateGoal;

    /**
     * 洽谈时间
     */
    @ExcelHeader(name = "洽谈时间",notNull = true)
    private LocalDateTime communicateDate;

    /**
     * 洽谈人
     */
    @ExcelHeader(name = "洽谈人",notNull = true)
    private String communicateUser;

    /**
     * 洽谈对象
     */
    @ExcelHeader(name = "洽谈对象",notNull = true)
    private String communicateObj;

    /**
     * 洽谈地址
     */
    @ExcelHeader(name = "洽谈地址",notNull = true)
    private String communicateAddress;

    /**
     * 洽谈内容
     */
    @ExcelHeader(name = "洽谈内容",notNull = true)
    private String communicateContent;

    /**
     * 费用预算
     */
    @ExcelHeader(name = "费用预算",notNull = true)
    private Double costBudget;

    /**
     * 项目结果
     */
    @ExcelHeader(name = "项目结果",notNull = true)
    private CommunicateResult projectResult;

    /**
     * 记录人
     */
    @ExcelHeader(name = "记录人",notNull = true)
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