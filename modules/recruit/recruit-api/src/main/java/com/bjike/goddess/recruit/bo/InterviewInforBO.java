package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.recruit.type.Gender;

/**
 * 面试信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InterviewInforBO extends BaseBO {

    /**
     * 电访日期
     */
    private String date;

    /**
     * 姓名
     */
    private String name;

    /**
     * 简历来源
     */
    private String resumeResource;

    /**
     * 应聘岗位
     */
    private String position;

    /**
     * 应聘部门
     */
    private String department;

    /**
     * 应聘地区
     */
    private String area;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 第一次电访了解到的情况
     */
    private String firstPhoneSituation;

    /**
     * 求职考试第一题是否正确
     */
    private Boolean whetherFirstQuestionCorrect;

    /**
     * 初试时间
     */
    private String firstTestTime;

    /**
     * 初试是否是面试
     */
    private Boolean whetherFaceTest;

    /**
     * 初试负责人
     */
    private String firstTestPrincipal;

    /**
     * 初试意见
     */
    private String firstTestAdvice;

    /**
     * 初试是否通过
     */
    private Boolean whetherFirstTestPass;

    /**
     * 是否需要复试
     */
    private Boolean whetherNeedSecondTest;

    /**
     * 复试时间
     */
    private String secondTestTime;

    /**
     * 复试负责人
     */
    private String secondTestPrincipal;

    /**
     * 复试意见
     */
    private String secondTestAdvice;

    /**
     * 复试是否通过
     */
    private Boolean whetherSecondTestPass;

    /**
     * 薪资面谈时间
     */
    private String salaryFaceTime;

    /**
     * 薪资面谈负责人
     */
    private String salaryFacePrincipal;

    /**
     * 是否已进行薪资面谈
     */
    private Boolean whetherSalaryFace;

    /**
     * 薪资面谈情况
     */
    private String salaryFaceSituation;

    /**
     * 总经办审核是否录取
     */
    private Boolean whetherPassBoss;

    /**
     * 总经办审批意见
     */
    private String bossAdvice;

    /**
     * 是否接受录取
     */
    private Boolean whetherAcceptAdmit;

    /**
     * 联络人
     */
    private String contactPerson;

    /**
     * 入职报道地址
     */
    private String entryAddress;

    /**
     * 未接受录取原因
     */
    private String denyAdmitReason;

    /**
     * 是否住宿
     */
    private Boolean whetherAccommodation;

    /**
     * 是否使用公司电脑
     */
    private Boolean whetherUseFirmPC;

    /**
     * 是否入职
     */
    private Boolean whetherEntry;

    /**
     * 未入职原因
     */
    private String denyEntryReason;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 备注
     */
    private String comment;

    /**
     * 是否服从调动安排
     */
    private Boolean whetherObeyTransfer;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResumeResource() {
        return resumeResource;
    }

    public void setResumeResource(String resumeResource) {
        this.resumeResource = resumeResource;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstPhoneSituation() {
        return firstPhoneSituation;
    }

    public void setFirstPhoneSituation(String firstPhoneSituation) {
        this.firstPhoneSituation = firstPhoneSituation;
    }

    public Boolean getWhetherFirstQuestionCorrect() {
        return whetherFirstQuestionCorrect;
    }

    public void setWhetherFirstQuestionCorrect(Boolean whetherFirstQuestionCorrect) {
        this.whetherFirstQuestionCorrect = whetherFirstQuestionCorrect;
    }

    public String getFirstTestTime() {
        return firstTestTime;
    }

    public void setFirstTestTime(String firstTestTime) {
        this.firstTestTime = firstTestTime;
    }

    public Boolean getWhetherFaceTest() {
        return whetherFaceTest;
    }

    public void setWhetherFaceTest(Boolean whetherFaceTest) {
        this.whetherFaceTest = whetherFaceTest;
    }

    public String getFirstTestPrincipal() {
        return firstTestPrincipal;
    }

    public void setFirstTestPrincipal(String firstTestPrincipal) {
        this.firstTestPrincipal = firstTestPrincipal;
    }

    public String getFirstTestAdvice() {
        return firstTestAdvice;
    }

    public void setFirstTestAdvice(String firstTestAdvice) {
        this.firstTestAdvice = firstTestAdvice;
    }

    public Boolean getWhetherFirstTestPass() {
        return whetherFirstTestPass;
    }

    public void setWhetherFirstTestPass(Boolean whetherFirstTestPass) {
        this.whetherFirstTestPass = whetherFirstTestPass;
    }

    public Boolean getWhetherNeedSecondTest() {
        return whetherNeedSecondTest;
    }

    public void setWhetherNeedSecondTest(Boolean whetherNeedSecondTest) {
        this.whetherNeedSecondTest = whetherNeedSecondTest;
    }

    public String getSecondTestTime() {
        return secondTestTime;
    }

    public void setSecondTestTime(String secondTestTime) {
        this.secondTestTime = secondTestTime;
    }

    public String getSecondTestPrincipal() {
        return secondTestPrincipal;
    }

    public void setSecondTestPrincipal(String secondTestPrincipal) {
        this.secondTestPrincipal = secondTestPrincipal;
    }

    public String getSecondTestAdvice() {
        return secondTestAdvice;
    }

    public void setSecondTestAdvice(String secondTestAdvice) {
        this.secondTestAdvice = secondTestAdvice;
    }

    public Boolean getWhetherSecondTestPass() {
        return whetherSecondTestPass;
    }

    public void setWhetherSecondTestPass(Boolean whetherSecondTestPass) {
        this.whetherSecondTestPass = whetherSecondTestPass;
    }

    public String getSalaryFaceTime() {
        return salaryFaceTime;
    }

    public void setSalaryFaceTime(String salaryFaceTime) {
        this.salaryFaceTime = salaryFaceTime;
    }

    public String getSalaryFacePrincipal() {
        return salaryFacePrincipal;
    }

    public void setSalaryFacePrincipal(String salaryFacePrincipal) {
        this.salaryFacePrincipal = salaryFacePrincipal;
    }

    public Boolean getWhetherSalaryFace() {
        return whetherSalaryFace;
    }

    public void setWhetherSalaryFace(Boolean whetherSalaryFace) {
        this.whetherSalaryFace = whetherSalaryFace;
    }

    public String getSalaryFaceSituation() {
        return salaryFaceSituation;
    }

    public void setSalaryFaceSituation(String salaryFaceSituation) {
        this.salaryFaceSituation = salaryFaceSituation;
    }

    public Boolean getWhetherPassBoss() {
        return whetherPassBoss;
    }

    public void setWhetherPassBoss(Boolean whetherPassBoss) {
        this.whetherPassBoss = whetherPassBoss;
    }

    public String getBossAdvice() {
        return bossAdvice;
    }

    public void setBossAdvice(String bossAdvice) {
        this.bossAdvice = bossAdvice;
    }

    public Boolean getWhetherAcceptAdmit() {
        return whetherAcceptAdmit;
    }

    public void setWhetherAcceptAdmit(Boolean whetherAcceptAdmit) {
        this.whetherAcceptAdmit = whetherAcceptAdmit;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEntryAddress() {
        return entryAddress;
    }

    public void setEntryAddress(String entryAddress) {
        this.entryAddress = entryAddress;
    }

    public String getDenyAdmitReason() {
        return denyAdmitReason;
    }

    public void setDenyAdmitReason(String denyAdmitReason) {
        this.denyAdmitReason = denyAdmitReason;
    }

    public Boolean getWhetherAccommodation() {
        return whetherAccommodation;
    }

    public void setWhetherAccommodation(Boolean whetherAccommodation) {
        this.whetherAccommodation = whetherAccommodation;
    }

    public Boolean getWhetherUseFirmPC() {
        return whetherUseFirmPC;
    }

    public void setWhetherUseFirmPC(Boolean whetherUseFirmPC) {
        this.whetherUseFirmPC = whetherUseFirmPC;
    }

    public Boolean getWhetherEntry() {
        return whetherEntry;
    }

    public void setWhetherEntry(Boolean whetherEntry) {
        this.whetherEntry = whetherEntry;
    }

    public String getDenyEntryReason() {
        return denyEntryReason;
    }

    public void setDenyEntryReason(String denyEntryReason) {
        this.denyEntryReason = denyEntryReason;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getWhetherObeyTransfer() {
        return whetherObeyTransfer;
    }

    public void setWhetherObeyTransfer(Boolean whetherObeyTransfer) {
        this.whetherObeyTransfer = whetherObeyTransfer;
    }
}
