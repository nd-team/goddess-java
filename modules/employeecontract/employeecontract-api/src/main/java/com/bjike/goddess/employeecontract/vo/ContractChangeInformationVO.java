package com.bjike.goddess.employeecontract.vo;

import com.bjike.goddess.employeecontract.enums.ChangeWay;
import com.bjike.goddess.employeecontract.enums.ContractCharacter;

/**
* 合同变更信息表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-09 05:18 ]
* @Description:	[ 合同变更信息表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ContractChangeInformationVO {
    /**
     * id
     */
    private String  id;
    /**
     * 合同性质
     */
    private ContractCharacter contractCharacter;

    /**
     * 合同编号
     */
    private String  ContractNumber;

    /**
     * 姓名
     */
    private String  name;

    /**
     * 员工编号
     */
    private String  employeeNumber;

    /**
     * 地区
     */
    private String  area;

    /**
     * 项目组/部门
     */
    private String  department;

    /**
     * 岗位
     */
    private String  position;

    /**
     * 入职日期
     */
    private String  entryDate;

    /**
     * 同意试用期时长
     */
    private Integer  agreeProbationTime;

    /**
     * 实际试用期时长
     */
    private Integer  realityProbationTime;

    /**
     * 在职时长
     */
    private Integer  onJobTime;

    /**
     * 转正时间
     */
    private Integer  conversionTime;

    /**
     * 离职日期
     */
    private Integer  dimissionDate;

    /**
     * 第一次续签是否需要合同变更
     */
    private Boolean  ifNeedContractChangeFirst;

    /**
     * 第一次续签变更形式
     */
    private ChangeWay firstChangeWay;

    /**
     * 第一次续签变更内容
     */
    private Boolean  firstChangeContent;

    /**
     * 第一次续签是否纳入统一变更
     */
    private Boolean  firstIfBringIntoUnifyChange;

    /**
     * 第一次续签变更原因
     */
    private String  firstChangeReason;

    /**
     * 第一次续签是否已确认变更
     */
    private Boolean  firstIfEnsureChange;

    /**
     * 第一次续签确认变更人
     */
    private Boolean  firstEnsureChanger;

    /**
     * 第二次续签是否需要合同变更
     */
    private Boolean  ifNeedContractChangeSecond;

    /**
     * 第二次续签变更形式
     */
    private ChangeWay  secondChangeWay;

    /**
     * 第二次续签变更内容
     */
    private Boolean  secondChangeContent;

    /**
     * 第二次续签是否纳入统一变更
     */
    private Boolean  secondIfBringIntoUnifyChange;

    /**
     * 第二次续签变更原因
     */
    private String  secondChangeReason;

    /**
     * 第二次续签是否已确认变更
     */
    private Boolean  secondIfEnsureChange;

    /**
     * 第二次续签确认变更人
     */
    private Boolean  secondEnsureChanger;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ContractCharacter getContractCharacter() {
        return contractCharacter;
    }

    public void setContractCharacter(ContractCharacter contractCharacter) {
        this.contractCharacter = contractCharacter;
    }

    public String getContractNumber() {
        return ContractNumber;
    }

    public void setContractNumber(String contractNumber) {
        ContractNumber = contractNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getAgreeProbationTime() {
        return agreeProbationTime;
    }

    public void setAgreeProbationTime(Integer agreeProbationTime) {
        this.agreeProbationTime = agreeProbationTime;
    }

    public Integer getRealityProbationTime() {
        return realityProbationTime;
    }

    public void setRealityProbationTime(Integer realityProbationTime) {
        this.realityProbationTime = realityProbationTime;
    }

    public Integer getOnJobTime() {
        return onJobTime;
    }

    public void setOnJobTime(Integer onJobTime) {
        this.onJobTime = onJobTime;
    }

    public Integer getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(Integer conversionTime) {
        this.conversionTime = conversionTime;
    }

    public Integer getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(Integer dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public Boolean getIfNeedContractChangeFirst() {
        return ifNeedContractChangeFirst;
    }

    public void setIfNeedContractChangeFirst(Boolean ifNeedContractChangeFirst) {
        this.ifNeedContractChangeFirst = ifNeedContractChangeFirst;
    }

    public ChangeWay getFirstChangeWay() {
        return firstChangeWay;
    }

    public void setFirstChangeWay(ChangeWay firstChangeWay) {
        this.firstChangeWay = firstChangeWay;
    }

    public Boolean getFirstChangeContent() {
        return firstChangeContent;
    }

    public void setFirstChangeContent(Boolean firstChangeContent) {
        this.firstChangeContent = firstChangeContent;
    }

    public Boolean getFirstIfBringIntoUnifyChange() {
        return firstIfBringIntoUnifyChange;
    }

    public void setFirstIfBringIntoUnifyChange(Boolean firstIfBringIntoUnifyChange) {
        this.firstIfBringIntoUnifyChange = firstIfBringIntoUnifyChange;
    }

    public String getFirstChangeReason() {
        return firstChangeReason;
    }

    public void setFirstChangeReason(String firstChangeReason) {
        this.firstChangeReason = firstChangeReason;
    }

    public Boolean getFirstIfEnsureChange() {
        return firstIfEnsureChange;
    }

    public void setFirstIfEnsureChange(Boolean firstIfEnsureChange) {
        this.firstIfEnsureChange = firstIfEnsureChange;
    }

    public Boolean getFirstEnsureChanger() {
        return firstEnsureChanger;
    }

    public void setFirstEnsureChanger(Boolean firstEnsureChanger) {
        this.firstEnsureChanger = firstEnsureChanger;
    }

    public Boolean getIfNeedContractChangeSecond() {
        return ifNeedContractChangeSecond;
    }

    public void setIfNeedContractChangeSecond(Boolean ifNeedContractChangeSecond) {
        this.ifNeedContractChangeSecond = ifNeedContractChangeSecond;
    }

    public ChangeWay getSecondChangeWay() {
        return secondChangeWay;
    }

    public void setSecondChangeWay(ChangeWay secondChangeWay) {
        this.secondChangeWay = secondChangeWay;
    }

    public Boolean getSecondChangeContent() {
        return secondChangeContent;
    }

    public void setSecondChangeContent(Boolean secondChangeContent) {
        this.secondChangeContent = secondChangeContent;
    }

    public Boolean getSecondIfBringIntoUnifyChange() {
        return secondIfBringIntoUnifyChange;
    }

    public void setSecondIfBringIntoUnifyChange(Boolean secondIfBringIntoUnifyChange) {
        this.secondIfBringIntoUnifyChange = secondIfBringIntoUnifyChange;
    }

    public String getSecondChangeReason() {
        return secondChangeReason;
    }

    public void setSecondChangeReason(String secondChangeReason) {
        this.secondChangeReason = secondChangeReason;
    }

    public Boolean getSecondIfEnsureChange() {
        return secondIfEnsureChange;
    }

    public void setSecondIfEnsureChange(Boolean secondIfEnsureChange) {
        this.secondIfEnsureChange = secondIfEnsureChange;
    }

    public Boolean getSecondEnsureChanger() {
        return secondEnsureChanger;
    }

    public void setSecondEnsureChanger(Boolean secondEnsureChanger) {
        this.secondEnsureChanger = secondEnsureChanger;
    }
}