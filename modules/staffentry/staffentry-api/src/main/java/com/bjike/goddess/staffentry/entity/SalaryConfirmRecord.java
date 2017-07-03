package com.bjike.goddess.staffentry.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 薪资确认记录
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 16:59]
 * @Description: [薪资确认记录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "staffentry_salaryconfirmrecord")
public class SalaryConfirmRecord extends BaseEntity {

    /**
     *姓名
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '姓名'")
    private String name;
    /**
     *员工编号
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '员工编号'")
    private String employeeID;
    /**
     *籍贯
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '籍贯'")
    private String nativePlace;
    /**
     *办理入职时间
     */
    @Column(nullable = false,columnDefinition = "DATE COMMENT '办理入职时间'")
    private LocalDate entryTime;
    /**
     *入职前居住地
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '入职前居住地'")
    private String beforeResidence;
    /**
     *是否对工作地有要求
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '是否对工作地有要求'")
    private String workPlaceRequest;
    /**
     *试用期是多长时间
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '试用期是多长时间'")
    private String probationDuration;
    /**
     *期望薪资
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '期望薪资'")
    private String expectSalary;
    /**
     *试用期薪资
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '试用期薪资'")
    private String probationSalary;
    /**
     *转正后薪资
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '转正后薪资'")
    private String officialSalary;
    /**
     *是否同意征税
     */
    @Column(name ="is_whetherTax", columnDefinition = "TINYINT(2)   COMMENT '是否同意征税(0无，1有)'", nullable = false )
    private Boolean whetherTax;
    /**
     *是否需要公司安排住宿
     */
    @Column(name ="is_whetherArrangeResidence", columnDefinition = "TINYINT(2)   COMMENT '是否需要公司安排住宿(0无，1有)'", nullable = false )
    private Boolean whetherArrangeResidence;
    /**
     *是否购买社保
     */
    @Column(name ="is_whetherSocialSecurity", columnDefinition = "TINYINT(2) COMMENT '是否购买社保(0无，1有)'", nullable = false )
    private Boolean whetherSocialSecurity;
    /**
     *紧急联络人
     */
    @Column(  columnDefinition = "VARCHAR(255) COMMENT '紧急联络人'")
    private String emergencyContract;
    /**
     *紧急联络人电话
     */
    @Column( columnDefinition = "VARCHAR(255) COMMENT '紧急联络人电话'")
    private String contractPhone;
    /**
     *从事通信工作时长
     */
    @Column( columnDefinition = "VARCHAR(255) COMMENT '从事通信工作时长'")
    private String communicationWorkDuration;
    /**
     *从事非通信工作时长
     */
    @Column( columnDefinition = "VARCHAR(255) COMMENT '从事非通信工作时长'")
    private String notCommunicationWorkDuration;
    /**
     *其他特殊要求
     */
    @Column( columnDefinition = "VARCHAR(255) COMMENT '其他特殊要求'")
    private String otherSpecialNeeds;
    /**
     *协办人
     */
    @Column( columnDefinition = "VARCHAR(255) COMMENT '协办人'")
    private String assistant;
    /**
     *是否有面谈确认单原件
     */
    @Column(name ="is_whetherVoucher", columnDefinition = "TINYINT(2)   COMMENT '是否有面谈确认单原件(0无，1有)'", nullable = false )
    private Boolean whetherVoucher;
    /**
     *是否有住宿
     */
    @Column(name ="is_whetherAccommodation", columnDefinition = "TINYINT(2)   COMMENT '是否有住宿(0无，1有)'", nullable = false )
    private Boolean whetherAccommodation;
    /**
     *确认人
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '确认人'")
    private String confirmPerson;
    /**
     *存档人
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '存档人'")
    private String archivePerson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public String getBeforeResidence() {
        return beforeResidence;
    }

    public void setBeforeResidence(String beforeResidence) {
        this.beforeResidence = beforeResidence;
    }

    public String getWorkPlaceRequest() {
        return workPlaceRequest;
    }

    public void setWorkPlaceRequest(String workPlaceRequest) {
        this.workPlaceRequest = workPlaceRequest;
    }

    public String getProbationDuration() {
        return probationDuration;
    }

    public void setProbationDuration(String probationDuration) {
        this.probationDuration = probationDuration;
    }

    public String getExpectSalary() {
        return expectSalary;
    }

    public void setExpectSalary(String expectSalary) {
        this.expectSalary = expectSalary;
    }

    public String getProbationSalary() {
        return probationSalary;
    }

    public void setProbationSalary(String probationSalary) {
        this.probationSalary = probationSalary;
    }

    public String getOfficialSalary() {
        return officialSalary;
    }

    public void setOfficialSalary(String officialSalary) {
        this.officialSalary = officialSalary;
    }

    public Boolean getWhetherTax() {
        return whetherTax;
    }

    public void setWhetherTax(Boolean whetherTax) {
        this.whetherTax = whetherTax;
    }

    public Boolean getWhetherArrangeResidence() {
        return whetherArrangeResidence;
    }

    public void setWhetherArrangeResidence(Boolean whetherArrangeResidence) {
        this.whetherArrangeResidence = whetherArrangeResidence;
    }

    public Boolean getWhetherSocialSecurity() {
        return whetherSocialSecurity;
    }

    public void setWhetherSocialSecurity(Boolean whetherSocialSecurity) {
        this.whetherSocialSecurity = whetherSocialSecurity;
    }

    public String getEmergencyContract() {
        return emergencyContract;
    }

    public void setEmergencyContract(String emergencyContract) {
        this.emergencyContract = emergencyContract;
    }

    public String getContractPhone() {
        return contractPhone;
    }

    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
    }

    public String getCommunicationWorkDuration() {
        return communicationWorkDuration;
    }

    public void setCommunicationWorkDuration(String communicationWorkDuration) {
        this.communicationWorkDuration = communicationWorkDuration;
    }

    public String getNotCommunicationWorkDuration() {
        return notCommunicationWorkDuration;
    }

    public void setNotCommunicationWorkDuration(String notCommunicationWorkDuration) {
        this.notCommunicationWorkDuration = notCommunicationWorkDuration;
    }

    public String getOtherSpecialNeeds() {
        return otherSpecialNeeds;
    }

    public void setOtherSpecialNeeds(String otherSpecialNeeds) {
        this.otherSpecialNeeds = otherSpecialNeeds;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public Boolean getWhetherVoucher() {
        return whetherVoucher;
    }

    public void setWhetherVoucher(Boolean whetherVoucher) {
        this.whetherVoucher = whetherVoucher;
    }

    public Boolean getWhetherAccommodation() {
        return whetherAccommodation;
    }

    public void setWhetherAccommodation(Boolean whetherAccommodation) {
        this.whetherAccommodation = whetherAccommodation;
    }

    public String getConfirmPerson() {
        return confirmPerson;
    }

    public void setConfirmPerson(String confirmPerson) {
        this.confirmPerson = confirmPerson;
    }

    public String getArchivePerson() {
        return archivePerson;
    }

    public void setArchivePerson(String archivePerson) {
        this.archivePerson = archivePerson;
    }
}
