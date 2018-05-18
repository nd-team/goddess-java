package com.bjike.goddess.problemhandle.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.problemhandle.enums.BusinessState;
import com.bjike.goddess.problemhandle.enums.TypeProblem;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目中问题受理和处理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-08 03:43 ]
 * @Description: [ 项目中问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectProblemAccTO extends BaseTO {

    /**
     * 录入人
     */
    @NotBlank(message = "录入人不能为空",groups = {ADD.class, EDIT.class})
    private String enterOne;

    /**
     * 问题编号
     */
    @NotBlank(message = "问题编号不能为空",groups = {ADD.class, EDIT.class})
    private String questionNum;

    /**
     * 所属地区
     */
    @NotBlank(message = "所属地区不能为空",groups = {ADD.class, EDIT.class})
    private String eachDistrict;

    /**
     * 所属项目组/部门
     */
    @NotBlank(message = "所属项目组不能为空",groups = {ADD.class, EDIT.class})
    private String subordinateDepartment;

    /**
     * 所属模块
     */
    @NotBlank(message = "所属模块不能为空",groups = {ADD.class, EDIT.class})
    private String subordinateModule;

    /**
     * 问题提出人
     */
    @NotBlank(message = "问题提出人不能为空",groups = {ADD.class, EDIT.class})
    private String question;

    /**
     * 事件（背景）描述
     */
    private String eventDescription;

    /**
     * 问题描述
     */
    @NotBlank(message = "问题描述不能为空",groups = {ADD.class, EDIT.class})
    private String problemDescription;

    /**
     * 获取时间
     */
    @NotBlank(message = "获取时间不能为空",groups = {ADD.class, EDIT.class})
    private String getTime;

    /**
     * 期望处理时间
     */
    @NotBlank(message = "期望处理时间不能为空",groups = {ADD.class, EDIT.class})
    private String expectedTime;

    /**
     * 问题类型
     */
    @NotNull(message = "问题类型不能为空",groups = {ADD.class, EDIT.class})
    private TypeProblem typeProblem;

    /**
     * 协助部门
     */
    private String assistDept;

    /**
     * 主功能
     */
    private String mainFunction;

    /**
     * 关联相关模块
     */
    @NotBlank(message = "关联相关模块不能为空",groups = {ADD.class, EDIT.class})
    private String correlativeModule;

    /**
     * 问题责任人
     */
    @NotBlank(message = "问题责任人不能为空",groups = {ADD.class, EDIT.class})
    private String problemPerson;

    /**
     * 问题来源
     */
    private String sourceProblem;

    /**
     * 影响
     */
    private String influence;

    /**
     * 是否通报
     */
    @NotNull(message = "是否通报不能为空",groups = {ADD.class, EDIT.class})
    private Boolean communicated;

    /**
     * 问题受理编号(对内)
     */
    @NotBlank(message = "问题受理编号不能为空",groups = {ADD.class, EDIT.class})
    private String problemAcceptanceNum;

    /**
     * 问题受理所属部门
     */
    @NotBlank(message = "问题受理所属部门不能为空",groups = {ADD.class, EDIT.class})
    private String problemHandledDepartment;

    /**
     * 问题受理所属模块
     */
    @NotBlank(message = "问题受理所属模块不能为空",groups = {ADD.class, EDIT.class})
    private String problemHandledMoudle;

    /**
     * 问题受理人
     */
    @NotBlank(message = "问题受理人不能为空",groups = {ADD.class, EDIT.class})
    private String probleAdmissible;

    /**
     * 问题跟进处理计划完成时间
     */
    private String problemFollowComTime;

    /**
     * 涉及金额
     */
    private Double involvedAmount;

    /**
     * 是否对外
     */
    private Boolean isOutward;

    /**
     * 业务状态
     */
    @NotNull(message = "业务状态不能为空",groups = {ADD.class, EDIT.class})
    private BusinessState businessState;

    /**
     * 市场信息编号
     */
    @NotBlank(message = "市场信息编号不能为空",groups = {ADD.class, EDIT.class})
    private String marketInfoNum;

    /**
     * 内部项目名称
     */
    @NotBlank(message = "内部项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String internalProjectName;

    /**
     * 客户编号
     */
    private String customerNum;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 职务
     */
    private String duty;

    /**
     * 姓名
     */
    private String name;

    /**
     * 问题处理结果
     */
    private String problemProcessResult;

    /**
     * 计划再跟进时间
     */
    private String planTime;

    /**
     * 是否闭环
     */
    @NotNull(message = "是否闭环不能为空",groups = {ADD.class, EDIT.class})
    private Boolean closeLoop;

    /**
     * 问题实际完成时间
     */
    private String problemActually;

    /**
     * 是否需要协调
     */
    private Boolean needCoordinate;

    /**
     * 协调部门
     */
    private String coordinate;

    /**
     * 协调模块
     */
    private String coordModule;

    /**
     * 协调结果
     */
    private String coordResult;

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

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(String expectedTime) {
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

    public String getProblemFollowComTime() {
        return problemFollowComTime;
    }

    public void setProblemFollowComTime(String problemFollowComTime) {
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

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public Boolean getCloseLoop() {
        return closeLoop;
    }

    public void setCloseLoop(Boolean closeLoop) {
        this.closeLoop = closeLoop;
    }

    public String getProblemActually() {
        return problemActually;
    }

    public void setProblemActually(String problemActually) {
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

}