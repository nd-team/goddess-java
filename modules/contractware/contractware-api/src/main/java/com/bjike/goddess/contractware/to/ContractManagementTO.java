package com.bjike.goddess.contractware.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
* 科目汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-30 06:13 ]
* @Description:	[ 科目汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ContractManagementTO extends BaseTO {
    /**
     * 内部合同编号
     */
    @NotBlank(message = "内部合同编号不能为空",groups = {ADD.class, EDIT.class})
    private String  internalContractNumber;

    /**
     * 合同性质
     */
    @NotBlank(message = "合同性质不能为空",groups = {ADD.class, EDIT.class})
    private String  contractCharacter;

    /**
     * 运营商名称
     */
    @NotBlank(message = "运营商名称不能为空",groups = {ADD.class, EDIT.class})
    private String  operatorName;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String  area;

    /**
     * 合作单位
     */
    @NotBlank(message = "合作单位不能为空",groups = {ADD.class, EDIT.class})
    private String  cooperator;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空",groups = {ADD.class, EDIT.class})
    private String  type;

    /**
     * 专业
     */
    @NotBlank(message = "专业不能为空",groups = {ADD.class, EDIT.class})
    private String  major;

    /**
     * 派工单号
     */
    @NotBlank(message = "派工单号不能为空",groups = {ADD.class, EDIT.class})
    private String  taskNum;

    /**
     * 派工项目名称
     */
    @NotBlank(message = "派工项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String  taskProjectName;

    /**
     * 项目内部名称
     */
    @NotBlank(message = "项目内部名称不能为空",groups = {ADD.class, EDIT.class})
    private String  innerProject;


    /**
     * 合同金额
     */
    @NotNull(message = "合同金额不能为空",groups = {ADD.class, EDIT.class})
    private Integer  contractMoney;

    /**
     * 工程奖罚款
     */
    @NotNull(message = "工程奖罚款不能为空",groups = {ADD.class, EDIT.class})
    private Integer  engineeringAwardFine;

    public String getInternalContractNumber() {
        return internalContractNumber;
    }

    public void setInternalContractNumber(String internalContractNumber) {
        this.internalContractNumber = internalContractNumber;
    }

    public String getContractCharacter() {
        return contractCharacter;
    }

    public void setContractCharacter(String contractCharacter) {
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

}