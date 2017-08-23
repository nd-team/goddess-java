package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 个人简介
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_individualresume")
public class IndividualResume extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;

    /**
     * 部门/项目组
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '部门/项目组'")
    private String department;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "employeeId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '员工编号'")
    private String employeeId;

    /**
     * 岗位
     */
    @Column(name = "post", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '岗位'")
    private String post;

    /**
     * 工作邮箱
     */
    @Column(name = "eMsil", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '工作邮箱'")
    private String eMsil;

    /**
     * 入职日期
     */
    @Column(name = "entryDate", nullable = false, columnDefinition = "DATE COMMENT '入职日期'")
    private LocalDate entryDate;

    /**
     * 在职时间(月)
     */
    @Column(name = "tenancyDuration", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '在职时间(月)'")
    private String tenancyDuration;

    /**
     * 转正时间
     */
    @Column(name = "positiveTime", nullable = false, columnDefinition = "DATE COMMENT '转正时间'")
    private LocalDate positiveTime;

    /**
     * 是否购买社保
     */
    @Column(name = "whetherSocialSecurity", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0  COMMENT '是否购买社保'")
    private Boolean whetherSocialSecurity;

    /**
     * 社保购买时间
     */
    @Column(name = "buySocialSecurityTime", columnDefinition = "DATE COMMENT '社保购买时间'")
    private LocalDate buySocialSecurityTime;

    /**
     * 技能等级
     */
    @Column(name = "skillRank", columnDefinition = "VARCHAR(255) COMMENT '技能等级'")
    private String skillRank;

    /**
     * 管理等级
     */
    @Column(name = "manageGrade", columnDefinition = "VARCHAR(255) COMMENT '管理等级'")
    private String manageGrade;

    /**
     * 项目提成
     */
    @Column(name = "itemCommission", columnDefinition = "VARCHAR(255) COMMENT '项目提成'")
    private String itemCommission;

    /**
     * 管理提成
     */
    @Column(name = "manageCommission", columnDefinition = "VARCHAR(255) COMMENT '管理提成'")
    private String manageCommission;

    /**
     * 奖励分数
     */
    @Column(name = "awardScore", columnDefinition = "INT(11) COMMENT '奖励分数'")
    private Integer awardScore;

    /**
     * 处罚分数
     */
    @Column(name = "penaltyScore", columnDefinition = "INT(11) COMMENT '处罚分数'")
    private Integer penaltyScore;

    /**
     * 经验值
     */
    @Column(name = "empiricalValue", columnDefinition = "VARCHAR(255) COMMENT '经验值'")
    private String empiricalValue;

    /**
     * 补助额度
     */
    @Column(name = "subsidyAmount", columnDefinition = "VARCHAR(255) COMMENT '补助额度'")
    private String subsidyAmount;

    /**
     * 年假天数
     */
    @Column(name = "annualLeave", columnDefinition = "DECIMAL(10,2) COMMENT '年假天数'")
    private Double annualLeave;

    /**
     * 个人愿景
     */
    @Column(name = "individualVision", columnDefinition = "VARCHAR(255) COMMENT '个人愿景'")
    private String individualVision;

    /**
     * 照片
     */
    @Column(name = "picture", columnDefinition = "VARCHAR(255) COMMENT '照片'")
    private String picture;

    /**
     * 兴趣爱好
     */
    @Column(name = "hobbies", columnDefinition = "VARCHAR(255) COMMENT '兴趣爱好'")
    private String hobbies;

    /**
     * 个人邮箱
     */
    @Column(name = "personalEmail", columnDefinition = "VARCHAR(255) COMMENT '个人邮箱'")
    private String personalEmail;

    /**
     * 出生年月
     */
    @Column(name = "dateOfBirth", columnDefinition = "VARCHAR(255) COMMENT '出生年月'")
    private String dateOfBirth;

    /**
     * QQ号
     */
    @Column(name = "qqNumber", columnDefinition = "VARCHAR(255) COMMENT 'QQ号'")
    private String qqNumber;

    /**
     * 微信号
     */
    @Column(name = "wechatId", columnDefinition = "VARCHAR(255) COMMENT '微信号'")
    private String wechatId;

    /**
     * 手机号
     */
    @Column(name = "mobile", columnDefinition = "VARCHAR(255) COMMENT '手机号'")
    private String mobile;

    /**
     * 紧急联系人
     */
    @Column(name = "emergencyContact", columnDefinition = "VARCHAR(255) COMMENT '紧急联系人'")
    private String emergencyContact;

    /**
     * 紧急联系人手机号
     */
    @Column(name = "emergencyContactPhone", columnDefinition = "VARCHAR(255) COMMENT '紧急联系人手机号'")
    private String emergencyContactPhone;

    /**
     * 学历
     */
    @Column(name = "education", columnDefinition = "VARCHAR(255) COMMENT '学历'")
    private String education;

    /**
     * 专业
     */
    @Column(name = "specialty", columnDefinition = "VARCHAR(255) COMMENT '专业'")
    private String specialty;

    /**
     * 专业学校
     */
    @Column(name = "academy", columnDefinition = "VARCHAR(255) COMMENT '专业学校'")
    private String academy;

    /**
     * 毕业时间
     */
    @Column(name = "graduationTime", columnDefinition = "VARCHAR(255) COMMENT '毕业时间'")
    private String graduationTime;

    /**
     * 现居住地
     */
    @Column(name = "nowResidence", columnDefinition = "VARCHAR(255) COMMENT '现居住地'")
    private String nowResidence;

    /**
     * 户口地址
     */
    @Column(name = "registeredAddress", columnDefinition = "VARCHAR(255) COMMENT '户口地址'")
    private String registeredAddress;

    /**
     * 工作年限
     */
    @Column(name = "workExperience", columnDefinition = "VARCHAR(255) COMMENT '工作年限'")
    private String workExperience;


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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEMsil() {
        return eMsil;
    }

    public void setEMsil(String eMsil) {
        this.eMsil = eMsil;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getTenancyDuration() {
        return tenancyDuration;
    }

    public void setTenancyDuration(String tenancyDuration) {
        this.tenancyDuration = tenancyDuration;
    }

    public LocalDate getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(LocalDate positiveTime) {
        this.positiveTime = positiveTime;
    }

    public Boolean getWhetherSocialSecurity() {
        return whetherSocialSecurity;
    }

    public void setWhetherSocialSecurity(Boolean whetherSocialSecurity) {
        this.whetherSocialSecurity = whetherSocialSecurity;
    }

    public LocalDate getBuySocialSecurityTime() {
        return buySocialSecurityTime;
    }

    public void setBuySocialSecurityTime(LocalDate buySocialSecurityTime) {
        this.buySocialSecurityTime = buySocialSecurityTime;
    }

    public String getSkillRank() {
        return skillRank;
    }

    public void setSkillRank(String skillRank) {
        this.skillRank = skillRank;
    }

    public String getManageGrade() {
        return manageGrade;
    }

    public void setManageGrade(String manageGrade) {
        this.manageGrade = manageGrade;
    }

    public String getItemCommission() {
        return itemCommission;
    }

    public void setItemCommission(String itemCommission) {
        this.itemCommission = itemCommission;
    }

    public String getManageCommission() {
        return manageCommission;
    }

    public void setManageCommission(String manageCommission) {
        this.manageCommission = manageCommission;
    }

    public Integer getAwardScore() {
        return awardScore;
    }

    public void setAwardScore(Integer awardScore) {
        this.awardScore = awardScore;
    }

    public Integer getPenaltyScore() {
        return penaltyScore;
    }

    public void setPenaltyScore(Integer penaltyScore) {
        this.penaltyScore = penaltyScore;
    }

    public String getEmpiricalValue() {
        return empiricalValue;
    }

    public void setEmpiricalValue(String empiricalValue) {
        this.empiricalValue = empiricalValue;
    }

    public String getSubsidyAmount() {
        return subsidyAmount;
    }

    public void setSubsidyAmount(String subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    public Double getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(Double annualLeave) {
        this.annualLeave = annualLeave;
    }

    public String getIndividualVision() {
        return individualVision;
    }

    public void setIndividualVision(String individualVision) {
        this.individualVision = individualVision;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getNowResidence() {
        return nowResidence;
    }

    public void setNowResidence(String nowResidence) {
        this.nowResidence = nowResidence;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}