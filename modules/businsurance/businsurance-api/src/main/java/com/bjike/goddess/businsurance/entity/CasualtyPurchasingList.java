package com.bjike.goddess.businsurance.entity;

import com.bjike.goddess.businsurance.enums.Gender;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 团体意外险购买名单
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:24 ]
 * @Description: [ 团体意外险购买名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businsurance_casualtypurchasinglist")
public class CasualtyPurchasingList extends BaseEntity {

    /**
     * 保单号
     */
    @Column(name = "insurancePolicyNo", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '保单号'")
    private String insurancePolicyNo;

    /**
     * 部门号
     */
    @Column(name = "unitNo", columnDefinition = "VARCHAR(255)   COMMENT '部门号'")
    private String unitNo;

    /**
     * 分单号
     */
    @Column(name = "singleNo",columnDefinition = "VARCHAR(255)   COMMENT '分单号'")
    private String singleNo;

    /**
     * 分单状态
     */
    @Column(name = "singleStatus", columnDefinition = "VARCHAR(255)   COMMENT '分单状态'")
    private String singleStatus;

    /**
     * 生效日期
     */
    @Column(name = "effectiveDate", nullable = false, columnDefinition = "DATE   COMMENT '生效日期'")
    private LocalDate effectiveDate;

    /**
     * 退保申请日期
     */
    @Column(name = "surrInsurApplyDate",columnDefinition = "DATE   COMMENT '退保申请日期'")
    private LocalDate surrInsurApplyDate;

    /**
     * 保障层级
     */
    @Column(name = "securityLeve",columnDefinition = "VARCHAR(255)   COMMENT '保障层级'")
    private String securityLeve;

    /**
     * 客户号码
     */
    @Column(name = "clientPhone",  columnDefinition = "VARCHAR(255)   COMMENT '客户号码'")
    private String clientPhone;

    /**
     * 被保人姓名
     */
    @Column(name = "beApplicantName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '被保人姓名'")
    private String beApplicantName;

    /**
     * 证件类型
     */
    @Column(name = "documentsType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证件类型'")
    private String documentsType;

    /**
     * 证件号码
     */
    @Column(name = "documentsPhone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证件号码'")
    private String documentsPhone;

    /**
     * 性别
     */
    @Column(name = "gender", nullable = false, columnDefinition = "TINYINT(255)   COMMENT '性别'")
    private Gender gender;

    /**
     * 出生日期
     */
    @Column(name = "birthDate", nullable = false, columnDefinition = "DATE   COMMENT '出生日期'")
    private LocalDate birthDate;

    /**
     * 投保年龄
     */
    @Column(name = "insuredAge",  columnDefinition = "TINYINT(2)   COMMENT '投保年龄'")
    private Integer insuredAge;

    /**
     * 被保人类型
     */
    @Column(name = "beApplicantType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '被保人类型'")
    private String beApplicantType;

    /**
     * 与主被保人关系
     */
    @Column(name = "mainWithBeAppliRela",columnDefinition = "VARCHAR(255)   COMMENT '与主被保人关系'")
    private String mainWithBeAppliRela;

    /**
     * 主被保险人姓名
     */
    @Column(name = "mainBeAppliName", columnDefinition = "VARCHAR(255)   COMMENT '主被保险人姓名'")
    private String mainBeAppliName;

    /**
     * 职业代码
     */
    @Column(name = "occupationCode", columnDefinition = "VARCHAR(255)   COMMENT '职业代码'")
    private String occupationCode;

    /**
     * 职业名称
     */
    @Column(name = "occupationName", columnDefinition = "VARCHAR(255)   COMMENT '职业名称'")
    private String occupationName;

    /**
     * 员工号
     */
    @Column(name = "employeeNum", columnDefinition = "VARCHAR(255)   COMMENT '员工号'")
    private String employeeNum;

    /**
     * 所属部门
     */
    @Column(name = "department",  columnDefinition = "VARCHAR(255)   COMMENT '所属部门'")
    private String department;

    /**
     * 月薪
     */
    @Column(name = "salaryMonth", columnDefinition = "VARCHAR(255)   COMMENT '月薪'")
    private String salaryMonth;

    /**
     * email邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(255)   COMMENT 'email邮箱'")
    private String email;

    /**
     * 移动电话
     */
    @Column(name = "mobilePhone", columnDefinition = "VARCHAR(255)   COMMENT '移动电话'")
    private String mobilePhone;

    /**
     * 联系电话
     */
    @Column(name = "contactPhone", columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String contactPhone;

    /**
     * 联系地址
     */
    @Column(name = "contactAddress", columnDefinition = "VARCHAR(255)   COMMENT '联系地址'")
    private String contactAddress;

    /**
     * 联系邮编
     */
    @Column(name = "contactZipcode", columnDefinition = "VARCHAR(255)   COMMENT '联系邮编'")
    private String contactZipcode;

    /**
     * 社保所在地
     */
    @Column(name = "socialSecurityArea", columnDefinition = "VARCHAR(255)   COMMENT '社保所在地'")
    private String socialSecurityArea;

    /**
     * 社保卡号
     */
    @Column(name = "socialSecurityCard", columnDefinition = "VARCHAR(255)   COMMENT '社保卡号'")
    private String socialSecurityCard;

    /**
     * 保险卡号
     */
    @Column(name = "insuranceCard", columnDefinition = "VARCHAR(255)   COMMENT '保险卡号'")
    private String insuranceCard;

    /**
     * 银行账号用途
     */
    @Column(name = "bankAccount", columnDefinition = "VARCHAR(255)   COMMENT '银行账号用途'")
    private String bankAccount;

    /**
     * 开户人姓名
     */
    @Column(name = "openAccountName", columnDefinition = "VARCHAR(255)   COMMENT '开户人姓名'")
    private String openAccountName;

    /**
     * 开户人证件类型
     */
    @Column(name = "openIdType", columnDefinition = "VARCHAR(255)   COMMENT '开户人证件类型'")
    private String openIdType;

    /**
     * 开户人证件号码
     */
    @Column(name = "openIdNo", columnDefinition = "VARCHAR(255)   COMMENT '开户人证件号码'")
    private String openIdNo;

    /**
     * 开户银行代码
     */
    @Column(name = "openBankCode", columnDefinition = "VARCHAR(255)   COMMENT '开户银行代码'")
    private String openBankCode;

    /**
     * 开户行全称
     */
    @Column(name = "openFullName", columnDefinition = "VARCHAR(255)   COMMENT '开户行全称'")
    private String openFullName;

    /**
     * 开户银行账号
     */
    @Column(name = "openBankAccountNum", columnDefinition = "VARCHAR(255)   COMMENT '开户银行账号'")
    private String openBankAccountNum;


    public String getInsurancePolicyNo() {
        return insurancePolicyNo;
    }

    public void setInsurancePolicyNo(String insurancePolicyNo) {
        this.insurancePolicyNo = insurancePolicyNo;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getSingleNo() {
        return singleNo;
    }

    public void setSingleNo(String singleNo) {
        this.singleNo = singleNo;
    }

    public String getSingleStatus() {
        return singleStatus;
    }

    public void setSingleStatus(String singleStatus) {
        this.singleStatus = singleStatus;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getSurrInsurApplyDate() {
        return surrInsurApplyDate;
    }

    public void setSurrInsurApplyDate(LocalDate surrInsurApplyDate) {
        this.surrInsurApplyDate = surrInsurApplyDate;
    }

    public String getSecurityLeve() {
        return securityLeve;
    }

    public void setSecurityLeve(String securityLeve) {
        this.securityLeve = securityLeve;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getBeApplicantName() {
        return beApplicantName;
    }

    public void setBeApplicantName(String beApplicantName) {
        this.beApplicantName = beApplicantName;
    }

    public String getDocumentsType() {
        return documentsType;
    }

    public void setDocumentsType(String documentsType) {
        this.documentsType = documentsType;
    }

    public String getDocumentsPhone() {
        return documentsPhone;
    }

    public void setDocumentsPhone(String documentsPhone) {
        this.documentsPhone = documentsPhone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getInsuredAge() {
        return insuredAge;
    }

    public void setInsuredAge(Integer insuredAge) {
        this.insuredAge = insuredAge;
    }

    public String getBeApplicantType() {
        return beApplicantType;
    }

    public void setBeApplicantType(String beApplicantType) {
        this.beApplicantType = beApplicantType;
    }

    public String getMainWithBeAppliRela() {
        return mainWithBeAppliRela;
    }

    public void setMainWithBeAppliRela(String mainWithBeAppliRela) {
        this.mainWithBeAppliRela = mainWithBeAppliRela;
    }

    public String getMainBeAppliName() {
        return mainBeAppliName;
    }

    public void setMainBeAppliName(String mainBeAppliName) {
        this.mainBeAppliName = mainBeAppliName;
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactZipcode() {
        return contactZipcode;
    }

    public void setContactZipcode(String contactZipcode) {
        this.contactZipcode = contactZipcode;
    }

    public String getSocialSecurityArea() {
        return socialSecurityArea;
    }

    public void setSocialSecurityArea(String socialSecurityArea) {
        this.socialSecurityArea = socialSecurityArea;
    }

    public String getSocialSecurityCard() {
        return socialSecurityCard;
    }

    public void setSocialSecurityCard(String socialSecurityCard) {
        this.socialSecurityCard = socialSecurityCard;
    }

    public String getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(String insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOpenAccountName() {
        return openAccountName;
    }

    public void setOpenAccountName(String openAccountName) {
        this.openAccountName = openAccountName;
    }

    public String getOpenIdType() {
        return openIdType;
    }

    public void setOpenIdType(String openIdType) {
        this.openIdType = openIdType;
    }

    public String getOpenIdNo() {
        return openIdNo;
    }

    public void setOpenIdNo(String openIdNo) {
        this.openIdNo = openIdNo;
    }

    public String getOpenBankCode() {
        return openBankCode;
    }

    public void setOpenBankCode(String openBankCode) {
        this.openBankCode = openBankCode;
    }

    public String getOpenFullName() {
        return openFullName;
    }

    public void setOpenFullName(String openFullName) {
        this.openFullName = openFullName;
    }

    public String getOpenBankAccountNum() {
        return openBankAccountNum;
    }

    public void setOpenBankAccountNum(String openBankAccountNum) {
        this.openBankAccountNum = openBankAccountNum;
    }
}