package com.bjike.goddess.contractware.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.contractware.enums.ContractCharacter;

import javax.persistence.*;


/**
* 科目汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-30 06:13 ]
* @Description:	[ 科目汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "contractware_contractmanagement")
public class ContractManagement extends BaseEntity {
    /**
     * 内部合同编号
     */
    @Column(name = "internalContractNumber",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '内部合同编号'"  )
    private String  internalContractNumber;

    /**
     * 合同性质
     */
    @Column(name = "contractCharacter",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '合同性质'"  )
    private ContractCharacter contractCharacter;

    /**
     * 运营商名称
     */
    @Column(name = "operatorName",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '运营商名称'"  )
    private String  operatorName;

    /**
     * 地区
     */
    @Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  )
    private String  area;

    /**
     * 合作单位
     */
    @Column(name = "cooperator",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '合作单位'"  )
    private String  cooperator;

    /**
     * 类型
     */
    @Column(name = "type",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '类型'"  )
    private String  type;

    /**
     * 专业
     */
    @Column(name = "major",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '专业'"  )
    private String  major;

    /**
     * 派工单号
     */
    @Column(name = "taskNum",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '派工单号'"  )
    private String  taskNum;

    /**
     * 派工项目名称
     */
    @Column(name = "taskProjectName",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '派工项目名称'"  )
    private String  taskProjectName;

    /**
     * 项目内部名称
     */
    @Column(name = "innerProject",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目内部名称'"  )
    private String  innerProject;

    /**
     * 是否有合同
     */
    @Column(name = "is_ifHaveContract",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有合同'"  , insertable = false  )
    private Boolean  ifHaveContract;

    /**
     * 合同金额
     */
    @Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '合同金额'"  )
    private Integer  contractMoney;

    /**
     * 工程奖罚款
     */
    @Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '工程奖罚款'"  )
    private Integer  engineeringAwardFine;

    /**
     * 状态
     */
    @Column(name = "status",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '状态'"  )
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}