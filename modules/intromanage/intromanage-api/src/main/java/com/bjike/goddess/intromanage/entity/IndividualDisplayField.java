package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 个人简介显示字段
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_individualdisplayfield")
public class IndividualDisplayField extends BaseEntity {

    /**
     * 是否显示地区
     */
    @Column(name = "ifShowArea", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示地区'")
    private Boolean ifShowArea;

    /**
     * 是否显示部门/项目组
     */
    @Column(name = "ifShowDepartment", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示部门/项目组'")
    private Boolean ifShowDepartment;

    /**
     * 是否显示姓名
     */
    @Column(name = "ifShowName", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示姓名'")
    private Boolean ifShowName;

    /**
     * 是否显示员工编号
     */
    @Column(name = "ifShowEmployeeId", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示员工编号'")
    private Boolean ifShowEmployeeId;

    /**
     * 是否显示岗位
     */
    @Column(name = "ifShowPost", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示岗位'")
    private Boolean ifShowPost;

    /**
     * 是否显示工作邮箱
     */
    @Column(name = "ifShowEmsil", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示工作邮箱'")
    private Boolean ifShowEmsil;

    /**
     * 是否显示入职日期
     */
    @Column(name = "ifShowEntryDate", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示入职日期'")
    private Boolean ifShowEntryDate;

    /**
     * 是否显示在职日期
     */
    @Column(name = "ifShowTenancyDuration", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示在职日期'")
    private Boolean ifShowTenancyDuration;

    /**
     * 是否显示转正时间
     */
    @Column(name = "ifShowPositiveTime", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示转正时间'")
    private Boolean ifShowPositiveTime;

    /**
     * 是否显示是否购买社保
     */
    @Column(name = "ifShowWhetherSocialSecurity", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示是否购买社保'")
    private Boolean ifShowWhetherSocialSecurity;

    /**
     * 是否显示社保购买时间
     */
    @Column(name = "ifShowBuySocialSecurityTime", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示社保购买时间'")
    private Boolean ifShowBuySocialSecurityTime;

    /**
     * 是否显示技能等级
     */
    @Column(name = "ifShowSkillRank", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示技能等级'")
    private Boolean ifShowSkillRank;

    /**
     * 是否显示管理等级
     */
    @Column(name = "ifShowManageGrade", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示管理等级'")
    private Boolean ifShowManageGrade;

    /**
     * 是否显示项目提成
     */
    @Column(name = "ifShowItemCommission", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示项目提成'")
    private Boolean ifShowItemCommission;

    /**
     * 是否显示管理提成
     */
    @Column(name = "ifShowManageCommission", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示管理提成'")
    private Boolean ifShowManageCommission;

    /**
     * 是否显示奖励分数
     */
    @Column(name = "ifShowAwardScore", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示奖励分数'")
    private Boolean ifShowAwardScore;

    /**
     * 是否显示处罚分数
     */
    @Column(name = "ifShowPenaltyScore", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示处罚分数'")
    private Boolean ifShowPenaltyScore;

    /**
     * 是否显示经验值
     */
    @Column(name = "ifShowEmpiricalValue", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示经验值'")
    private Boolean ifShowEmpiricalValue;

    /**
     * 是否显示补助额度
     */
    @Column(name = "ifShowSubsidyAmount", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示补助额度'")
    private Boolean ifShowSubsidyAmount;

    /**
     * 是否显示年假天数
     */
    @Column(name = "ifShowAnnualLeave", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示年假天数'")
    private Boolean ifShowAnnualLeave;

    /**
     * 是否显示个人愿景
     */
    @Column(name = "ifShowIndividualVision", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示个人愿景'")
    private Boolean ifShowIndividualVision;

    /**
     * 是否显示照片
     */
    @Column(name = "ifShowPicture", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示照片'")
    private Boolean ifShowPicture;

    /**
     * 是否显示兴趣爱好
     */
    @Column(name = "ifShowHobbies", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示兴趣爱好'")
    private Boolean ifShowHobbies;

    /**
     * 是否显示个人邮箱
     */
    @Column(name = "ifShowPersonalEmail", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示个人邮箱'")
    private Boolean ifShowPersonalEmail;

    /**
     * 是否显示出生年月
     */
    @Column(name = "ifShowDateOfBirth", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示出生年月'")
    private Boolean ifShowDateOfBirth;

    /**
     * 是否显示QQ号
     */
    @Column(name = "ifShowQqNumber", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示QQ号'")
    private Boolean ifShowQqNumber;

    /**
     * 是否显示微信号
     */
    @Column(name = "ifShowWechatId", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示微信号'")
    private Boolean ifShowWechatId;

    /**
     * 是否显示手机号
     */
    @Column(name = "ifShowMobile", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示手机号'")
    private Boolean ifShowMobile;

    /**
     * 是否显示紧急联系人
     */
    @Column(name = "ifShowEmergencyContact", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示紧急联系人'")
    private Boolean ifShowEmergencyContact;

    /**
     * 是否显示紧急联系人手机号
     */
    @Column(name = "ifShowEmergencyContactPhone", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示紧急联系人手机号'")
    private Boolean ifShowEmergencyContactPhone;

    /**
     * 是否显示学历
     */
    @Column(name = "ifShowEducation", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示学历'")
    private Boolean ifShowEducation;

    /**
     * 是否显示专业
     */
    @Column(name = "ifShowSpecialty", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示专业'")
    private Boolean ifShowSpecialty;

    /**
     * 是否显示专业学校
     */
    @Column(name = "ifShowAcademy", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示专业学校'")
    private Boolean ifShowAcademy;

    /**
     * 是否显示毕业时间
     */
    @Column(name = "ifShowGraduationTime", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示毕业时间'")
    private Boolean ifShowGraduationTime;

    /**
     * 是否显示现居住地
     */
    @Column(name = "ifShowNowResidence", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示现居住地'")
    private Boolean ifShowNowResidence;

    /**
     * 是否显示户口地址
     */
    @Column(name = "ifShowRegisteredAddress", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示户口地址'")
    private Boolean ifShowRegisteredAddress;

    /**
     * 是否显示工作年限
     */
    @Column(name = "ifShowWorkExperience", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示工作年限'")
    private Boolean ifShowWorkExperience;


    public Boolean getIfShowArea() {
        return ifShowArea;
    }

    public void setIfShowArea(Boolean ifShowArea) {
        this.ifShowArea = ifShowArea;
    }

    public Boolean getIfShowDepartment() {
        return ifShowDepartment;
    }

    public void setIfShowDepartment(Boolean ifShowDepartment) {
        this.ifShowDepartment = ifShowDepartment;
    }

    public Boolean getIfShowName() {
        return ifShowName;
    }

    public void setIfShowName(Boolean ifShowName) {
        this.ifShowName = ifShowName;
    }

    public Boolean getIfShowEmployeeId() {
        return ifShowEmployeeId;
    }

    public void setIfShowEmployeeId(Boolean ifShowEmployeeId) {
        this.ifShowEmployeeId = ifShowEmployeeId;
    }

    public Boolean getIfShowPost() {
        return ifShowPost;
    }

    public void setIfShowPost(Boolean ifShowPost) {
        this.ifShowPost = ifShowPost;
    }

    public Boolean getIfShowEmsil() {
        return ifShowEmsil;
    }

    public void setIfShowEmsil(Boolean ifShowEmsil) {
        this.ifShowEmsil = ifShowEmsil;
    }

    public Boolean getIfShowEntryDate() {
        return ifShowEntryDate;
    }

    public void setIfShowEntryDate(Boolean ifShowEntryDate) {
        this.ifShowEntryDate = ifShowEntryDate;
    }

    public Boolean getIfShowTenancyDuration() {
        return ifShowTenancyDuration;
    }

    public void setIfShowTenancyDuration(Boolean ifShowTenancyDuration) {
        this.ifShowTenancyDuration = ifShowTenancyDuration;
    }

    public Boolean getIfShowPositiveTime() {
        return ifShowPositiveTime;
    }

    public void setIfShowPositiveTime(Boolean ifShowPositiveTime) {
        this.ifShowPositiveTime = ifShowPositiveTime;
    }

    public Boolean getIfShowWhetherSocialSecurity() {
        return ifShowWhetherSocialSecurity;
    }

    public void setIfShowWhetherSocialSecurity(Boolean ifShowWhetherSocialSecurity) {
        this.ifShowWhetherSocialSecurity = ifShowWhetherSocialSecurity;
    }

    public Boolean getIfShowBuySocialSecurityTime() {
        return ifShowBuySocialSecurityTime;
    }

    public void setIfShowBuySocialSecurityTime(Boolean ifShowBuySocialSecurityTime) {
        this.ifShowBuySocialSecurityTime = ifShowBuySocialSecurityTime;
    }

    public Boolean getIfShowSkillRank() {
        return ifShowSkillRank;
    }

    public void setIfShowSkillRank(Boolean ifShowSkillRank) {
        this.ifShowSkillRank = ifShowSkillRank;
    }

    public Boolean getIfShowManageGrade() {
        return ifShowManageGrade;
    }

    public void setIfShowManageGrade(Boolean ifShowManageGrade) {
        this.ifShowManageGrade = ifShowManageGrade;
    }

    public Boolean getIfShowItemCommission() {
        return ifShowItemCommission;
    }

    public void setIfShowItemCommission(Boolean ifShowItemCommission) {
        this.ifShowItemCommission = ifShowItemCommission;
    }

    public Boolean getIfShowManageCommission() {
        return ifShowManageCommission;
    }

    public void setIfShowManageCommission(Boolean ifShowManageCommission) {
        this.ifShowManageCommission = ifShowManageCommission;
    }

    public Boolean getIfShowAwardScore() {
        return ifShowAwardScore;
    }

    public void setIfShowAwardScore(Boolean ifShowAwardScore) {
        this.ifShowAwardScore = ifShowAwardScore;
    }

    public Boolean getIfShowPenaltyScore() {
        return ifShowPenaltyScore;
    }

    public void setIfShowPenaltyScore(Boolean ifShowPenaltyScore) {
        this.ifShowPenaltyScore = ifShowPenaltyScore;
    }

    public Boolean getIfShowEmpiricalValue() {
        return ifShowEmpiricalValue;
    }

    public void setIfShowEmpiricalValue(Boolean ifShowEmpiricalValue) {
        this.ifShowEmpiricalValue = ifShowEmpiricalValue;
    }

    public Boolean getIfShowSubsidyAmount() {
        return ifShowSubsidyAmount;
    }

    public void setIfShowSubsidyAmount(Boolean ifShowSubsidyAmount) {
        this.ifShowSubsidyAmount = ifShowSubsidyAmount;
    }

    public Boolean getIfShowAnnualLeave() {
        return ifShowAnnualLeave;
    }

    public void setIfShowAnnualLeave(Boolean ifShowAnnualLeave) {
        this.ifShowAnnualLeave = ifShowAnnualLeave;
    }

    public Boolean getIfShowIndividualVision() {
        return ifShowIndividualVision;
    }

    public void setIfShowIndividualVision(Boolean ifShowIndividualVision) {
        this.ifShowIndividualVision = ifShowIndividualVision;
    }

    public Boolean getIfShowPicture() {
        return ifShowPicture;
    }

    public void setIfShowPicture(Boolean ifShowPicture) {
        this.ifShowPicture = ifShowPicture;
    }

    public Boolean getIfShowHobbies() {
        return ifShowHobbies;
    }

    public void setIfShowHobbies(Boolean ifShowHobbies) {
        this.ifShowHobbies = ifShowHobbies;
    }

    public Boolean getIfShowPersonalEmail() {
        return ifShowPersonalEmail;
    }

    public void setIfShowPersonalEmail(Boolean ifShowPersonalEmail) {
        this.ifShowPersonalEmail = ifShowPersonalEmail;
    }

    public Boolean getIfShowDateOfBirth() {
        return ifShowDateOfBirth;
    }

    public void setIfShowDateOfBirth(Boolean ifShowDateOfBirth) {
        this.ifShowDateOfBirth = ifShowDateOfBirth;
    }

    public Boolean getIfShowQqNumber() {
        return ifShowQqNumber;
    }

    public void setIfShowQqNumber(Boolean ifShowQqNumber) {
        this.ifShowQqNumber = ifShowQqNumber;
    }

    public Boolean getIfShowWechatId() {
        return ifShowWechatId;
    }

    public void setIfShowWechatId(Boolean ifShowWechatId) {
        this.ifShowWechatId = ifShowWechatId;
    }

    public Boolean getIfShowMobile() {
        return ifShowMobile;
    }

    public void setIfShowMobile(Boolean ifShowMobile) {
        this.ifShowMobile = ifShowMobile;
    }

    public Boolean getIfShowEmergencyContact() {
        return ifShowEmergencyContact;
    }

    public void setIfShowEmergencyContact(Boolean ifShowEmergencyContact) {
        this.ifShowEmergencyContact = ifShowEmergencyContact;
    }

    public Boolean getIfShowEmergencyContactPhone() {
        return ifShowEmergencyContactPhone;
    }

    public void setIfShowEmergencyContactPhone(Boolean ifShowEmergencyContactPhone) {
        this.ifShowEmergencyContactPhone = ifShowEmergencyContactPhone;
    }

    public Boolean getIfShowEducation() {
        return ifShowEducation;
    }

    public void setIfShowEducation(Boolean ifShowEducation) {
        this.ifShowEducation = ifShowEducation;
    }

    public Boolean getIfShowSpecialty() {
        return ifShowSpecialty;
    }

    public void setIfShowSpecialty(Boolean ifShowSpecialty) {
        this.ifShowSpecialty = ifShowSpecialty;
    }

    public Boolean getIfShowAcademy() {
        return ifShowAcademy;
    }

    public void setIfShowAcademy(Boolean ifShowAcademy) {
        this.ifShowAcademy = ifShowAcademy;
    }

    public Boolean getIfShowGraduationTime() {
        return ifShowGraduationTime;
    }

    public void setIfShowGraduationTime(Boolean ifShowGraduationTime) {
        this.ifShowGraduationTime = ifShowGraduationTime;
    }

    public Boolean getIfShowNowResidence() {
        return ifShowNowResidence;
    }

    public void setIfShowNowResidence(Boolean ifShowNowResidence) {
        this.ifShowNowResidence = ifShowNowResidence;
    }

    public Boolean getIfShowRegisteredAddress() {
        return ifShowRegisteredAddress;
    }

    public void setIfShowRegisteredAddress(Boolean ifShowRegisteredAddress) {
        this.ifShowRegisteredAddress = ifShowRegisteredAddress;
    }

    public Boolean getIfShowWorkExperience() {
        return ifShowWorkExperience;
    }

    public void setIfShowWorkExperience(Boolean ifShowWorkExperience) {
        this.ifShowWorkExperience = ifShowWorkExperience;
    }
}