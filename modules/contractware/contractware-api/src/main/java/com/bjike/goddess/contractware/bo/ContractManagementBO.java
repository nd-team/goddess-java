package com.bjike.goddess.contractware.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contractware.enums.ContractCharacter;
import com.bjike.goddess.contractware.enums.ContractStatus;

import javax.persistence.Column;
/**
* 科目汇总业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-30 06:13 ]
* @Description:	[ 科目汇总业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ContractManagementBO extends BaseBO {

    /**
     * 新增时间
     */
    private String addTime;

    /**
     * 内部合同编号
     */
    private String  internalContractNumber;

    /**
     * 合同性质
     */
    private ContractCharacter contractCharacter;

    /**
     * 运营商名称
     */
    private String  operatorName;

    /**
     * 地区
     */
    private String  area;

    /**
     * 合作单位
     */
    private String  cooperator;

    /**
     * 类型
     */
    private String  type;

    /**
     * 专业
     */
    private String  major;

    /**
     * 派工单号
     */
    private String  taskNum;

    /**
     * 派工项目名称
     */
    private String  taskProjectName;

    /**
     * 项目内部名称
     */
    private String  innerProject;

    /**
     * 是否有合同
     */
    private Boolean  ifHaveContract;

    /**
     * 合同金额
     */
    private Integer  contractMoney;

    /**
     * 工程奖罚款
     */
    private Integer  engineeringAwardFine;

    /**
     * 状态
     */
    private ContractStatus status;

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getInternalContractNumber() {
        return internalContractNumber;
    }

    public void setInternalContractNumber(String internalContractNumber) {
        this.internalContractNumber = internalContractNumber;
    }

    public ContractCharacter getContractCharacter() {
        return contractCharacter;
    }

    public void setContractCharacter(ContractCharacter contractCharacter) {
        this.contractCharacter = contractCharacter;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCooperator() {
        return cooperator;
    }

    public void setCooperator(String cooperator) {
        this.cooperator = cooperator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getTaskProjectName() {
        return taskProjectName;
    }

    public void setTaskProjectName(String taskProjectName) {
        this.taskProjectName = taskProjectName;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public Boolean getIfHaveContract() {
        return ifHaveContract;
    }

    public void setIfHaveContract(Boolean ifHaveContract) {
        this.ifHaveContract = ifHaveContract;
    }

    public Integer getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Integer contractMoney) {
        this.contractMoney = contractMoney;
    }

    public Integer getEngineeringAwardFine() {
        return engineeringAwardFine;
    }

    public void setEngineeringAwardFine(Integer engineeringAwardFine) {
        this.engineeringAwardFine = engineeringAwardFine;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }
}