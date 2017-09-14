package com.bjike.goddess.regularization.vo;

import com.bjike.goddess.regularization.type.Assess;
import com.bjike.goddess.regularization.type.StaffStatus;

/**
 * 转正人员信息表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 02:20 ]
 * @Description: [ 转正人员信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TransferInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 岗位
     */
    private String jobs;

    /**
     * 姓名
     */
    private String name;

    /**
     * 员工编号
     */
    private String empNo;

    /**
     * 性别
     */
    private String gender;

    /**
     * 学历
     */
    private String education;

    /**
     * 专业
     */
    private String profession;

    /**
     * 工作年限
     */
    private Double workingYear;

    /**
     * 入职时间
     */
    private String hiredate;

    /**
     * 同意试用期时长
     */
    private String probationaryPer;

    /**
     * 试用期到期时间
     */
    private String probationDue;

    /**
     * 三天是否跟进
     */
    private Assess threeFollow;

    /**
     * 三天跟进收集的意见
     */
    private String threeFollowOpinion;

    /**
     * 一周内是否跟进
     */
    private Assess weekFollow;

    /**
     * 一周跟进收集的意见
     */
    private String weekFollowOpinion;

    /**
     * 一个月是否跟进
     */
    private Assess monthFollow;

    /**
     * 一个月跟进收集的意见
     */
    private String monthFollowOpinion;

    /**
     * 状态
     */
    private StaffStatus staffStatus;

    /**
     * 转正申请日期
     */
    private String applyDate;

    /**
     * 截止目前试用期时长
     */
    private String asProbationLength;

    /**
     * 确定事项是否确认
     */
    private Boolean confirmEvent;

    /**
     * 确认人
     */
    private String confirmPeople;

    /**
     * 截至申请日期考勤情况
     */
    private String applyDateAtten;

    /**
     * 奖励处罚情况
     */
    private String rewardPunOpinion;

    /**
     * 转正后主项技能
     */
    private String additionalSkill;

    /**
     * 转正后主项技能等级
     */
    private String additionalSkillGrade;

    /**
     * 转正后小项技能
     */
    private String eventsSkill;

    /**
     * 转正后小项技能等级
     */
    private String eventsSkillGrade;

    /**
     * 是否符合人员编制
     */
    private Boolean conformStaffing;

    /**
     * 收入与成本分析意见
     */
    private String incomeCostOpinion;

    /**
     * 模块负责人
     */
    private String moduleLeader;

    /**
     * 模块负责人意见
     */
    private String moduleLeaderOpinion;

    /**
     * 项目经理
     */
    private String proManage;

    /**
     * 项目经理意见
     */
    private String proManageOpinion;

    /**
     * 总经理
     */
    private String generManage;

    /**
     * 总经理意见
     */
    private String generManageOpinion;

    /**
     * 是否同意转正
     */
    private Boolean consentPositive;

    /**
     * 转正开始日期
     */
    private String positiveStartDate;

    /**
     * 转正是否通过
     */
    private Boolean positiveThrough;

    /**
     * 实际试用期时长
     */
    private Integer praProbationaryPer;

    /**
     * 转正情况面谈人
     */
    private String interviewPeper;

    /**
     * 面谈内容
     */
    private String interviewContent;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProbationDue() {
        return probationDue;
    }

    public void setProbationDue(String probationDue) {
        this.probationDue = probationDue;
    }

    public Assess getThreeFollow() {
        return threeFollow;
    }

    public void setThreeFollow(Assess threeFollow) {
        this.threeFollow = threeFollow;
    }

    public String getThreeFollowOpinion() {
        return threeFollowOpinion;
    }

    public void setThreeFollowOpinion(String threeFollowOpinion) {
        this.threeFollowOpinion = threeFollowOpinion;
    }

    public Assess getWeekFollow() {
        return weekFollow;
    }

    public void setWeekFollow(Assess weekFollow) {
        this.weekFollow = weekFollow;
    }

    public String getWeekFollowOpinion() {
        return weekFollowOpinion;
    }

    public void setWeekFollowOpinion(String weekFollowOpinion) {
        this.weekFollowOpinion = weekFollowOpinion;
    }

    public Assess getMonthFollow() {
        return monthFollow;
    }

    public void setMonthFollow(Assess monthFollow) {
        this.monthFollow = monthFollow;
    }

    public String getMonthFollowOpinion() {
        return monthFollowOpinion;
    }

    public void setMonthFollowOpinion(String monthFollowOpinion) {
        this.monthFollowOpinion = monthFollowOpinion;
    }

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getAsProbationLength() {
        return asProbationLength;
    }

    public void setAsProbationLength(String asProbationLength) {
        this.asProbationLength = asProbationLength;
    }

    public Boolean getConfirmEvent() {
        return confirmEvent;
    }

    public void setConfirmEvent(Boolean confirmEvent) {
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

    public Boolean getConformStaffing() {
        return conformStaffing;
    }

    public void setConformStaffing(Boolean conformStaffing) {
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

    public Boolean getConsentPositive() {
        return consentPositive;
    }

    public void setConsentPositive(Boolean consentPositive) {
        this.consentPositive = consentPositive;
    }

    public String getPositiveStartDate() {
        return positiveStartDate;
    }

    public void setPositiveStartDate(String positiveStartDate) {
        this.positiveStartDate = positiveStartDate;
    }

    public Boolean getPositiveThrough() {
        return positiveThrough;
    }

    public void setPositiveThrough(Boolean positiveThrough) {
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