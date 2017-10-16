package com.bjike.goddess.intromanage.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;


/**
 * 个人简介导入
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndividualResumeExcel extends BaseTO {

    /**
     * 序号
     */
    @ExcelHeader(name = "序号",notNull = true)
    private Integer seqNum;
    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 部门/项目组
     */
    @ExcelHeader(name = "部门/项目组",notNull = true)
    private String department;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名",notNull = true)
    private String name;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号",notNull = true)
    private String employeeId;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String post;

    /**
     * 工作邮箱
     */
    @ExcelHeader(name = "工作邮箱",notNull = true)
    private String eMsil;

    /**
     * 入职日期
     */
    @ExcelHeader(name = "入职日期",notNull = true)
    private LocalDate entryDate;

    /**
     * 在职时间(月)
     */
    @ExcelHeader(name = "在职时间(月)",notNull = true)
    private String tenancyDuration;

    /**
     * 转正时间
     */
    @ExcelHeader(name = "转正时间",notNull = true)
    private LocalDate positiveTime;

    /**
     * 是否购买社保
     */
    @ExcelHeader(name = "是否购买社保",notNull = true)
    private String whetherSocialSecurity;

    /**
     * 社保购买时间
     */
    @ExcelHeader(name = "社保购买时间")
    private LocalDate buySocialSecurityTime;

    /**
     * 技能等级
     */
    @ExcelHeader(name = "技能等级")
    private String skillRank;

    /**
     * 管理等级
     */
    @ExcelHeader(name = "管理等级")
    private String manageGrade;

    /**
     * 项目提成
     */
    @ExcelHeader(name = "项目提成")
    private String itemCommission;

    /**
     * 管理提成
     */
    @ExcelHeader(name = "管理提成")
    private String manageCommission;

    /**
     * 奖励分数
     */
    @ExcelHeader(name = "奖励分数")
    private Integer awardScore;

    /**
     * 处罚分数
     */
    @ExcelHeader(name = "处罚分数")
    private Integer penaltyScore;

    /**
     * 经验值
     */
    @ExcelHeader(name = "经验值")
    private String empiricalValue;

    /**
     * 补助额度
     */
    @ExcelHeader(name = "补助额度")
    private String subsidyAmount;

    /**
     * 年假天数
     */
    @ExcelHeader(name = "年假天数")
    private Double annualLeave;

    /**
     * 个人愿景
     */
    @ExcelHeader(name = "个人愿景")
    private String individualVision;

    /**
     * 照片
     */
    @ExcelHeader(name = "照片")
    private String picture;

    /**
     * 兴趣爱好
     */
    @ExcelHeader(name = "兴趣爱好")
    private String hobbies;

    /**
     * 个人邮箱
     */
    @ExcelHeader(name = "个人邮箱")
    private String personalEmail;

    /**
     * 出生年月
     */
    @ExcelHeader(name = "出生年月")
    private String dateOfBirth;

    /**
     * QQ号
     */
    @ExcelHeader(name = "QQ号")
    private String qqNumber;

    /**
     * 微信号
     */
    @ExcelHeader(name = "微信号")
    private String wechatId;

    /**
     * 手机号
     */
    @ExcelHeader(name = "手机号")
    private String mobile;

    /**
     * 紧急联系人
     */
    @ExcelHeader(name = "紧急联系人")
    private String emergencyContact;

    /**
     * 紧急联系人手机号
     */
    @ExcelHeader(name = "紧急联系人手机号")
    private String emergencyContactPhone;

    /**
     * 学历
     */
    @ExcelHeader(name = "学历")
    private String education;

    /**
     * 专业
     */
    @ExcelHeader(name = "专业")
    private String specialty;

    /**
     * 专业学校
     */
    @ExcelHeader(name = "专业学校")
    private String academy;

    /**
     * 毕业时间
     */
    @ExcelHeader(name = "毕业时间")
    private String graduationTime;

    /**
     * 现居住地
     */
    @ExcelHeader(name = "现居住地")
    private String nowResidence;

    /**
     * 户口地址
     */
    @ExcelHeader(name = "户口地址")
    private String registeredAddress;

    /**
     * 工作年限
     */
    @ExcelHeader(name = "工作年限")
    private String workExperience;
    /**
     * 员工状态
     */
    @ExcelHeader(name = "员工状态")
    private String staffStatus;

    /**
     * 奖励名称
     */
    @ExcelHeader(name = "奖励名称")
    private String rewardsName;

    /**
     * 奖品
     */
    @ExcelHeader(name = "奖品")
    private String prize;

    /**
     * 奖金
     */
    @ExcelHeader(name = "奖金")
    private String bonus;

    /**
     * 荣誉名称
     */
    @ExcelHeader(name = "荣誉名称")
    private String honorName;

    /**
     * 荣誉等级
     */
    @ExcelHeader(name = "荣誉等级")
    private String honorGrade;

    /**
     * 公司补助
     */
    @ExcelHeader(name = "公司补助")
    private String firmSubsidy;

    /**
     * 教育地址
     */
    @ExcelHeader(name = "教育地址")
    private String educatAddress;

    /**
     * 培训经历
     */
    @ExcelHeader(name = "培训经历")
    private String trainingExperience;

    /**
     * 曾经参与的组织与活动
     */
    @ExcelHeader(name = "曾经参与的组织与活动")
    private String participatedActivity;

    /**
     * 项目经历
     */
    @ExcelHeader(name = "项目经历")
    private String projectExperience;

    /**
     * 获取的专业证书
     */
    @ExcelHeader(name = "获取的专业证书")
    private String certificateTitle;

    /**
     * 获取证书的时间
     */
    @ExcelHeader(name = "获取证书的时间")
    private String certificateTime;

    /**
     * 证书编号
     */
    @ExcelHeader(name = "证书编号")
    private String certificateNo;

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
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

    public String geteMsil() {
        return eMsil;
    }

    public void seteMsil(String eMsil) {
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

    public String getWhetherSocialSecurity() {
        return whetherSocialSecurity;
    }

    public void setWhetherSocialSecurity(String whetherSocialSecurity) {
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

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getRewardsName() {
        return rewardsName;
    }

    public void setRewardsName(String rewardsName) {
        this.rewardsName = rewardsName;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public String getHonorGrade() {
        return honorGrade;
    }

    public void setHonorGrade(String honorGrade) {
        this.honorGrade = honorGrade;
    }

    public String getFirmSubsidy() {
        return firmSubsidy;
    }

    public void setFirmSubsidy(String firmSubsidy) {
        this.firmSubsidy = firmSubsidy;
    }

    public String getEducatAddress() {
        return educatAddress;
    }

    public void setEducatAddress(String educatAddress) {
        this.educatAddress = educatAddress;
    }

    public String getTrainingExperience() {
        return trainingExperience;
    }

    public void setTrainingExperience(String trainingExperience) {
        this.trainingExperience = trainingExperience;
    }

    public String getParticipatedActivity() {
        return participatedActivity;
    }

    public void setParticipatedActivity(String participatedActivity) {
        this.participatedActivity = participatedActivity;
    }

    public String getProjectExperience() {
        return projectExperience;
    }

    public void setProjectExperience(String projectExperience) {
        this.projectExperience = projectExperience;
    }

    public String getCertificateTitle() {
        return certificateTitle;
    }

    public void setCertificateTitle(String certificateTitle) {
        this.certificateTitle = certificateTitle;
    }

    public String getCertificateTime() {
        return certificateTime;
    }

    public void setCertificateTime(String certificateTime) {
        this.certificateTime = certificateTime;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }
}