package com.bjike.goddess.regularization.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.regularization.type.Assess;
import com.bjike.goddess.regularization.type.SexType;
import com.bjike.goddess.regularization.type.StaffStatus;

import java.time.LocalDate;


/**
 * 转正人员信息导入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 02:20 ]
 * @Description: [ 转正人员信息导入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TransferInfoTemple extends BaseTO {

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
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String jobs;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名",notNull = true)
    private String name;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号",notNull = true)
    private String empNo;

    /**
     * 性别
     */
    @ExcelHeader(name = "性别",notNull = true)
    private String gender;

    /**
     * 学历
     */
    @ExcelHeader(name = "学历",notNull = true)
    private String education;

    /**
     * 专业
     */
    @ExcelHeader(name = "专业",notNull = true)
    private String profession;

    /**
     * 工作年限
     */
    @ExcelHeader(name = "工作年限",notNull = true)
    private Double workingYear;

    /**
     * 入职时间
     */
    @ExcelHeader(name = "入职时间",notNull = true)
    private String hiredate;

    /**
     * 同意试用期时长
     */
    @ExcelHeader(name = "同意试用期时长")
    private String probationaryPer;

    /**
     * 三天是否跟进
     */
    @ExcelHeader(name = "三天是否跟进")
    private String threeFollow;

    /**
     * 三天跟进收集的意见
     */
    @ExcelHeader(name = "三天跟进收集的意见")
    private String threeFollowOpinion;

    /**
     * 一周内是否跟进
     */
    @ExcelHeader(name = "一周内是否跟进")
    private String weekFollow;

    /**
     * 一周跟进收集的意见
     */
    @ExcelHeader(name = "一周跟进收集的意见")
    private String weekFollowOpinion;

    /**
     * 一个月是否跟进
     */
    @ExcelHeader(name = "一个月是否跟进")
    private String monthFollow;

    /**
     * 一个月跟进收集的意见
     */
    @ExcelHeader(name = "一个月跟进收集的意见")
    private String monthFollowOpinion;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态",notNull = true)
    private String staffStatus;

    /**
     * 转正申请日期
     */
    @ExcelHeader(name = "转正申请日期")
    private String applyDate;

    /**
     * 截止目前试用期时长
     */
    @ExcelHeader(name = "截止目前试用期时长")
    private Integer asProbationLength;

    /**
     * 确定事项是否确认
     */
    @ExcelHeader(name = "确定事项是否确认")
    private String confirmEvent;

    /**
     * 确认人
     */
    @ExcelHeader(name = "确认人")
    private String confirmPeople;

    /**
     * 截至申请日期考勤情况
     */
    @ExcelHeader(name = "截至申请日期考勤情况")
    private String applyDateAtten;

    /**
     * 奖励处罚情况
     */
    @ExcelHeader(name = "奖励处罚情况")
    private String rewardPunOpinion;

    /**
     * 转正后主项技能
     */
    @ExcelHeader(name = "转正后主项技能")
    private String additionalSkill;

    /**
     * 转正后主项技能等级
     */
    @ExcelHeader(name = "转正后主项技能等级")
    private String additionalSkillGrade;

    /**
     * 转正后小项技能
     */
    @ExcelHeader(name = "转正后小项技能")
    private String eventsSkill;

    /**
     * 转正后小项技能等级
     */
    @ExcelHeader(name = "转正后小项技能等级")
    private String eventsSkillGrade;

    /**
     * 是否符合人员编制
     */
    @ExcelHeader(name = "是否符合人员编制")
    private String conformStaffing;

    /**
     * 收入与成本分析意见
     */
    @ExcelHeader(name = "收入与成本分析意见")
    private String incomeCostOpinion;

    /**
     * 模块负责人
     */
    @ExcelHeader(name = "模块负责人")
    private String moduleLeader;

    /**
     * 模块负责人意见
     */
    @ExcelHeader(name = "模块负责人意见")
    private String moduleLeaderOpinion;

    /**
     * 项目经理
     */
    @ExcelHeader(name = "项目经理")
    private String proManage;

    /**
     * 项目经理意见
     */
    @ExcelHeader(name = "项目经理意见")
    private String proManageOpinion;

    /**
     * 总经理
     */
    @ExcelHeader(name = "总经理")
    private String generManage;

    /**
     * 总经理意见
     */
    @ExcelHeader(name = "总经理意见")
    private String generManageOpinion;

    /**
     * 是否同意转正
     */
    @ExcelHeader(name = "是否同意转正")
    private String consentPositive;

    /**
     * 转正开始日期
     */
    @ExcelHeader(name = "转正开始日期")
    private String positiveStartDate;

    /**
     * 转正是否通过
     */
    @ExcelHeader(name = "转正是否通过")
    private String positiveThrough;

    /**
     * 实际试用期时长
     */
    @ExcelHeader(name = "实际试用期时长")
    private Integer praProbationaryPer;

    /**
     * 转正情况面谈人
     */
    @ExcelHeader(name = "转正情况面谈人")
    private String interviewPeper;

    /**
     * 面谈内容
     */
    @ExcelHeader(name = "面谈内容")
    private String interviewContent;

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

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Double getWorkingYear() {
        return workingYear;
    }

    public void setWorkingYear(Double workingYear) {
        this.workingYear = workingYear;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getProbationaryPer() {
        return probationaryPer;
    }

    public void setProbationaryPer(String probationaryPer) {
        this.probationaryPer = probationaryPer;
    }

    public String getThreeFollow() {
        return threeFollow;
    }

    public void setThreeFollow(String threeFollow) {
        this.threeFollow = threeFollow;
    }

    public String getThreeFollowOpinion() {
        return threeFollowOpinion;
    }

    public void setThreeFollowOpinion(String threeFollowOpinion) {
        this.threeFollowOpinion = threeFollowOpinion;
    }

    public String getWeekFollow() {
        return weekFollow;
    }

    public void setWeekFollow(String weekFollow) {
        this.weekFollow = weekFollow;
    }

    public String getWeekFollowOpinion() {
        return weekFollowOpinion;
    }

    public void setWeekFollowOpinion(String weekFollowOpinion) {
        this.weekFollowOpinion = weekFollowOpinion;
    }

    public String getMonthFollow() {
        return monthFollow;
    }

    public void setMonthFollow(String monthFollow) {
        this.monthFollow = monthFollow;
    }

    public String getMonthFollowOpinion() {
        return monthFollowOpinion;
    }

    public void setMonthFollowOpinion(String monthFollowOpinion) {
        this.monthFollowOpinion = monthFollowOpinion;
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getAsProbationLength() {
        return asProbationLength;
    }

    public void setAsProbationLength(Integer asProbationLength) {
        this.asProbationLength = asProbationLength;
    }

    public String getConfirmEvent() {
        return confirmEvent;
    }

    public void setConfirmEvent(String confirmEvent) {
        this.confirmEvent = confirmEvent;
    }

    public String getConfirmPeople() {
        return confirmPeople;
    }

    public void setConfirmPeople(String confirmPeople) {
        this.confirmPeople = confirmPeople;
    }

    public String getApplyDateAtten() {
        return applyDateAtten;
    }

    public void setApplyDateAtten(String applyDateAtten) {
        this.applyDateAtten = applyDateAtten;
    }

    public String getRewardPunOpinion() {
        return rewardPunOpinion;
    }

    public void setRewardPunOpinion(String rewardPunOpinion) {
        this.rewardPunOpinion = rewardPunOpinion;
    }

    public String getAdditionalSkill() {
        return additionalSkill;
    }

    public void setAdditionalSkill(String additionalSkill) {
        this.additionalSkill = additionalSkill;
    }

    public String getAdditionalSkillGrade() {
        return additionalSkillGrade;
    }

    public void setAdditionalSkillGrade(String additionalSkillGrade) {
        this.additionalSkillGrade = additionalSkillGrade;
    }

    public String getEventsSkill() {
        return eventsSkill;
    }

    public void setEventsSkill(String eventsSkill) {
        this.eventsSkill = eventsSkill;
    }

    public String getEventsSkillGrade() {
        return eventsSkillGrade;
    }

    public void setEventsSkillGrade(String eventsSkillGrade) {
        this.eventsSkillGrade = eventsSkillGrade;
    }

    public String getConformStaffing() {
        return conformStaffing;
    }

    public void setConformStaffing(String conformStaffing) {
        this.conformStaffing = conformStaffing;
    }

    public String getIncomeCostOpinion() {
        return incomeCostOpinion;
    }

    public void setIncomeCostOpinion(String incomeCostOpinion) {
        this.incomeCostOpinion = incomeCostOpinion;
    }

    public String getModuleLeader() {
        return moduleLeader;
    }

    public void setModuleLeader(String moduleLeader) {
        this.moduleLeader = moduleLeader;
    }

    public String getModuleLeaderOpinion() {
        return moduleLeaderOpinion;
    }

    public void setModuleLeaderOpinion(String moduleLeaderOpinion) {
        this.moduleLeaderOpinion = moduleLeaderOpinion;
    }

    public String getProManage() {
        return proManage;
    }

    public void setProManage(String proManage) {
        this.proManage = proManage;
    }

    public String getProManageOpinion() {
        return proManageOpinion;
    }

    public void setProManageOpinion(String proManageOpinion) {
        this.proManageOpinion = proManageOpinion;
    }

    public String getGenerManage() {
        return generManage;
    }

    public void setGenerManage(String generManage) {
        this.generManage = generManage;
    }

    public String getGenerManageOpinion() {
        return generManageOpinion;
    }

    public void setGenerManageOpinion(String generManageOpinion) {
        this.generManageOpinion = generManageOpinion;
    }

    public String getConsentPositive() {
        return consentPositive;
    }

    public void setConsentPositive(String consentPositive) {
        this.consentPositive = consentPositive;
    }

    public String getPositiveStartDate() {
        return positiveStartDate;
    }

    public void setPositiveStartDate(String positiveStartDate) {
        this.positiveStartDate = positiveStartDate;
    }

    public String getPositiveThrough() {
        return positiveThrough;
    }

    public void setPositiveThrough(String positiveThrough) {
        this.positiveThrough = positiveThrough;
    }

    public Integer getPraProbationaryPer() {
        return praProbationaryPer;
    }

    public void setPraProbationaryPer(Integer praProbationaryPer) {
        this.praProbationaryPer = praProbationaryPer;
    }

    public String getInterviewPeper() {
        return interviewPeper;
    }

    public void setInterviewPeper(String interviewPeper) {
        this.interviewPeper = interviewPeper;
    }

    public String getInterviewContent() {
        return interviewContent;
    }

    public void setInterviewContent(String interviewContent) {
        this.interviewContent = interviewContent;
    }
}