package com.bjike.goddess.projectissuehandle.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.projectissuehandle.enums.BusinessState;
import com.bjike.goddess.projectissuehandle.enums.TypeProblem;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 项目中问题受理和处理导出
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-08 03:43 ]
 * @Description: [ 项目中问题受理和处理导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectProblemAccExport extends BaseBO {

    /**
     * 录入人
     */
    @ExcelHeader(name="录入人",notNull = true)
    private String enterOne;

    /**
     * 问题编号
     */
    @ExcelHeader(name="问题编号",notNull = true)
    private String questionNum;

    /**
     * 所属地区
     */
    @ExcelHeader(name="所属地区",notNull = true)
    private String eachDistrict;

    /**
     * 所属项目组/部门
     */
    @ExcelHeader(name="所属项目组/部门",notNull = true)
    private String subordinateDepartment;

    /**
     * 所属模块
     */
    @ExcelHeader(name="所属模块",notNull = true)
    private String subordinateModule;

    /**
     * 问题提出人
     */
    @ExcelHeader(name="问题提出人",notNull = true)
    private String question;

    /**
     * 事件（背景）描述
     */
    @ExcelHeader(name="事件（背景）描述")
    private String eventDescription;

    /**
     * 问题描述
     */
    @ExcelHeader(name="问题描述",notNull = true)
    private String problemDescription;

    /**
     * 获取时间
     */
    @ExcelHeader(name="获取时间",notNull = true)
    private String getTime;

    /**
     * 期望处理时间
     */
    @ExcelHeader(name="期望处理时间",notNull = true)
    private String expectedTime;

    /**
     * 问题类型
     */
    @ExcelHeader(name="问题类型",notNull = true)
    private TypeProblem typeProblem;

    /**
     * 协助部门
     */
    @ExcelHeader(name="协助部门")
    private String assistDept;

    /**
     * 主功能
     */
    @ExcelHeader(name="主功能")
    private String mainFunction;

    /**
     * 关联相关模块
     */
    @ExcelHeader(name="关联相关模块",notNull = true)
    private String correlativeModule;

    /**
     * 问题责任人
     */
    @ExcelHeader(name="问题责任人",notNull = true)
    private String problemPerson;

    /**
     * 问题来源
     */
    @ExcelHeader(name="问题来源")
    private String sourceProblem;

    /**
     * 影响
     */
    @ExcelHeader(name="影响")
    private String influence;

    /**
     * 是否通报
     */
    @ExcelHeader(name="是否通报",notNull = true)
    private String communicated;

    /**
     * 问题受理编号(对内)
     */
    @ExcelHeader(name="问题受理编号",notNull = true)
    private String problemAcceptanceNum;

    /**
     * 问题受理所属部门
     */
    @ExcelHeader(name="问题受理所属部门",notNull = true)
    private String problemHandledDepartment;

    /**
     * 问题受理所属模块
     */
    @ExcelHeader(name="问题受理所属模块",notNull = true)
    private String problemHandledMoudle;

    /**
     * 问题受理人
     */
    @ExcelHeader(name="问题受理人",notNull = true)
    private String probleAdmissible;

    /**
     * 问题跟进处理计划完成时间
     */
    @ExcelHeader(name="问题跟进处理计划完成时间")
    private String problemFollowComTime;

    /**
     * 涉及金额
     */
    @ExcelHeader(name="涉及金额")
    private Double involvedAmount;

    /**
     * 是否对外
     */
    @ExcelHeader(name="是否对外")
    private String isOutward;

    /**
     * 业务状态
     */
    @ExcelHeader(name="业务状态",notNull = true)
    private BusinessState businessState;

    /**
     * 市场信息编号
     */
    @ExcelHeader(name="市场信息编号",notNull = true)
    private String marketInfoNum;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name="内部项目名称",notNull = true)
    private String internalProjectName;

    /**
     * 客户编号
     */
    @ExcelHeader(name="客户编号")
    private String customerNum;

    /**
     * 公司名称
     */
    @ExcelHeader(name="公司名称")
    private String companyName;

    /**
     * 职务
     */
    @ExcelHeader(name="职务")
    private String duty;

    /**
     * 姓名
     */
    @ExcelHeader(name="姓名")
    private String name;

    /**
     * 问题处理结果
     */
    @ExcelHeader(name="问题处理结果")
    private String problemProcessResult;

    /**
     * 计划再跟进时间
     */
    @ExcelHeader(name="计划再跟进时间")
    private String planTime;

    /**
     * 是否闭环
     */
    @ExcelHeader(name="是否闭环",notNull = true)
    private String closeLoop;

    /**
     * 问题实际完成时间
     */
    @ExcelHeader(name="问题实际完成时间")
    private String problemActually;

    /**
     * 是否需要协调
     */
    @ExcelHeader(name="是否需要协调")
    private String needCoordinate;

    /**
     * 协调部门
     */
    @ExcelHeader(name="协调部门")
    private String coordinate;

    /**
     * 协调模块
     */
    @ExcelHeader(name="协调模块")
    private String coordModule;

    /**
     * 协调结果
     */
    @ExcelHeader(name="协调结果")
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

    public String getCommunicated() {
        return communicated;
    }

    public void setCommunicated(String communicated) {
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

    public String getIsOutward() {
        return isOutward;
    }

    public void setIsOutward(String isOutward) {
        this.isOutward = isOutward;
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

    public String getCloseLoop() {
        return closeLoop;
    }

    public void setCloseLoop(String closeLoop) {
        this.closeLoop = closeLoop;
    }

    public String getProblemActually() {
        return problemActually;
    }

    public void setProblemActually(String problemActually) {
        this.problemActually = problemActually;
    }

    public String getNeedCoordinate() {
        return needCoordinate;
    }

    public void setNeedCoordinate(String needCoordinate) {
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