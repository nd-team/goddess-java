package com.bjike.goddess.employeecontract.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.employeecontract.enums.ContractCharacter;
import com.bjike.goddess.employeecontract.enums.ContractStatus;

import javax.persistence.Column;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-08 17:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContractInformationSetExcel {
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
    @ExcelHeader(name = "入职日期",notNull = true)
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
    private Integer realityProbationTime;

    /**
     * 在职时长
     */
    @ExcelHeader(name = "在职时长",notNull = true)
    private Integer onJobTime;

    /**
     * 转正时间
     */
    @ExcelHeader(name = "转正时间",notNull = true)
    private String conversionTime;

    /**
     * 离职日期
     */
    @ExcelHeader(name = "离职日期",notNull = true)
    private String dimissionDate;

    /**
     * 应签订劳动合同时间
     */
    @ExcelHeader(name = "应签订劳动合同时间",notNull = true)
    private String  shouldSignContractDate;

    /**
     * 是否签订劳动合同
     */
    @ExcelHeader(name = "是否签订劳动合同",notNull = true)
    private Boolean  ifSignContract;

    /**
     * 签订所属公司
     */
    @ExcelHeader(name = "签订所属公司",notNull = true)
    private String signBelongCompany;

    /**
     * 签订劳动合同开始日期
     */
    @ExcelHeader(name = "签订劳动合同开始日期",notNull = true)
    private String signContractStartDate;

    /**
     * 签订劳动合同到期日期
     */
    @ExcelHeader(name = "签订劳动合同到期日期",notNull = true)
    private String signContractEndDate;

    /**
     * 合同期限
     */
    @ExcelHeader(name = "合同期限",notNull = true)
    private Integer contractDeadline;

    /**
     * 是否续签
     */
    @ExcelHeader(name = "是否续签",notNull = true)
    private Boolean  ifRenew;

    /**
     * 第一次续签是否需要合同变更
     */
    @ExcelHeader(name = "第一次续签是否需要合同变更",notNull = true)
    private Boolean  ifNeedContractChangeFirst;

    /**
     * 第一次续签开始日期
     */
    @ExcelHeader(name = "第一次续签开始日期",notNull = true)
    private String  firstRenewStartDate;

    /**
     * 第一次续签到期日期
     */
    @ExcelHeader(name = "第一次续签到期日期",notNull = true)
    private String  firstRenewEndDate;

    /**
     * 第一次续签合同期限
     */
    @ExcelHeader(name = "第一次续签合同期限",notNull = true)
    private Integer firstReneweContractDeadline;

    /**
     * 是否第二次续签
     */
    @ExcelHeader(name = "是否第二次续签",notNull = true)
    private Boolean  ifSecondRenew;

    /**
     * 第二次续签是否需要合同变更
     */
    @ExcelHeader(name = "第二次续签是否需要合同变更",notNull = true)
    private Boolean  ifNeedContractChangeSecond;

    /**
     * 第二次续签开始日期
     */
    @ExcelHeader(name = "第二次续签开始日期",notNull = true)
    private String  secondRenewStartDate;

    /**
     * 第二次续签到期日期
     */
    @ExcelHeader(name = "第二次续签到期日期",notNull = true)
    private String  secondRenewEndDate;

    /**
     * 是否解除合同
     */
    @ExcelHeader(name = "是否解除合同",notNull = true)
    private Boolean  ifRelieveContract;

    /**
     * 解除合同时间
     */
    @ExcelHeader(name = "解除合同时间",notNull = true)
    private String relieveContractDate;

    /**
     * 是否有附件
     */
    @ExcelHeader(name = "是否有附件",notNull = true)
    private Boolean  ifAttachment;

    /**
     * 原件存储位置
     */
    @ExcelHeader(name = "原件存储位置",notNull = true)
    private String  attachmentStorageLocation;

    /**
     * 合同状态
     */
    @ExcelHeader(name = "合同状态",notNull = true)
    private ContractStatus contractStatus;

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

    public String getShouldSignContractDate() {
        return shouldSignContractDate;
    }

    public void setShouldSignContractDate(String shouldSignContractDate) {
        this.shouldSignContractDate = shouldSignContractDate;
    }

    public Boolean getIfSignContract() {
        return ifSignContract;
    }

    public void setIfSignContract(Boolean ifSignContract) {
        this.ifSignContract = ifSignContract;
    }

    public String getSignBelongCompany() {
        return signBelongCompany;
    }

    public void setSignBelongCompany(String signBelongCompany) {
        this.signBelongCompany = signBelongCompany;
    }

    public String getSignContractStartDate() {
        return signContractStartDate;
    }

    public void setSignContractStartDate(String signContractStartDate) {
        this.signContractStartDate = signContractStartDate;
    }

    public String getSignContractEndDate() {
        return signContractEndDate;
    }

    public void setSignContractEndDate(String signContractEndDate) {
        this.signContractEndDate = signContractEndDate;
    }

    public Integer getContractDeadline() {
        return contractDeadline;
    }

    public void setContractDeadline(Integer contractDeadline) {
        this.contractDeadline = contractDeadline;
    }

    public Boolean getIfRenew() {
        return ifRenew;
    }

    public void setIfRenew(Boolean ifRenew) {
        this.ifRenew = ifRenew;
    }

    public Boolean getIfNeedContractChangeFirst() {
        return ifNeedContractChangeFirst;
    }

    public void setIfNeedContractChangeFirst(Boolean ifNeedContractChangeFirst) {
        this.ifNeedContractChangeFirst = ifNeedContractChangeFirst;
    }

    public String getFirstRenewStartDate() {
        return firstRenewStartDate;
    }

    public void setFirstRenewStartDate(String firstRenewStartDate) {
        this.firstRenewStartDate = firstRenewStartDate;
    }

    public String getFirstRenewEndDate() {
        return firstRenewEndDate;
    }

    public void setFirstRenewEndDate(String firstRenewEndDate) {
        this.firstRenewEndDate = firstRenewEndDate;
    }

    public Integer getFirstReneweContractDeadline() {
        return firstReneweContractDeadline;
    }

    public void setFirstReneweContractDeadline(Integer firstReneweContractDeadline) {
        this.firstReneweContractDeadline = firstReneweContractDeadline;
    }

    public Boolean getIfSecondRenew() {
        return ifSecondRenew;
    }

    public void setIfSecondRenew(Boolean ifSecondRenew) {
        this.ifSecondRenew = ifSecondRenew;
    }

    public Boolean getIfNeedContractChangeSecond() {
        return ifNeedContractChangeSecond;
    }

    public void setIfNeedContractChangeSecond(Boolean ifNeedContractChangeSecond) {
        this.ifNeedContractChangeSecond = ifNeedContractChangeSecond;
    }

    public String getSecondRenewStartDate() {
        return secondRenewStartDate;
    }

    public void setSecondRenewStartDate(String secondRenewStartDate) {
        this.secondRenewStartDate = secondRenewStartDate;
    }

    public String getSecondRenewEndDate() {
        return secondRenewEndDate;
    }

    public void setSecondRenewEndDate(String secondRenewEndDate) {
        this.secondRenewEndDate = secondRenewEndDate;
    }

    public Boolean getIfRelieveContract() {
        return ifRelieveContract;
    }

    public void setIfRelieveContract(Boolean ifRelieveContract) {
        this.ifRelieveContract = ifRelieveContract;
    }

    public String getRelieveContractDate() {
        return relieveContractDate;
    }

    public void setRelieveContractDate(String relieveContractDate) {
        this.relieveContractDate = relieveContractDate;
    }

    public Boolean getIfAttachment() {
        return ifAttachment;
    }

    public void setIfAttachment(Boolean ifAttachment) {
        this.ifAttachment = ifAttachment;
    }

    public String getAttachmentStorageLocation() {
        return attachmentStorageLocation;
    }

    public void setAttachmentStorageLocation(String attachmentStorageLocation) {
        this.attachmentStorageLocation = attachmentStorageLocation;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }
}
