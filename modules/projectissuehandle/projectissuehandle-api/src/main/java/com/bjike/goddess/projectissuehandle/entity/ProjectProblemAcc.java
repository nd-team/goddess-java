package com.bjike.goddess.projectissuehandle.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.projectissuehandle.enums.BusinessState;
import com.bjike.goddess.projectissuehandle.enums.TypeProblem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 项目中问题受理和处理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-08 03:43 ]
 * @Description: [ 项目中问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectissuehandle_projectproblemacc")
public class ProjectProblemAcc extends BaseEntity {

    /**
     * 录入人
     */
    @Column(name = "enterOne", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '录入人'")
    private String enterOne;

    /**
     * 问题编号
     */
    @Column(name = "questionNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题编号'")
    private String questionNum;

    /**
     * 所属地区
     */
    @Column(name = "eachDistrict", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属地区'")
    private String eachDistrict;

    /**
     * 所属项目组/部门
     */
    @Column(name = "subordinateDepartment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属项目组/部门'")
    private String subordinateDepartment;

    /**
     * 所属模块
     */
    @Column(name = "subordinateModule", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属模块'")
    private String subordinateModule;

    /**
     * 问题提出人
     */
    @Column(name = "question", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题提出人'")
    private String question;

    /**
     * 事件（背景）描述
     */
    @Column(name = "eventDescription",columnDefinition = "VARCHAR(255)   COMMENT '事件（背景）描述'")
    private String eventDescription;

    /**
     * 问题描述
     */
    @Column(name = "problemDescription", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题描述'")
    private String problemDescription;

    /**
     * 获取时间
     */
    @Column(name = "getTime", nullable = false, columnDefinition = "DATE   COMMENT '获取时间'")
    private LocalDate getTime;

    /**
     * 期望处理时间
     */
    @Column(name = "expectedTime", nullable = false, columnDefinition = "DATE   COMMENT '期望处理时间'")
    private LocalDate expectedTime;

    /**
     * 问题类型
     */
    @Column(name = "typeProblem", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '问题类型'")
    private TypeProblem typeProblem;

    /**
     * 协助部门
     */
    @Column(name = "assistDept", columnDefinition = "VARCHAR(255)   COMMENT '协助部门'")
    private String assistDept;

    /**
     * 主功能
     */
    @Column(name = "mainFunction", columnDefinition = "VARCHAR(255)   COMMENT '主功能'")
    private String mainFunction;

    /**
     * 关联相关模块
     */
    @Column(name = "correlativeModule", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '关联相关模块'")
    private String correlativeModule;

    /**
     * 问题责任人
     */
    @Column(name = "problemPerson", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题责任人'")
    private String problemPerson;

    /**
     * 问题来源
     */
    @Column(name = "sourceProblem",  columnDefinition = "VARCHAR(255)   COMMENT '问题来源'")
    private String sourceProblem;

    /**
     * 影响
     */
    @Column(name = "influence", columnDefinition = "VARCHAR(255)   COMMENT '影响'")
    private String influence;

    /**
     * 是否通报
     */
    @Column(name = "is_communicated", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否通报'")
    private Boolean communicated;

    /**
     * 问题受理编号(对内)
     */
    @Column(name = "problemAcceptanceNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题受理编号(对内)'")
    private String problemAcceptanceNum;

    /**
     * 问题受理所属部门
     */
    @Column(name = "problemHandledDepartment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题受理所属部门'")
    private String problemHandledDepartment;

    /**
     * 问题受理所属模块
     */
    @Column(name = "problemHandledMoudle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题受理所属模块'")
    private String problemHandledMoudle;

    /**
     * 问题受理人
     */
    @Column(name = "probleAdmissible", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题受理人'")
    private String probleAdmissible;

    /**
     * 问题跟进处理计划完成时间
     */
    @Column(name = "problemFollowComTime", columnDefinition = "DATE   COMMENT '问题跟进处理计划完成时间'")
    private LocalDate problemFollowComTime;

    /**
     * 涉及金额
     */
    @Column(name = "involvedAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '涉及金额'")
    private Double involvedAmount;

    /**
     * 是否对外
     */
    @Column(name = "is_isOutward", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否对外'", insertable = false)
    private Boolean isOutward;

    /**
     * 业务状态
     */
    @Column(name = "businessState", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '业务状态'")
    private BusinessState businessState;

    /**
     * 市场信息编号
     */
    @Column(name = "marketInfoNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '市场信息编号'")
    private String marketInfoNum;

    /**
     * 内部项目名称
     */
    @Column(name = "internalProjectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String internalProjectName;

    /**
     * 客户编号
     */
    @Column(name = "customerNum",  columnDefinition = "VARCHAR(255)   COMMENT '客户编号'")
    private String customerNum;

    /**
     * 公司名称
     */
    @Column(name = "companyName",  columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String companyName;

    /**
     * 职务
     */
    @Column(name = "duty",  columnDefinition = "VARCHAR(255)   COMMENT '职务'")
    private String duty;

    /**
     * 姓名
     */
    @Column(name = "name",  columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 问题处理结果
     */
    @Column(name = "problemProcessResult",  columnDefinition = "VARCHAR(255)   COMMENT '问题处理结果'")
    private String problemProcessResult;

    /**
     * 计划再跟进时间
     */
    @Column(name = "planTime", columnDefinition = "DATE   COMMENT '计划再跟进时间'")
    private LocalDate planTime;

    /**
     * 是否闭环
     */
    @Column(name = "is_closeLoop", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否闭环'")
    private Boolean closeLoop;

    /**
     * 问题实际完成时间
     */
    @Column(name = "problemActually",  columnDefinition = "DATE   COMMENT '问题实际完成时间'")
    private LocalDate problemActually;

    /**
     * 是否需要协调
     */
    @Column(name = "is_needCoordinate",  columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需要协调'", insertable = false)
    private Boolean needCoordinate;

    /**
     * 协调部门
     */
    @Column(name = "coordinate",  columnDefinition = "VARCHAR(255)   COMMENT '协调部门'")
    private String coordinate;

    /**
     * 协调模块
     */
    @Column(name = "coordModule",  columnDefinition = "VARCHAR(255)   COMMENT '协调模块'")
    private String coordModule;

    /**
     * 协调结果
     */
    @Column(name = "coordResult",  columnDefinition = "VARCHAR(255)   COMMENT '协调结果'")
    private String coordResult;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    public String getEnterOne() {
        return enterOne;
    }

    public void setEnterOne(String enterOne) {
        this.enterOne = enterOne;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }

    public String getEachDistrict() {
        return eachDistrict;
    }

    public void setEachDistrict(String eachDistrict) {
        this.eachDistrict = eachDistrict;
    }

    public String getSubordinateDepartment() {
        return subordinateDepartment;
    }

    public void setSubordinateDepartment(String subordinateDepartment) {
        this.subordinateDepartment = subordinateDepartment;
    }

    public String getSubordinateModule() {
        return subordinateModule;
    }

    public void setSubordinateModule(String subordinateModule) {
        this.subordinateModule = subordinateModule;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public LocalDate getGetTime() {
        return getTime;
    }

    public void setGetTime(LocalDate getTime) {
        this.getTime = getTime;
    }

    public LocalDate getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(LocalDate expectedTime) {
        this.expectedTime = expectedTime;
    }

    public TypeProblem getTypeProblem() {
        return typeProblem;
    }

    public void setTypeProblem(TypeProblem typeProblem) {
        this.typeProblem = typeProblem;
    }

    public String getAssistDept() {
        return assistDept;
    }

    public void setAssistDept(String assistDept) {
        this.assistDept = assistDept;
    }

    public String getMainFunction() {
        return mainFunction;
    }

    public void setMainFunction(String mainFunction) {
        this.mainFunction = mainFunction;
    }

    public String getCorrelativeModule() {
        return correlativeModule;
    }

    public void setCorrelativeModule(String correlativeModule) {
        this.correlativeModule = correlativeModule;
    }

    public String getProblemPerson() {
        return problemPerson;
    }

    public void setProblemPerson(String problemPerson) {
        this.problemPerson = problemPerson;
    }

    public String getSourceProblem() {
        return sourceProblem;
    }

    public void setSourceProblem(String sourceProblem) {
        this.sourceProblem = sourceProblem;
    }

    public String getInfluence() {
        return influence;
    }

    public void setInfluence(String influence) {
        this.influence = influence;
    }

    public Boolean getCommunicated() {
        return communicated;
    }

    public void setCommunicated(Boolean communicated) {
        this.communicated = communicated;
    }

    public String getProblemAcceptanceNum() {
        return problemAcceptanceNum;
    }

    public void setProblemAcceptanceNum(String problemAcceptanceNum) {
        this.problemAcceptanceNum = problemAcceptanceNum;
    }

    public String getProblemHandledDepartment() {
        return problemHandledDepartment;
    }

    public void setProblemHandledDepartment(String problemHandledDepartment) {
        this.problemHandledDepartment = problemHandledDepartment;
    }

    public String getProblemHandledMoudle() {
        return problemHandledMoudle;
    }

    public void setProblemHandledMoudle(String problemHandledMoudle) {
        this.problemHandledMoudle = problemHandledMoudle;
    }

    public String getProbleAdmissible() {
        return probleAdmissible;
    }

    public void setProbleAdmissible(String probleAdmissible) {
        this.probleAdmissible = probleAdmissible;
    }

    public LocalDate getProblemFollowComTime() {
        return problemFollowComTime;
    }

    public void setProblemFollowComTime(LocalDate problemFollowComTime) {
        this.problemFollowComTime = problemFollowComTime;
    }

    public Double getInvolvedAmount() {
        return involvedAmount;
    }

    public void setInvolvedAmount(Double involvedAmount) {
        this.involvedAmount = involvedAmount;
    }

    public Boolean getOutward() {
        return isOutward;
    }

    public void setOutward(Boolean outward) {
        isOutward = outward;
    }

    public BusinessState getBusinessState() {
        return businessState;
    }

    public void setBusinessState(BusinessState businessState) {
        this.businessState = businessState;
    }

    public String getMarketInfoNum() {
        return marketInfoNum;
    }

    public void setMarketInfoNum(String marketInfoNum) {
        this.marketInfoNum = marketInfoNum;
    }

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProblemProcessResult() {
        return problemProcessResult;
    }

    public void setProblemProcessResult(String problemProcessResult) {
        this.problemProcessResult = problemProcessResult;
    }

    public LocalDate getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDate planTime) {
        this.planTime = planTime;
    }

    public Boolean getCloseLoop() {
        return closeLoop;
    }

    public void setCloseLoop(Boolean closeLoop) {
        this.closeLoop = closeLoop;
    }

    public LocalDate getProblemActually() {
        return problemActually;
    }

    public void setProblemActually(LocalDate problemActually) {
        this.problemActually = problemActually;
    }

    public Boolean getNeedCoordinate() {
        return needCoordinate;
    }

    public void setNeedCoordinate(Boolean needCoordinate) {
        this.needCoordinate = needCoordinate;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordModule() {
        return coordModule;
    }

    public void setCoordModule(String coordModule) {
        this.coordModule = coordModule;
    }

    public String getCoordResult() {
        return coordResult;
    }

    public void setCoordResult(String coordResult) {
        this.coordResult = coordResult;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}