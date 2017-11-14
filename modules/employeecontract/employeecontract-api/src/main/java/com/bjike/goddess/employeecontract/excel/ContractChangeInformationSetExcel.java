package com.bjike.goddess.employeecontract.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.employeecontract.enums.ChangeWay;
import com.bjike.goddess.employeecontract.enums.ContractCharacter;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-10 10:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContractChangeInformationSetExcel {
    /**
     * 合同性质
     */
    @ExcelHeader(name = "合同性质",notNull = true)
    private ContractCharacter contractCharacter;

    /**
     * 合同编号
     */
    @ExcelHeader(name = "合同编号",notNull = true)
    private String  ContractNumber;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名",notNull = true)
    private String  name;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号",notNull = true)
    private String  employeeNumber;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String  area;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门",notNull = true)
    private String  department;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String  position;

    /**
     * 入职日期
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String entryDate;

    /**
     * 同意试用期时长
     */
    @ExcelHeader(name = "同意试用期时长",notNull = true)
    private Integer  agreeProbationTime;

    /**
     * 实际试用期时长
     */
    @ExcelHeader(name = "实际试用期时长",notNull = true)
    private Integer  realityProbationTime;

    /**
     * 在职时长
     */
    @ExcelHeader(name = "在职时长",notNull = true)
    private Integer  onJobTime;

    /**
     * 转正时间
     */
    @ExcelHeader(name = "转正时间",notNull = true)
    private String  conversionTime;

    /**
     * 离职日期
     */
    @ExcelHeader(name = "离职日期",notNull = true)
    private String  dimissionDate;

    /**
     * 第一次续签是否需要合同变更
     */
    @ExcelHeader(name = "第一次续签是否需要合同变更",notNull = true)
    private Boolean  ifNeedContractChangeFirst;

    /**
     * 第一次续签变更形式
     */
    @ExcelHeader(name = "第一次续签变更形式",notNull = true)
    private ChangeWay firstChangeWay;

    /**
     * 第一次续签变更内容
     */
    @ExcelHeader(name = "第一次续签变更内容",notNull = true)
    private Boolean  firstChangeContent;

    /**
     * 第一次续签是否纳入统一变更
     */
    @ExcelHeader(name = "第一次续签是否纳入统一变更",notNull = true)
    private Boolean  firstIfBringIntoUnifyChange;

    /**
     * 第一次续签变更原因
     */
    @ExcelHeader(name = "第一次续签变更原因",notNull = true)
    private String  firstChangeReason;

    /**
     * 第一次续签是否已确认变更
     */
    @ExcelHeader(name = "第一次续签是否已确认变更",notNull = true)
    private Boolean  firstIfEnsureChange;

    /**
     * 第一次续签确认变更人
     */
    @ExcelHeader(name = "第一次续签确认变更人",notNull = true)
    private String  firstEnsureChanger;

    /**
     * 第二次续签是否需要合同变更
     */
    @ExcelHeader(name = "第二次续签是否需要合同变更",notNull = true)
    private Boolean  ifNeedContractChangeSecond;

    /**
     * 第二次续签变更形式
     */
    @ExcelHeader(name = "第二次续签变更形式",notNull = true)
    private ChangeWay  secondChangeWay;

    /**
     * 第二次续签变更内容
     */
    @ExcelHeader(name = "第二次续签变更内容",notNull = true)
    private Boolean  secondChangeContent;

    /**
     * 第二次续签是否纳入统一变更
     */
    @ExcelHeader(name = "第二次续签是否纳入统一变更",notNull = true)
    private Boolean  secondIfBringIntoUnifyChange;

    /**
     * 第二次续签变更原因
     */
    @ExcelHeader(name = "第二次续签变更原因",notNull = true)
    private String  secondChangeReason;

    /**
     * 第二次续签是否已确认变更
     */
    @ExcelHeader(name = "第二次续签是否已确认变更",notNull = true)
    private Boolean  secondIfEnsureChange;

    /**
     * 第二次续签确认变更人
     */
    @ExcelHeader(name = "第二次续签确认变更人",notNull = true)
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

    public String getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(String conversionTime) {
        this.conversionTime = conversionTime;
    }

    public String getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(String dimissionDate) {
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
