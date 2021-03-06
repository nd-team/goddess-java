package com.bjike.goddess.contractcommunicat.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 商务洽谈业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessNegotiationTemplateExcel extends BaseBO {

    /**
     * 市场编号
     */
    @ExcelHeader(name = "市场编号")
    private String marketNum;

    /**
     * 内部项目编号
     */
    @ExcelHeader(name = "内部项目编号")
    private String projectNum;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称")
    private String innerProject;

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型")
    private String businessType;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区")
    private String area;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门")
    private String department;

    /**
     * 问题受理编号(对内)
     */
    @ExcelHeader(name = "问题受理编号(对内)")
    private String problemNum;

    /**
     * 问题归属
     */
    @ExcelHeader(name = "问题归属")
    private String problemBelong;

    /**
     * 洽谈轮次
     */
    @ExcelHeader(name = "洽谈轮次")
    private Integer rounds;

    /**
     * 计划洽谈时间
     */
    @ExcelHeader(name = "计划洽谈时间")
    private LocalDate planNegotiationTime;

    /**
     * 实际洽谈时间
     */
    @ExcelHeader(name = "实际洽谈时间")
    private LocalDate practiceNegotiationTime;

    /**
     * 洽谈人
     */
    @ExcelHeader(name = "洽谈人")
    private String discussPeople;

    /**
     * 记录人
     */
    @ExcelHeader(name = "记录人")
    private String recorder;

    /**
     * 客户编号
     */
    @ExcelHeader(name = "客户编号")
    private String customerNum;

    /**
     * 洽谈公司
     */
    @ExcelHeader(name = "洽谈公司")
    private String discussCompany;

    /**
     * 洽谈对象
     */
    @ExcelHeader(name = "洽谈对象")
    private String discussObject;

    /**
     * 洽谈方式
     */
    @ExcelHeader(name = "洽谈方式")
    private String discussWay;

    /**
     * 洽谈地点
     */
    @ExcelHeader(name = "洽谈地点")
    private String discussPlace;

    /**
     * 问题归类
     */
    @ExcelHeader(name = "问题归类")
    private String problemCategorize;

    /**
     * 洽谈内容
     */
    @ExcelHeader(name = "洽谈内容")
    private String discussContent;

    /**
     * 洽谈目的
     */
    @ExcelHeader(name = "洽谈目的")
    private String discussIdea;

    /**
     * 是否有洽谈准备
     */
    @ExcelHeader(name = "是否有洽谈准备")
    private String discussPrepare;

    /**
     * 涉及金额
     */
    @ExcelHeader(name = "涉及金额")
    private String amountInvolved;

    /**
     * 是否洽谈
     */
    @ExcelHeader(name = "是否洽谈")
    private String discuss;

    /**
     * 洽谈结果
     */
    @ExcelHeader(name = "洽谈结果")
    private String discussResult;

    /**
     * 是否达到洽谈目的
     */
    @ExcelHeader(name = "是否达到洽谈目的")
    private String attainDiscussIdea;

    /**
     * 是否有洽谈到其他问题
     */
    @ExcelHeader(name = "是否有洽谈到其他问题")
    private String discussProblem;

    /**
     * 话题记录
     */
    @ExcelHeader(name = "话题记录")
    private String subjectRecord;

    /**
     * 是否有录音
     */
    @ExcelHeader(name = "是否有录音")
    private String soundRecord;

    /**
     * 是否转入合同管理-已立项
     */
    @ExcelHeader(name = "是否转入合同管理-已立项")
    private String hasProject;

    /**
     * 是否转入合同管理-市场费用
     */
    @ExcelHeader(name = "是否转入合同管理-市场费用")
    private String marketCost;

    /**
     * 是否转换市场招待
     */
    @ExcelHeader(name = "是否转换市场招待")
    private String marketFor;

    /**
     * 是否持续跟进
     */
    @ExcelHeader(name = "是否持续跟进")
    private String continueFollowUp;

    /**
     * 是否闭环
     */
    @ExcelHeader(name = "是否闭环")
    private String closedLoop;

    /**
     * 是否需要协助
     */
    @ExcelHeader(name = "是否需要协助")
    private String needAssist;

    /**
     * 需协助部门
     */
    @ExcelHeader(name = "需协助部门")
    private String assistDept;

    /**
     * 协助内容
     */
    @ExcelHeader(name = "协助内容")
    private String assistContent;

    /**
     * 是否已发协助函
     */
    @ExcelHeader(name = "是否已发协助函")
    private String assistLetter;

    /**
     * 协助函单号
     */
    @ExcelHeader(name = "协助函单号")
    private String assistLetterNum;

    /**
     * 计划跟进时间
     */
    @ExcelHeader(name = "计划跟进时间")
    private LocalDate planFollowUp;

    /**
     * 是否产生路费
     */
    @ExcelHeader(name = "是否产生路费")
    private String produceTrip;

    /**
     * 路费
     */
    @ExcelHeader(name = "路费")
    private Double trip;


    public String getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(String marketNum) {
        this.marketNum = marketNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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

    public String getProblemNum() {
        return problemNum;
    }

    public void setProblemNum(String problemNum) {
        this.problemNum = problemNum;
    }

    public String getProblemBelong() {
        return problemBelong;
    }

    public void setProblemBelong(String problemBelong) {
        this.problemBelong = problemBelong;
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public LocalDate getPlanNegotiationTime() {
        return planNegotiationTime;
    }

    public void setPlanNegotiationTime(LocalDate planNegotiationTime) {
        this.planNegotiationTime = planNegotiationTime;
    }

    public LocalDate getPracticeNegotiationTime() {
        return practiceNegotiationTime;
    }

    public void setPracticeNegotiationTime(LocalDate practiceNegotiationTime) {
        this.practiceNegotiationTime = practiceNegotiationTime;
    }

    public String getDiscussPeople() {
        return discussPeople;
    }

    public void setDiscussPeople(String discussPeople) {
        this.discussPeople = discussPeople;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getDiscussCompany() {
        return discussCompany;
    }

    public void setDiscussCompany(String discussCompany) {
        this.discussCompany = discussCompany;
    }

    public String getDiscussObject() {
        return discussObject;
    }

    public void setDiscussObject(String discussObject) {
        this.discussObject = discussObject;
    }

    public String getDiscussWay() {
        return discussWay;
    }

    public void setDiscussWay(String discussWay) {
        this.discussWay = discussWay;
    }

    public String getDiscussPlace() {
        return discussPlace;
    }

    public void setDiscussPlace(String discussPlace) {
        this.discussPlace = discussPlace;
    }

    public String getProblemCategorize() {
        return problemCategorize;
    }

    public void setProblemCategorize(String problemCategorize) {
        this.problemCategorize = problemCategorize;
    }

    public String getDiscussContent() {
        return discussContent;
    }

    public void setDiscussContent(String discussContent) {
        this.discussContent = discussContent;
    }

    public String getDiscussIdea() {
        return discussIdea;
    }

    public void setDiscussIdea(String discussIdea) {
        this.discussIdea = discussIdea;
    }

    public String getDiscussPrepare() {
        return discussPrepare;
    }

    public void setDiscussPrepare(String discussPrepare) {
        this.discussPrepare = discussPrepare;
    }

    public String getAmountInvolved() {
        return amountInvolved;
    }

    public void setAmountInvolved(String amountInvolved) {
        this.amountInvolved = amountInvolved;
    }

    public String getDiscuss() {
        return discuss;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public String getDiscussResult() {
        return discussResult;
    }

    public void setDiscussResult(String discussResult) {
        this.discussResult = discussResult;
    }

    public String getAttainDiscussIdea() {
        return attainDiscussIdea;
    }

    public void setAttainDiscussIdea(String attainDiscussIdea) {
        this.attainDiscussIdea = attainDiscussIdea;
    }

    public String getDiscussProblem() {
        return discussProblem;
    }

    public void setDiscussProblem(String discussProblem) {
        this.discussProblem = discussProblem;
    }

    public String getSubjectRecord() {
        return subjectRecord;
    }

    public void setSubjectRecord(String subjectRecord) {
        this.subjectRecord = subjectRecord;
    }

    public String getSoundRecord() {
        return soundRecord;
    }

    public void setSoundRecord(String soundRecord) {
        this.soundRecord = soundRecord;
    }

    public String getHasProject() {
        return hasProject;
    }

    public void setHasProject(String hasProject) {
        this.hasProject = hasProject;
    }

    public String getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(String marketCost) {
        this.marketCost = marketCost;
    }

    public String getMarketFor() {
        return marketFor;
    }

    public void setMarketFor(String marketFor) {
        this.marketFor = marketFor;
    }

    public String getContinueFollowUp() {
        return continueFollowUp;
    }

    public void setContinueFollowUp(String continueFollowUp) {
        this.continueFollowUp = continueFollowUp;
    }

    public String getClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(String closedLoop) {
        this.closedLoop = closedLoop;
    }

    public String getNeedAssist() {
        return needAssist;
    }

    public void setNeedAssist(String needAssist) {
        this.needAssist = needAssist;
    }

    public String getAssistDept() {
        return assistDept;
    }

    public void setAssistDept(String assistDept) {
        this.assistDept = assistDept;
    }

    public String getAssistContent() {
        return assistContent;
    }

    public void setAssistContent(String assistContent) {
        this.assistContent = assistContent;
    }

    public String getAssistLetter() {
        return assistLetter;
    }

    public void setAssistLetter(String assistLetter) {
        this.assistLetter = assistLetter;
    }

    public String getAssistLetterNum() {
        return assistLetterNum;
    }

    public void setAssistLetterNum(String assistLetterNum) {
        this.assistLetterNum = assistLetterNum;
    }

    public LocalDate getPlanFollowUp() {
        return planFollowUp;
    }

    public void setPlanFollowUp(LocalDate planFollowUp) {
        this.planFollowUp = planFollowUp;
    }

    public String getProduceTrip() {
        return produceTrip;
    }

    public void setProduceTrip(String produceTrip) {
        this.produceTrip = produceTrip;
    }

    public Double getTrip() {
        return trip;
    }

    public void setTrip(Double trip) {
        this.trip = trip;
    }
}