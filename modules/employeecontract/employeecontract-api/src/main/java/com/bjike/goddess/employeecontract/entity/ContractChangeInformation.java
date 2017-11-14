package com.bjike.goddess.employeecontract.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.employeecontract.enums.ChangeWay;
import com.bjike.goddess.employeecontract.enums.ContractCharacter;

import javax.persistence.*;
import java.time.LocalDate;


/**
* 合同变更信息
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-09 05:18 ]
* @Description:	[ 合同变更信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "employeecontract_contractchangeinformation")
public class ContractChangeInformation extends BaseEntity {
    /**
     * 合同性质
     */
    @Column(name = "contractCharacter",nullable = false,columnDefinition = "TINYINT(2)  COMMENT '合同性质'"  )
    private ContractCharacter contractCharacter;

    /**
     * 合同编号
     */
    @Column(name = "ContractNumber",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '合同编号'"  )
    private String  ContractNumber;

    /**
     * 姓名
     */
    @Column(name = "name",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '姓名'"  )
    private String  name;

    /**
     * 员工编号
     */
    @Column(name = "employeeNumber",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '员工编号'"  )
    private String  employeeNumber;

    /**
     * 地区
     */
    @Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  )
    private String  area;

    /**
     * 项目组/部门
     */
    @Column(name = "department",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'"  )
    private String  department;

    /**
     * 岗位
     */
    @Column(name = "position",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '岗位'"  )
    private String  position;

    /**
     * 入职日期
     */
    @Column(name = "entryDate",nullable = false,columnDefinition = "DATE   COMMENT '入职日期'"  )
    private LocalDate entryDate;

    /**
     * 同意试用期时长
     */
    @Column(name = "agreeProbationTime",nullable = false,columnDefinition = "INTEGER   COMMENT '同意试用期时长'"  )
    private Integer  agreeProbationTime;

    /**
     * 实际试用期时长
     */
    @Column(name = "realityProbationTime",nullable = false,columnDefinition = "INTEGER   COMMENT '实际试用期时长'"  )
    private Integer  realityProbationTime;

    /**
     * 在职时长
     */
    @Column(name = "onJobTime",nullable = false,columnDefinition = "INTEGER   COMMENT '在职时长'"  )
    private Integer  onJobTime;

    /**
     * 转正时间
     */
    @Column(name = "conversionTime",columnDefinition = "DATE  COMMENT '转正时间'"  )
    private LocalDate  conversionTime;

    /**
     * 离职日期
     */
    @Column(name = "dimissionDate",columnDefinition = "DATE   COMMENT '离职日期'"  )
    private LocalDate  dimissionDate;

    /**
     * 第一次续签是否需要合同变更
     */
    @Column(name = "is_ifNeedContractChangeFirst",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第一次续签是否需要合同变更'"  , insertable = false  )
    private Boolean  ifNeedContractChangeFirst;

    /**
     * 第一次续签变更形式
     */
    @Column(name = "firstChangeWay",columnDefinition = "TINYINT(2)  COMMENT '第一次续签变更形式'"  )
    private ChangeWay firstChangeWay;

    /**
     * 第一次续签变更内容
     */
    @Column(name = "is_firstChangeContent",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第一次续签变更内容'"  , insertable = false  )
    private Boolean  firstChangeContent;

    /**
     * 第一次续签是否纳入统一变更
     */
    @Column(name = "is_firstIfBringIntoUnifyChange",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第一次续签是否纳入统一变更'"  , insertable = false  )
    private Boolean  firstIfBringIntoUnifyChange;

    /**
     * 第一次续签变更原因
     */
    @Column(name = "firstChangeReason",columnDefinition = "VARCHAR(255)   COMMENT '第一次续签变更原因'"  )
    private String  firstChangeReason;

    /**
     * 第一次续签是否已确认变更
     */
    @Column(name = "is_firstIfEnsureChange",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第一次续签是否已确认变更'"  , insertable = false  )
    private Boolean  firstIfEnsureChange;

    /**
     * 第一次续签确认变更人
     */
    @Column(name = "firstEnsureChanger",columnDefinition = "VARCHAR(255) COMMENT '第一次续签确认变更人'"   )
    private String  firstEnsureChanger;

    /**
     * 第二次续签是否需要合同变更
     */
    @Column(name = "is_ifNeedContractChangeSecond",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第二次续签是否需要合同变更'"  , insertable = false  )
    private Boolean  ifNeedContractChangeSecond;

    /**
     * 第二次续签变更形式
     */
    @Column(name = "secondChangeWay",columnDefinition = "TINYINT(2)   COMMENT '第二次续签变更形式'"  )
    private ChangeWay  secondChangeWay;

    /**
     * 第二次续签变更内容
     */
    @Column(name = "is_secondChangeContent",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第二次续签变更内容'"  , insertable = false  )
    private Boolean  secondChangeContent;

    /**
     * 第二次续签是否纳入统一变更
     */
    @Column(name = "is_secondIfBringIntoUnifyChange",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第二次续签是否纳入统一变更'"  , insertable = false  )
    private Boolean  secondIfBringIntoUnifyChange;

    /**
     * 第二次续签变更原因
     */
    @Column(name = "secondChangeReason",columnDefinition = "VARCHAR(255)   COMMENT '第二次续签变更原因'"  )
    private String  secondChangeReason;

    /**
     * 第二次续签是否已确认变更
     */
    @Column(name = "is_secondIfEnsureChange",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第二次续签是否已确认变更'"  , insertable = false  )
    private Boolean  secondIfEnsureChange;

    /**
     * 第二次续签确认变更人
     */
    @Column(name = "secondEnsureChanger",columnDefinition = "VARCHAR(255)  COMMENT '第二次续签确认变更人'"  )
    private String  secondEnsureChanger;

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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
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

    public LocalDate getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(LocalDate conversionTime) {
        this.conversionTime = conversionTime;
    }

    public LocalDate getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(LocalDate dimissionDate) {
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

    public String getFirstEnsureChanger() {
        return firstEnsureChanger;
    }

    public void setFirstEnsureChanger(String firstEnsureChanger) {
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

    public String getSecondEnsureChanger() {
        return secondEnsureChanger;
    }

    public void setSecondEnsureChanger(String secondEnsureChanger) {
        this.secondEnsureChanger = secondEnsureChanger;
    }
}