package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.recruit.type.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 15:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "recruit_interviewinfor")
public class InterviewInfor extends BaseEntity {

    /**
     * 日期
     */
    @Column(columnDefinition = "DATE COMMENT '电访日期' ")
    private LocalDate date;

    /**
     * 简历来源
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '简历来源' ")
    private String resumeResource;
    /**
     * 岗位
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '应聘岗位' ")
    private String position;
    /**
     * 应聘地区
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '应聘地区' ")
    private String area;

    /**
     * 应聘部门/项目组
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '应聘部门' ")
    private String department;

    /**
     * 姓名
     */
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT '姓名' ")
    private String name;

    /**
     * 性别
     */
    @Column(nullable = false, columnDefinition = "TINYINT(2) COMMENT '性别' ")
    private Gender gender;

    /**
     * 联系方式
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '联系电话' ")
    private String phone;
    /**
     * 简历筛选是否通过
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '简历筛选是否通过' ")
    private Boolean whetherPass;

    /**
     * 电子邮箱
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '电子邮箱' ")
    private String email;

    /**
     * 是否有相关工作经验
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否有相关工作经验' ")
    private Boolean workingExperience;
    /**
     * 第一次电访了解到的情况
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '第一次电访了解到的情况' ")
    private String firstPhoneSituation;

    /**
     * 求职考试第一题是否正确
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '求职考试第一题是否正确' ")
    private Boolean whetherFirstQuestionCorrect;

    /**
     * 邀约初试时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '初试时间' ")
    private LocalDateTime firstTestTime;
    /**
     * 初试负责人
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '初试负责人' ")
    private String firstTestPrincipal;
    /**
     * 初试地点
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '初试地点' ")
    private String firstPlace;
    /**
     * 初试意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '初试意见' ")
    private String firstTestAdvice;
    /**
     * 是否初试
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '初试是否是面试' ")
    private Boolean whetherFaceTest;
    /**
     * 未应约初试原因
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '未应约初试原因' ")
    private String notFirstCase;

    /**
     * 初试是否通过
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '初试是否通过' ")
    private Boolean whetherFirstTestPass;

    /**
     * 是否需要复试
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否需要复试' ")
    private Boolean whetherNeedSecondTest;

    /**
     * 复试时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '复试时间' ")
    private LocalDateTime secondTestTime;

    /**
     * 复试负责人
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '复试负责人' ")
    private String secondTestPrincipal;

    /**
     * 复试意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '复试意见' ")
    private String secondTestAdvice;

    /**
     * 复试是否通过
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '复试是否通过' ")
    private Boolean whetherSecondTestPass;

    /**
     * 薪资面谈时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '薪资面谈时间' ")
    private LocalDateTime salaryFaceTime;

    /**
     * 薪资面谈负责人
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '薪资面谈负责人' ")
    private String salaryFacePrincipal;
    /**
     * 面谈意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '面谈意见' ")
    private String faceAdvice;
    /**
     * 总经办
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '总经办' ")
    private String boss;

    /**
     * 总经办意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '总经办审批意见' ")
    private String bossAdvice;
    /**
     * 是否同意录用
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否同意录用' ")
    private Boolean agreedEmployed;

    /**
     * 审批时间
     */
    @Column(columnDefinition = "DATE COMMENT '审批时间' ")
    private LocalDate auditTime;

    /**
     * 是否接受录取
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否接受录取' ")
    private Boolean whetherAcceptAdmit;
    /**
     * 未接受录取原因
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '未接受录取原因' ")
    private String denyAdmitReason;
    /**
     * 入职时间
     */
    @Column(columnDefinition = "DATE COMMENT '入职时间' ")
    private LocalDate entryTime;

    /**
     * 是否住宿
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否住宿' ")
    private Boolean whetherAccommodation;

    /**
     * 是否使用公司电脑
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否使用公司电脑' ")
    private Boolean whetherUseFirmPC;
    /**
     * 入职地址
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '入职地址' ")
    private String entryAddress;
    /**
     * 是否入职
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否入职' ")
    private Boolean whetherEntry;

    /**
     * 未入职原因
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '未入职原因' ")
    private String denyEntryReason;
    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注' ")
    private String comment;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getWhetherPass() {
        return whetherPass;
    }

    public void setWhetherPass(Boolean whetherPass) {
        this.whetherPass = whetherPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(Boolean workingExperience) {
        this.workingExperience = workingExperience;
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


    public String getFirstTestPrincipal() {
        return firstTestPrincipal;
    }

    public void setFirstTestPrincipal(String firstTestPrincipal) {
        this.firstTestPrincipal = firstTestPrincipal;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getFirstTestAdvice() {
        return firstTestAdvice;
    }

    public void setFirstTestAdvice(String firstTestAdvice) {
        this.firstTestAdvice = firstTestAdvice;
    }

    public Boolean getWhetherFaceTest() {
        return whetherFaceTest;
    }

    public void setWhetherFaceTest(Boolean whetherFaceTest) {
        this.whetherFaceTest = whetherFaceTest;
    }

    public String getNotFirstCase() {
        return notFirstCase;
    }

    public void setNotFirstCase(String notFirstCase) {
        this.notFirstCase = notFirstCase;
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

    public LocalDateTime getFirstTestTime() {
        return firstTestTime;
    }

    public void setFirstTestTime(LocalDateTime firstTestTime) {
        this.firstTestTime = firstTestTime;
    }

    public LocalDateTime getSecondTestTime() {
        return secondTestTime;
    }

    public void setSecondTestTime(LocalDateTime secondTestTime) {
        this.secondTestTime = secondTestTime;
    }

    public LocalDateTime getSalaryFaceTime() {
        return salaryFaceTime;
    }

    public void setSalaryFaceTime(LocalDateTime salaryFaceTime) {
        this.salaryFaceTime = salaryFaceTime;
    }

    public String getSalaryFacePrincipal() {
        return salaryFacePrincipal;
    }

    public void setSalaryFacePrincipal(String salaryFacePrincipal) {
        this.salaryFacePrincipal = salaryFacePrincipal;
    }

    public String getFaceAdvice() {
        return faceAdvice;
    }

    public void setFaceAdvice(String faceAdvice) {
        this.faceAdvice = faceAdvice;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getBossAdvice() {
        return bossAdvice;
    }

    public void setBossAdvice(String bossAdvice) {
        this.bossAdvice = bossAdvice;
    }

    public Boolean getAgreedEmployed() {
        return agreedEmployed;
    }

    public void setAgreedEmployed(Boolean agreedEmployed) {
        this.agreedEmployed = agreedEmployed;
    }

    public LocalDate getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDate auditTime) {
        this.auditTime = auditTime;
    }

    public Boolean getWhetherAcceptAdmit() {
        return whetherAcceptAdmit;
    }

    public void setWhetherAcceptAdmit(Boolean whetherAcceptAdmit) {
        this.whetherAcceptAdmit = whetherAcceptAdmit;
    }

    public String getDenyAdmitReason() {
        return denyAdmitReason;
    }

    public void setDenyAdmitReason(String denyAdmitReason) {
        this.denyAdmitReason = denyAdmitReason;
    }

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
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

    public String getEntryAddress() {
        return entryAddress;
    }

    public void setEntryAddress(String entryAddress) {
        this.entryAddress = entryAddress;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
