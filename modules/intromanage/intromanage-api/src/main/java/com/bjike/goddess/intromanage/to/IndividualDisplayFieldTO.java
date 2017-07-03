package com.bjike.goddess.intromanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 个人简介显示字段
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndividualDisplayFieldTO extends BaseTO {

    /**
     * 是否显示地区
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示地区不能为空")
    private Boolean ifShowArea;

    /**
     * 是否显示部门/项目组
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示部门/项目组不能为空")
    private Boolean ifShowDepartment;

    /**
     * 是否显示姓名
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示姓名不能为空")
    private Boolean ifShowName;

    /**
     * 是否显示员工编号
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示员工编号不能为空")
    private Boolean ifShowEmployeeId;

    /**
     * 是否显示岗位
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示岗位不能为空")
    private Boolean ifShowPost;

    /**
     * 是否显示工作邮箱
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示工作邮箱不能为空")
    private Boolean ifShowEmsil;

    /**
     * 是否显示入职日期
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示入职日期不能为空")
    private Boolean ifShowEntryDate;

    /**
     * 是否显示在职日期
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示在职日期不能为空")
    private Boolean ifShowTenancyDuration;

    /**
     * 是否显示转正时间
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示转正时间不能为空")
    private Boolean ifShowPositiveTime;

    /**
     * 是否显示是否购买社保
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示是否购买社保不能为空")
    private Boolean ifShowWhetherSocialSecurity;

    /**
     * 是否显示社保购买时间
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示社保购买时间不能为空")
    private Boolean ifShowBuySocialSecurityTime;

    /**
     * 是否显示技能等级
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示技能等级不能为空")
    private Boolean ifShowSkillRank;

    /**
     * 是否显示管理等级
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示管理等级不能为空")
    private Boolean ifShowManageGrade;

    /**
     * 是否显示项目提成
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示项目提成不能为空")
    private Boolean ifShowItemCommission;

    /**
     * 是否显示管理提成
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示管理提成不能为空")
    private Boolean ifShowManageCommission;

    /**
     * 是否显示奖励分数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示奖励分数不能为空")
    private Boolean ifShowAwardScore;

    /**
     * 是否显示处罚分数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示处罚分数不能为空")
    private Boolean ifShowPenaltyScore;

    /**
     * 是否显示经验值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示经验值不能为空")
    private Boolean ifShowEmpiricalValue;

    /**
     * 是否显示补助额度
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示补助额度不能为空")
    private Boolean ifShowSubsidyAmount;

    /**
     * 是否显示年假天数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示年假天数不能为空")
    private Boolean ifShowAnnualLeave;

    /**
     * 是否显示个人愿景
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示个人愿景不能为空")
    private Boolean ifShowIndividualVision;

    /**
     * 是否显示照片
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示照片不能为空")
    private Boolean ifShowPicture;

    /**
     * 是否显示兴趣爱好
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示兴趣爱好不能为空")
    private Boolean ifShowHobbies;

    /**
     * 是否显示个人邮箱
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示个人邮箱不能为空")
    private Boolean ifShowPersonalEmail;

    /**
     * 是否显示出生年月
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示出生年月不能为空")
    private Boolean ifShowDateOfBirth;

    /**
     * 是否显示QQ号
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示QQ号不能为空")
    private Boolean ifShowQqNumber;

    /**
     * 是否显示微信号
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示微信号不能为空")
    private Boolean ifShowWechatId;

    /**
     * 是否显示手机号
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示手机号不能为空")
    private Boolean ifShowMobile;

    /**
     * 是否显示紧急联系人
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示紧急联系人不能为空")
    private Boolean ifShowEmergencyContact;

    /**
     * 是否显示紧急联系人手机号
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示紧急联系人手机号不能为空")
    private Boolean ifShowEmergencyContactPhone;

    /**
     * 是否显示学历
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示学历不能为空")
    private Boolean ifShowEducation;

    /**
     * 是否显示专业
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示专业不能为空")
    private Boolean ifShowSpecialty;

    /**
     * 是否显示专业学校
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示专业学校不能为空")
    private Boolean ifShowAcademy;

    /**
     * 是否显示毕业时间
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示毕业时间不能为空")
    private Boolean ifShowGraduationTime;

    /**
     * 是否显示现居住地
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示现居住地不能为空")
    private Boolean ifShowNowResidence;

    /**
     * 是否显示户口地址
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示户口地址不能为空")
    private Boolean ifShowRegisteredAddress;

    /**
     * 是否显示工作年限
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示工作年限不能为空")
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