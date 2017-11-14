package com.bjike.goddess.employeecontract.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.employeecontract.enums.AgreeProbationTime;
import com.bjike.goddess.employeecontract.enums.ContractCharacter;
import com.bjike.goddess.employeecontract.enums.ContractStatus;

import javax.persistence.*;
import java.time.LocalDate;


/**
* 员工合同信息
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-08 10:56 ]
* @Description:	[ 员工合同信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "employeecontract_contractinformation")
public class ContractInformation extends BaseEntity {
    /**
     * 合同性质
     */
    @Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '合同性质'"  )
    private ContractCharacter  contractCharacter;

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
    private LocalDate  entryDate;

    /**
     * 同意试用期时长
     */
    @Column(name = "agreeProbationTime",nullable = false,columnDefinition = "TINYINT(5)   COMMENT '同意试用期时长'"  )
    private AgreeProbationTime agreeProbationTime;

    /**
     * 实际试用期时长
     */
    @Column(name = "realityProbationTime",nullable = false,columnDefinition = "INT   COMMENT '实际试用期时长'"  )
    private Integer realityProbationTime;

    /**
     * 在职时长
     */
    @Column(name = "onJobTime",nullable = false,columnDefinition = "INT  COMMENT '在职时长'"  )
    private Integer onJobTime;

    /**
     * 转正时间
     */
    @Column(name = "conversionTime",nullable = false,columnDefinition = "DATE  COMMENT '转正时间'"  )
    private LocalDate conversionTime;

    /**
     * 离职日期
     */
    @Column(name = "dimissionDate",nullable = false,columnDefinition = "DATE   COMMENT '离职日期'"  )
    private LocalDate dimissionDate;

    /**
     * 应签订劳动合同时间
     */
    @Column(name = "shouldSignContractDate",nullable = false,columnDefinition = "DATE   COMMENT '应签订劳动合同时间'"  )
    private LocalDate  shouldSignContractDate;

    /**
     * 是否签订劳动合同
     */
    @Column(name = "is_ifSignContract",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否签订劳动合同'"  , insertable = false  )
    private Boolean  ifSignContract;

    /**
     * 签订所属公司
     */
    @Column(name = "signBelongCompany",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '签订所属公司'"  )
    private String signBelongCompany;

    /**
     * 签订劳动合同开始日期
     */
    @Column(name = "signContractStartDate",nullable = false,columnDefinition = "DATE   COMMENT '签订劳动合同开始日期'"  )
    private LocalDate signContractStartDate;

    /**
     * 签订劳动合同到期日期
     */
    @Column(name = "signContractEndDate",nullable = false,columnDefinition = "DATE   COMMENT '签订劳动合同到期日期'"  )
    private LocalDate signContractEndDate;

    /**
     * 合同期限
     */
    @Column(name = "contractDeadline",nullable = false,columnDefinition = "INT  COMMENT '合同期限'"  )
    private Integer contractDeadline;

    /**
     * 是否续签
     */
    @Column(name = "is_ifRenew",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否续签'"  , insertable = false  )
    private Boolean  ifRenew;

    /**
     * 第一次续签是否需要合同变更
     */
    @Column(name = "is_ifNeedContractChangeFirst",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第一次续签是否需要合同变更'"  , insertable = false  )
    private Boolean  ifNeedContractChangeFirst;

    /**
     * 第一次续签开始日期
     */
    @Column(name = "firstRenewStartDate",nullable = false,columnDefinition = "DATE   COMMENT '第一次续签开始日期'"  )
    private LocalDate  firstRenewStartDate;

    /**
     * 第一次续签到期日期
     */
    @Column(name = "firstRenewEndDate",nullable = false,columnDefinition = "DATE   COMMENT '第一次续签到期日期'"  )
    private LocalDate  firstRenewEndDate;

    /**
     * 第一次续签合同期限
     */
    @Column(name = "firstReneweContractDeadline",nullable = false,columnDefinition = "INT  COMMENT '第一次续签合同期限'"  )
    private Integer firstReneweContractDeadline;

    /**
     * 是否第二次续签
     */
    @Column(name = "is_ifSecondRenew",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否第二次续签'"  , insertable = false  )
    private Boolean  ifSecondRenew;

    /**
     * 第二次续签是否需要合同变更
     */
    @Column(name = "is_ifNeedContractChangeSecond",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '第二次续签是否需要合同变更'"  , insertable = false  )
    private Boolean  ifNeedContractChangeSecond;

    /**
     * 第二次续签开始日期
     */
    @Column(name = "secondRenewStartDate",nullable = false,columnDefinition = "DATE   COMMENT '第二次续签开始日期'"  )
    private LocalDate  secondRenewStartDate;

    /**
     * 第二次续签到期日期
     */
    @Column(name = "secondRenewEndDate",nullable = false,columnDefinition = "DATE   COMMENT '第二次续签到期日期'"  )
    private LocalDate  secondRenewEndDate;

    /**
     * 是否解除合同
     */
    @Column(name = "is_ifRelieveContract",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否解除合同'"  , insertable = false  )
    private Boolean  ifRelieveContract;

    /**
     * 解除合同时间
     */
    @Column(name = "relieveContractDate",nullable = false,columnDefinition = "DATE   COMMENT '解除合同时间'"  )
    private LocalDate relieveContractDate;

    /**
     * 是否有附件
     */
    @Column(name = "is_ifAttachment",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有附件'"  , insertable = false  )
    private Boolean  ifAttachment;

    /**
     * 原件存储位置
     */
    @Column(name = "attachmentStorageLocation",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '原件存储位置'"  )
    private String  attachmentStorageLocation;

    /**
     * 合同状态
     */
    @Column(name = "contractStatus",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '合同状态'"  )
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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public AgreeProbationTime getAgreeProbationTime() {
        return agreeProbationTime;
    }

    public void setAgreeProbationTime(AgreeProbationTime agreeProbationTime) {
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

    public LocalDate getShouldSignContractDate() {
        return shouldSignContractDate;
    }

    public void setShouldSignContractDate(LocalDate shouldSignContractDate) {
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

    public LocalDate getSignContractStartDate() {
        return signContractStartDate;
    }

    public void setSignContractStartDate(LocalDate signContractStartDate) {
        this.signContractStartDate = signContractStartDate;
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

    public LocalDate getFirstRenewStartDate() {
        return firstRenewStartDate;
    }

    public void setFirstRenewStartDate(LocalDate firstRenewStartDate) {
        this.firstRenewStartDate = firstRenewStartDate;
    }

    public LocalDate getFirstRenewEndDate() {
        return firstRenewEndDate;
    }

    public void setFirstRenewEndDate(LocalDate firstRenewEndDate) {
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

    public LocalDate getSecondRenewStartDate() {
        return secondRenewStartDate;
    }

    public void setSecondRenewStartDate(LocalDate secondRenewStartDate) {
        this.secondRenewStartDate = secondRenewStartDate;
    }

    public LocalDate getSecondRenewEndDate() {
        return secondRenewEndDate;
    }

    public void setSecondRenewEndDate(LocalDate secondRenewEndDate) {
        this.secondRenewEndDate = secondRenewEndDate;
    }

    public Boolean getIfRelieveContract() {
        return ifRelieveContract;
    }

    public void setIfRelieveContract(Boolean ifRelieveContract) {
        this.ifRelieveContract = ifRelieveContract;
    }

    public LocalDate getRelieveContractDate() {
        return relieveContractDate;
    }

    public void setRelieveContractDate(LocalDate relieveContractDate) {
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

    public LocalDate getSignContractEndDate() {
        return signContractEndDate;
    }

    public void setSignContractEndDate(LocalDate signContractEndDate) {
        this.signContractEndDate = signContractEndDate;
    }
}