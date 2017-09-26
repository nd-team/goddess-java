package com.bjike.goddess.businsurance.to;

import com.bjike.goddess.businsurance.enums.Gender;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 团体意外险购买名单
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:24 ]
 * @Description: [ 团体意外险购买名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CasualtyPurchasingListTO extends BaseTO {

    /**
     * 保单号
     */
    @NotBlank(message = "保单号不能为空",groups = {ADD.class, EDIT.class})
    private String insurancePolicyNo;

    /**
     * 部门号
     */
    private String unitNo;

    /**
     * 分单号
     */
    private String singleNo;

    /**
     * 分单状态
     */
    private String singleStatus;

    /**
     * 生效日期
     */
    @NotBlank(message = "生效日期不能为空",groups = {ADD.class, EDIT.class})
    private String effectiveDate;

    /**
     * 退保申请日期
     */
    private String surrInsurApplyDate;

    /**
     * 保障层级
     */
    private String securityLeve;

    /**
     * 客户号码
     */
    private String clientPhone;

    /**
     * 被保人姓名
     */
    @NotBlank(message = "被保人姓名不能为空",groups = {ADD.class, EDIT.class})
    private String beApplicantName;

    /**
     * 证件类型
     */
    @NotBlank(message = "证件类型不能为空",groups = {ADD.class, EDIT.class})
    private String documentsType;

    /**
     * 证件号码
     */
    @NotBlank(message = "证件号码不能为空",groups = {ADD.class, EDIT.class})
    private String documentsPhone;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空",groups = {ADD.class, EDIT.class})
    private Gender gender;

    /**
     * 出生日期
     */
    @NotBlank(message = "出生日期不能为空",groups = {ADD.class, EDIT.class})
    private String birthDate;

    /**
     * 投保年龄
     */
    private Integer insuredAge;

    /**
     * 被保人类型
     */
    @NotBlank(message = "被保人类型不能为空",groups = {ADD.class, EDIT.class})
    private String beApplicantType;

    /**
     * 与主被保人关系
     */
    private String mainWithBeAppliRela;

    /**
     * 主被保险人姓名
     */
    private String mainBeAppliName;

    /**
     * 职业代码
     */
    private String occupationCode;

    /**
     * 职业名称
     */
    private String occupationName;

    /**
     * 员工号
     */
    private String employeeNum;

    /**
     * 所属部门
     */
    private String department;

    /**
     * 月薪
     */
    private String salaryMonth;

    /**
     * email邮箱
     */
    private String email;

    /**
     * 移动电话
     */
    private String mobilePhone;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 联系地址
     */
    private String contactAddress;

    /**
     * 联系邮编
     */
    private String contactZipcode;

    /**
     * 社保所在地
     */
    private String socialSecurityArea;

    /**
     * 社保卡号
     */
    private String socialSecurityCard;

    /**
     * 保险卡号
     */
    private String insuranceCard;

    /**
     * 银行账号用途
     */
    private String bankAccount;

    /**
     * 开户人姓名
     */
    private String openAccountName;

    /**
     * 开户人证件类型
     */
    private String openIdType;

    /**
     * 开户人证件号码
     */
    private String openIdNo;

    /**
     * 开户银行代码
     */
    private String openBankCode;

    /**
     * 开户行全称
     */
    private String openFullName;

    /**
     * 开户银行账号
     */
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

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getSurrInsurApplyDate() {
        return surrInsurApplyDate;
    }

    public void setSurrInsurApplyDate(String surrInsurApplyDate) {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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