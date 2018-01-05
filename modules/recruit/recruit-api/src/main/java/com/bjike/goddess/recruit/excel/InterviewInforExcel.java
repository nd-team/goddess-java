package com.bjike.goddess.recruit.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.recruit.type.Gender;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 15:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InterviewInforExcel extends BaseBO {
    /**
     * 日期
     */
    @ExcelHeader(name = "日期")
    private String date;

    /**
     * 简历来源
     */
    @ExcelHeader(name = "简历来源")
    private String resumeResource;
    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位")
    private String position;
    /**
     * 应聘地区
     */
    @ExcelHeader(name = "应聘地区")
    private String area;

    /**
     * 应聘部门/项目组
     */
    @ExcelHeader(name = "应聘部门/项目组")
    private String department;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名")
    private String name;

    /**
     * 性别
     */
    @ExcelHeader(name = "性别")
    private Gender gender;

    /**
     * 联系方式
     */
    @ExcelHeader(name = "联系方式")
    private String phone;
    /**
     * 简历筛选是否通过
     */
    @ExcelHeader(name = "简历筛选是否通过")
    private String whetherPass;

    /**
     * 电子邮箱
     */
    @ExcelHeader(name = "电子邮箱")
    private String email;

    /**
     * 是否有相关工作经验
     */
    @ExcelHeader(name = "是否有相关工作经验")
    private String workingExperience;
    /**
     * 第一次电访了解到的情况
     */
    @ExcelHeader(name = "第一次电访了解到的情况")
    private String firstPhoneSituation;

    /**
     * 求职考试第一题是否正确
     */
    @ExcelHeader(name = "求职考试第一题是否正确")
    private String whetherFirstQuestionCorrect;

    /**
     * 邀约初试时间
     */
    @ExcelHeader(name = "邀约初试时间")
    private String firstTestTime;
    /**
     * 初试负责人
     */
    @ExcelHeader(name = "初试负责人")
    private String firstTestPrincipal;
    /**
     * 初试地点
     */
    @ExcelHeader(name = "初试地点")
    private String firstPlace;
    /**
     * 初试意见
     */
    @ExcelHeader(name = "初试意见")
    private String firstTestAdvice;
    /**
     * 是否初试
     */
    @ExcelHeader(name = "是否初试")
    private String whetherFaceTest;
    /**
     * 未应约初试原因
     */
    @ExcelHeader(name = "未应约初试原因")
    private String notFirstCase;

    /**
     * 初试是否通过
     */
    @ExcelHeader(name = "初试是否通过")
    private String whetherFirstTestPass;

    /**
     * 是否需要复试
     */
    @ExcelHeader(name = "是否需要复试")
    private String whetherNeedSecondTest;

    /**
     * 复试时间
     */
    @ExcelHeader(name = "复试时间")
    private String secondTestTime;

    /**
     * 复试负责人
     */
    @ExcelHeader(name = "复试负责人")
    private String secondTestPrincipal;

    /**
     * 复试意见
     */
    @ExcelHeader(name = "复试意见")
    private String secondTestAdvice;

    /**
     * 复试是否通过
     */
    @ExcelHeader(name = "复试是否通过")
    private String whetherSecondTestPass;

    /**
     * 薪资面谈时间
     */
    @ExcelHeader(name = "薪资面谈时间")
    private String salaryFaceTime;

    /**
     * 薪资面谈负责人
     */
    @ExcelHeader(name = "薪资面谈负责人")
    private String salaryFacePrincipal;
    /**
     * 面谈意见
     */
    @ExcelHeader(name = "面谈意见")
    private String faceAdvice;
    /**
     * 总经办
     */
    @ExcelHeader(name = "总经办")
    private String boss;

    /**
     * 总经办意见
     */
    @ExcelHeader(name = "总经办意见")
    private String bossAdvice;
    /**
     * 是否同意录用
     */
    @ExcelHeader(name = "是否同意录用")
    private String agreedEmployed;

    /**
     * 审批时间
     */
    @ExcelHeader(name = "审批时间")
    private String auditTime;

    /**
     * 是否接受录取
     */
    @ExcelHeader(name = "是否接受录取")
    private String whetherAcceptAdmit;
    /**
     * 未接受录取原因
     */
    @ExcelHeader(name = "未接受录取原因")
    private String denyAdmitReason;
    /**
     * 入职时间
     */
    @ExcelHeader(name = "入职时间")
    private String entryTime;

    /**
     * 是否住宿
     */
    @ExcelHeader(name = "是否住宿")
    private String whetherAccommodation;

    /**
     * 是否使用公司电脑
     */
    @ExcelHeader(name = "是否使用公司电脑")
    private String whetherUseFirmPC;
    /**
     * 入职地址
     */
    @ExcelHeader(name = "入职地址")
    private String entryAddress;
    /**
     * 是否入职
     */
    @ExcelHeader(name = "是否入职")
    private String whetherEntry;

    /**
     * 未入职原因
     */
    @ExcelHeader(name = "未入职原因")
    private String denyEntryReason;
    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String comment;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getWhetherPass() {
        return whetherPass;
    }

    public void setWhetherPass(String whetherPass) {
        this.whetherPass = whetherPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(String workingExperience) {
        this.workingExperience = workingExperience;
    }

    public String getFirstPhoneSituation() {
        return firstPhoneSituation;
    }

    public void setFirstPhoneSituation(String firstPhoneSituation) {
        this.firstPhoneSituation = firstPhoneSituation;
    }

    public String getWhetherFirstQuestionCorrect() {
        return whetherFirstQuestionCorrect;
    }

    public void setWhetherFirstQuestionCorrect(String whetherFirstQuestionCorrect) {
        this.whetherFirstQuestionCorrect = whetherFirstQuestionCorrect;
    }

    public String getFirstTestTime() {
        return firstTestTime;
    }

    public void setFirstTestTime(String firstTestTime) {
        this.firstTestTime = firstTestTime;
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

    public String getWhetherFaceTest() {
        return whetherFaceTest;
    }

    public void setWhetherFaceTest(String whetherFaceTest) {
        this.whetherFaceTest = whetherFaceTest;
    }

    public String getNotFirstCase() {
        return notFirstCase;
    }

    public void setNotFirstCase(String notFirstCase) {
        this.notFirstCase = notFirstCase;
    }

    public String getWhetherFirstTestPass() {
        return whetherFirstTestPass;
    }

    public void setWhetherFirstTestPass(String whetherFirstTestPass) {
        this.whetherFirstTestPass = whetherFirstTestPass;
    }

    public String getWhetherNeedSecondTest() {
        return whetherNeedSecondTest;
    }

    public void setWhetherNeedSecondTest(String whetherNeedSecondTest) {
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

    public String getWhetherSecondTestPass() {
        return whetherSecondTestPass;
    }

    public void setWhetherSecondTestPass(String whetherSecondTestPass) {
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

    public String getAgreedEmployed() {
        return agreedEmployed;
    }

    public void setAgreedEmployed(String agreedEmployed) {
        this.agreedEmployed = agreedEmployed;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getWhetherAcceptAdmit() {
        return whetherAcceptAdmit;
    }

    public void setWhetherAcceptAdmit(String whetherAcceptAdmit) {
        this.whetherAcceptAdmit = whetherAcceptAdmit;
    }

    public String getDenyAdmitReason() {
        return denyAdmitReason;
    }

    public void setDenyAdmitReason(String denyAdmitReason) {
        this.denyAdmitReason = denyAdmitReason;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getWhetherAccommodation() {
        return whetherAccommodation;
    }

    public void setWhetherAccommodation(String whetherAccommodation) {
        this.whetherAccommodation = whetherAccommodation;
    }

    public String getWhetherUseFirmPC() {
        return whetherUseFirmPC;
    }

    public void setWhetherUseFirmPC(String whetherUseFirmPC) {
        this.whetherUseFirmPC = whetherUseFirmPC;
    }

    public String getEntryAddress() {
        return entryAddress;
    }

    public void setEntryAddress(String entryAddress) {
        this.entryAddress = entryAddress;
    }

    public String getWhetherEntry() {
        return whetherEntry;
    }

    public void setWhetherEntry(String whetherEntry) {
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
