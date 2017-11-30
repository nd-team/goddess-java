package com.bjike.goddess.contractcommunicat.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 商务洽谈
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contractcommunicat_businessnegotiation")
public class BusinessNegotiation extends BaseEntity {

    /**
     * 市场编号
     */
    @Column(name = "marketNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '市场编号'")
    private String marketNum;

    /**
     * 内部项目编号
     */
    @Column(name = "projectNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目编号'")
    private String projectNum;

    /**
     * 内部项目名称
     */
    @Column(name = "innerProject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerProject;

    /**
     * 业务类型
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 问题受理编号(对内)
     */
    @Column(name = "problemNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题受理编号(对内)'")
    private String problemNum;

    /**
     * 问题归属
     */
    @Column(name = "problemBelong", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题归属'")
    private String problemBelong;

    /**
     * 洽谈轮次
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈轮次'")
    private Integer rounds;

    /**
     * 计划洽谈时间
     */
    @Column(name = "planNegotiationTime", nullable = false, columnDefinition = "DATE   COMMENT '计划洽谈时间'")
    private LocalDate planNegotiationTime;

    /**
     * 实际洽谈时间
     */
    @Column(name = "practiceNegotiationTime", nullable = false, columnDefinition = "DATE   COMMENT '实际洽谈时间'")
    private LocalDate practiceNegotiationTime;

    /**
     * 洽谈人
     */
    @Column(name = "discussPeople", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈人'")
    private String discussPeople;

    /**
     * 记录人
     */
    @Column(name = "recorder", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '记录人'")
    private String recorder;

    /**
     * 客户编号
     */
    @Column(name = "customerNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户编号'")
    private String customerNum;

    /**
     * 洽谈公司
     */
    @Column(name = "discussCompany", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈公司'")
    private String discussCompany;

    /**
     * 洽谈对象
     */
    @Column(name = "discussObject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈对象'")
    private String discussObject;

    /**
     * 洽谈方式
     */
    @Column(name = "discussWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈方式'")
    private String discussWay;

    /**
     * 洽谈地点
     */
    @Column(name = "discussPlace", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈地点'")
    private String discussPlace;

    /**
     * 问题归类
     */
    @Column(name = "problemCategorize", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题归类'")
    private String problemCategorize;

    /**
     * 洽谈内容
     */
    @Column(name = "discussContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈内容'")
    private String discussContent;

    /**
     * 洽谈目的
     */
    @Column(name = "discussIdea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈目的'")
    private String discussIdea;

    /**
     * 是否有洽谈准备
     */
    @Column(name = "is_discussPrepare", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有洽谈准备'", insertable = false)
    private Boolean discussPrepare;

    /**
     * 涉及金额
     */
    @Column(name = "amountInvolved", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '涉及金额'")
    private String amountInvolved;

    /**
     * 是否洽谈
     */
    @Column(name = "is_discuss", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否洽谈'", insertable = false)
    private Boolean discuss;

    /**
     * 洽谈结果
     */
    @Column(name = "discussResult", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '洽谈结果'")
    private String discussResult;

    /**
     * 是否达到洽谈目的
     */
    @Column(name = "is_attainDiscussIdea", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否达到洽谈目的'", insertable = false)
    private Boolean attainDiscussIdea;

    /**
     * 是否有洽谈到其他问题
     */
    @Column(name = "is_discussProblem", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有洽谈到其他问题'", insertable = false)
    private Boolean discussProblem;

    /**
     * 话题记录
     */
    @Column(name = "subjectRecord", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '话题记录'")
    private String subjectRecord;

    /**
     * 是否有录音
     */
    @Column(name = "is_soundRecord", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有录音'", insertable = false)
    private Boolean soundRecord;

    /**
     * 是否转入合同管理-已立项
     */
    @Column(name = "is_hasProject", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否转入合同管理-已立项'", insertable = false)
    private Boolean hasProject;

    /**
     * 是否转入合同管理-市场费用
     */
    @Column(name = "is_marketCost", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否转入合同管理-市场费用'", insertable = false)
    private Boolean marketCost;

    /**
     * 是否转换市场招待
     */
    @Column(name = "is_marketFor", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否转换市场招待'", insertable = false)
    private Boolean marketFor;

    /**
     * 是否持续跟进
     */
    @Column(name = "is_continueFollowUp", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否持续跟进'", insertable = false)
    private Boolean continueFollowUp;

    /**
     * 是否闭环
     */
    @Column(name = "is_closedLoop", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否闭环'", insertable = false)
    private Boolean closedLoop;

    /**
     * 是否需要协助
     */
    @Column(name = "is_needAssist", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需要协助'", insertable = false)
    private Boolean needAssist;

    /**
     * 需协助部门
     */
    @Column(name = "assistDept", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '需协助部门'")
    private String assistDept;

    /**
     * 协助内容
     */
    @Column(name = "assistContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协助内容'")
    private String assistContent;

    /**
     * 是否已发协助函
     */
    @Column(name = "is_assistLetter", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否已发协助函'", insertable = false)
    private Boolean assistLetter;

    /**
     * 协助函单号
     */
    @Column(name = "assistLetterNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协助函单号'")
    private String assistLetterNum;

    /**
     * 计划跟进时间
     */
    @Column(name = "planFollowUp", nullable = false, columnDefinition = "DATE   COMMENT '计划跟进时间'")
    private LocalDate planFollowUp;

    /**
     * 是否产生路费
     */
    @Column(name = "is_produceTrip", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否产生路费'", insertable = false)
    private Boolean produceTrip;

    /**
     * 路费
     */
    @Column(name = "trip", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '路费'")
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

    public Boolean getDiscussPrepare() {
        return discussPrepare;
    }

    public void setDiscussPrepare(Boolean discussPrepare) {
        this.discussPrepare = discussPrepare;
    }

    public String getAmountInvolved() {
        return amountInvolved;
    }

    public void setAmountInvolved(String amountInvolved) {
        this.amountInvolved = amountInvolved;
    }

    public Boolean getDiscuss() {
        return discuss;
    }

    public void setDiscuss(Boolean discuss) {
        this.discuss = discuss;
    }

    public String getDiscussResult() {
        return discussResult;
    }

    public void setDiscussResult(String discussResult) {
        this.discussResult = discussResult;
    }

    public Boolean getAttainDiscussIdea() {
        return attainDiscussIdea;
    }

    public void setAttainDiscussIdea(Boolean attainDiscussIdea) {
        this.attainDiscussIdea = attainDiscussIdea;
    }

    public Boolean getDiscussProblem() {
        return discussProblem;
    }

    public void setDiscussProblem(Boolean discussProblem) {
        this.discussProblem = discussProblem;
    }

    public String getSubjectRecord() {
        return subjectRecord;
    }

    public void setSubjectRecord(String subjectRecord) {
        this.subjectRecord = subjectRecord;
    }

    public Boolean getSoundRecord() {
        return soundRecord;
    }

    public void setSoundRecord(Boolean soundRecord) {
        this.soundRecord = soundRecord;
    }

    public Boolean getHasProject() {
        return hasProject;
    }

    public void setHasProject(Boolean hasProject) {
        this.hasProject = hasProject;
    }

    public Boolean getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(Boolean marketCost) {
        this.marketCost = marketCost;
    }

    public Boolean getMarketFor() {
        return marketFor;
    }

    public void setMarketFor(Boolean marketFor) {
        this.marketFor = marketFor;
    }

    public Boolean getContinueFollowUp() {
        return continueFollowUp;
    }

    public void setContinueFollowUp(Boolean continueFollowUp) {
        this.continueFollowUp = continueFollowUp;
    }

    public Boolean getClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(Boolean closedLoop) {
        this.closedLoop = closedLoop;
    }

    public Boolean getNeedAssist() {
        return needAssist;
    }

    public void setNeedAssist(Boolean needAssist) {
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

    public Boolean getAssistLetter() {
        return assistLetter;
    }

    public void setAssistLetter(Boolean assistLetter) {
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

    public Boolean getProduceTrip() {
        return produceTrip;
    }

    public void setProduceTrip(Boolean produceTrip) {
        this.produceTrip = produceTrip;
    }

    public Double getTrip() {
        return trip;
    }

    public void setTrip(Double trip) {
        this.trip = trip;
    }
}