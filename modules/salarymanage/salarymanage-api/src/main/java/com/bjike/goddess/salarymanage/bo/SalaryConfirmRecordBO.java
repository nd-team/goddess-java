package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.salarymanage.enums.Probation;

/**
 * 招聘面谈薪资确认记录业务接口
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-09-15 02:05 ]
 * @Description: [ 招聘面谈薪资确认记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryConfirmRecordBO extends BaseBO {
    /**
     * 姓名
     */
    private String userName;

    /**
     * 岗位
     */
    private String position;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 业务方向
     */
    private String businessDirection;

    /**
     * 技能
     */
    private String skill;

    /**
     * 工作年限
     */
    private String workAge;

    /**
     * 是否入职
     */
    private Boolean isEntry;

    /**
     * 员工编号
     */
    private String employeeID;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 入职时间
     */
    private String entryDate;

    /**
     * 入职前居住地
     */
    private String beforePlace;

    /**
     * 是否对工作地有要求
     */
    private Boolean ifAreaRequire;

    /**
     * 是否服从调动安排
     */
    private Boolean ifAcceptRemove;

    /**
     * 试用期是多长时间
     */
    private Probation probation;

    /**
     * 入职前期望薪资是多少
     */
    private Integer expectSalary;

    /**
     * 面谈后同意确认试用期薪水是多少
     */
    private Integer probationSalary;

    /**
     * 面谈后同意确认转正后薪水是多少
     */
    private Integer officialSalary;

    /**
     * 是否同意超出个税起征点3500以上的金额，需提供相对应金额发票
     */
    private Boolean ifHandInvoice;

    /**
     * 是否需要公司安排住宿
     */
    private Boolean ifNeedStay;

    /**
     * 是否同意工作满三个月并且转正之后从第四个月开始购买社保
     */
    private Boolean ifAgreeSocial;

    /**
     * 紧急联络人
     */
    private String emergencyContact;

    /**
     * 紧急联系人电话
     */
    private String emergencyContactPhone;

    /**
     * 入职前从事的通信专业/工作时间
     */
    private Integer communicationDate;

    /**
     * 入职前从事的非通信专业/工作时间
     */
    private Integer noCommunicationDate;

    /**
     * 其他特殊约定
     */
    private String speciaTerms;

    /**
     * 协办人
     */
    private String coOrganiser;

    /**
     * 是否有面谈确认单原件
     */
    private Boolean ifConfirmationSlip;

    /**
     * 是否有住宿
     */
    private Boolean ifStay;

    /**
     * 确认人
     */
    private String confirmPeople;

    /**
     * 是否已确认
     */
    private Boolean ifconfirm;

    /**
     * 存档人
     */
    private String archivePeople;

    /**
     * 状态
     */
    private String status;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    public Boolean getIsEntry() {
        return isEntry;
    }

    public void setIsEntry(Boolean isEntry) {
        this.isEntry = isEntry;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getBeforePlace() {
        return beforePlace;
    }

    public void setBeforePlace(String beforePlace) {
        this.beforePlace = beforePlace;
    }

    public Boolean getIfAreaRequire() {
        return ifAreaRequire;
    }

    public void setIfAreaRequire(Boolean ifAreaRequire) {
        this.ifAreaRequire = ifAreaRequire;
    }

    public Boolean getIfAcceptRemove() {
        return ifAcceptRemove;
    }

    public void setIfAcceptRemove(Boolean ifAcceptRemove) {
        this.ifAcceptRemove = ifAcceptRemove;
    }

    public Probation getProbation() {
        return probation;
    }

    public void setProbation(Probation probation) {
        this.probation = probation;
    }

    public Integer getExpectSalary() {
        return expectSalary;
    }

    public void setExpectSalary(Integer expectSalary) {
        this.expectSalary = expectSalary;
    }

    public Integer getProbationSalary() {
        return probationSalary;
    }

    public void setProbationSalary(Integer probationSalary) {
        this.probationSalary = probationSalary;
    }

    public Integer getOfficialSalary() {
        return officialSalary;
    }

    public void setOfficialSalary(Integer officialSalary) {
        this.officialSalary = officialSalary;
    }

    public Boolean getIfHandInvoice() {
        return ifHandInvoice;
    }

    public void setIfHandInvoice(Boolean ifHandInvoice) {
        this.ifHandInvoice = ifHandInvoice;
    }

    public Boolean getIfNeedStay() {
        return ifNeedStay;
    }

    public void setIfNeedStay(Boolean ifNeedStay) {
        this.ifNeedStay = ifNeedStay;
    }

    public Boolean getIfAgreeSocial() {
        return ifAgreeSocial;
    }

    public void setIfAgreeSocial(Boolean ifAgreeSocial) {
        this.ifAgreeSocial = ifAgreeSocial;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public Integer getCommunicationDate() {
        return communicationDate;
    }

    public void setCommunicationDate(Integer communicationDate) {
        this.communicationDate = communicationDate;
    }

    public Integer getNoCommunicationDate() {
        return noCommunicationDate;
    }

    public void setNoCommunicationDate(Integer noCommunicationDate) {
        this.noCommunicationDate = noCommunicationDate;
    }

    public String getSpeciaTerms() {
        return speciaTerms;
    }

    public void setSpeciaTerms(String speciaTerms) {
        this.speciaTerms = speciaTerms;
    }

    public String getCoOrganiser() {
        return coOrganiser;
    }

    public void setCoOrganiser(String coOrganiser) {
        this.coOrganiser = coOrganiser;
    }

    public Boolean getIfConfirmationSlip() {
        return ifConfirmationSlip;
    }

    public void setIfConfirmationSlip(Boolean ifConfirmationSlip) {
        this.ifConfirmationSlip = ifConfirmationSlip;
    }

    public Boolean getIfStay() {
        return ifStay;
    }

    public void setIfStay(Boolean ifStay) {
        this.ifStay = ifStay;
    }

    public String getConfirmPeople() {
        return confirmPeople;
    }

    public void setConfirmPeople(String confirmPeople) {
        this.confirmPeople = confirmPeople;
    }

    public Boolean getIfconfirm() {
        return ifconfirm;
    }

    public void setIfconfirm(Boolean ifconfirm) {
        this.ifconfirm = ifconfirm;
    }

    public String getArchivePeople() {
        return archivePeople;
    }

    public void setArchivePeople(String archivePeople) {
        this.archivePeople = archivePeople;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}