package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.regularization.type.Assess;
import com.bjike.goddess.regularization.type.StaffStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 转正人员信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 02:20 ]
 * @Description: [ 转正人员信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TransferInfoTO extends BaseTO {
    public interface testFollowUp{

    }
    public interface testWelfareAssess{

    }
    public interface testPlanAssess{

    }
    public interface testBudgetAssess{

    }
    public interface testModuleRespon{

    }
    public interface testProjectManage{

    }
    public interface testGenManage{

    }
    public interface testInterview{

    }
    /**
     * 三天是否跟进
     */
    @NotNull(message = "三天是否跟进不能为空",groups = {TransferInfoTO.testFollowUp.class})
    private Assess threeFollow;

    /**
     * 三天跟进收集的意见
     */
    private String threeFollowOpinion;

    /**
     * 一周内是否跟进
     */
    @NotNull(message = "一周内是否跟进不能为空",groups = {TransferInfoTO.testFollowUp.class})
    private Assess weekFollow;

    /**
     * 一周跟进收集的意见
     */
    private String weekFollowOpinion;

    /**
     * 一个月是否跟进
     */
    @NotNull(message = "一个月是否跟进不能为空",groups = {TransferInfoTO.testFollowUp.class})
    private Assess monthFollow;

    /**
     * 一个月跟进收集的意见
     */
    private String monthFollowOpinion;

    /**
     * 截至申请日期考勤情况
     */
    @NotBlank(message = "截至申请日期考勤情况不能为空",groups = {TransferInfoTO.testWelfareAssess.class})
    private String applyDateAtten;

    /**
     * 奖励处罚情况
     */
    @NotBlank(message = "奖励处罚情况不能为空",groups = {TransferInfoTO.testWelfareAssess.class})
    private String rewardPunOpinion;

    /**
     * 转正后主项技能
     */
    @NotBlank(message = "转正后主项技能不能为空",groups = {TransferInfoTO.testPlanAssess.class})
    private String additionalSkill;

    /**
     * 转正后主项技能等级
     */
    @NotBlank(message = "转正后主项技能等级不能为空",groups = {TransferInfoTO.testPlanAssess.class})
    private String additionalSkillGrade;

    /**
     * 转正后小项技能
     */
    @NotBlank(message = "转正后小项技能不能为空",groups = {TransferInfoTO.testPlanAssess.class})
    private String eventsSkill;

    /**
     * 转正后小项技能等级
     */
    @NotBlank(message = "转正后小项技能等级不能为空",groups = {TransferInfoTO.testPlanAssess.class})
    private String eventsSkillGrade;

    /**
     * 是否符合人员编制
     */
    @NotNull(message = "是否符合人员编制不能为空",groups = {TransferInfoTO.testPlanAssess.class})
    private Boolean conformStaffing;

    /**
     * 收入与成本分析意见
     */
    @NotBlank(message = "收入与成本分析意见不能为空",groups = {TransferInfoTO.testBudgetAssess.class})
    private String incomeCostOpinion;

    /**
     * 模块负责人
     */
    @NotBlank(message = "模块负责人不能为空",groups = {TransferInfoTO.testModuleRespon.class})
    private String moduleLeader;

    /**
     * 模块负责人意见
     */
    @NotBlank(message = "模块负责人意见不能为空",groups = {TransferInfoTO.testModuleRespon.class})
    private String moduleLeaderOpinion;

    /**
     * 项目经理
     */
    @NotBlank(message = "项目经理不能为空",groups = {TransferInfoTO.testProjectManage.class})
    private String proManage;

    /**
     * 项目经理意见
     */
    @NotBlank(message = "项目经理意见不能为空",groups = {TransferInfoTO.testProjectManage.class})
    private String proManageOpinion;

    /**
     * 总经理
     */
    @NotBlank(message = "项目经理意见不能为空",groups = {TransferInfoTO.testGenManage.class})
    private String generManage;

    /**
     * 总经理意见
     */
    private String generManageOpinion;

    /**
     * 是否同意转正
     */
    @NotNull(message = "是否同意转正不能为空",groups = {TransferInfoTO.testGenManage.class})
    private Boolean consentPositive;

    /**
     * 转正开始日期
     */
    @NotBlank(message = "转正开始日期不能为空",groups = {TransferInfoTO.testGenManage.class})
    private String positiveStartDate;

    /**
     * 转正是否通过
     */
    @NotNull(message = "转正是否通过不能为空",groups = {TransferInfoTO.testGenManage.class})
    private Boolean positiveThrough;

    /**
     * 转正情况面谈人
     */
    @NotNull(message = "转正情况面谈人不能为空",groups = {TransferInfoTO.testInterview.class})
    private String interviewPeper;

    /**
     * 面谈内容
     */
    @NotBlank(message = "面谈内容不能为空",groups = {TransferInfoTO.testInterview.class})
    private String interviewContent;

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