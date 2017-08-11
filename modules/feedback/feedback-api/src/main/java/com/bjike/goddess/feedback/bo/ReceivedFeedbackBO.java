package com.bjike.goddess.feedback.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 已受理的反馈业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 09:24 ]
 * @Description: [ 已受理的反馈业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReceivedFeedbackBO extends BaseBO {
    /**
     * 录入人
     */
    private String inputUser;

    /**
     * 问题编号(对外)
     */
    private String problemNum;
    /**
     * 问题受理编号(对内)
     */
    private String acceptNum;
    /**
     * 事件(背景)描述
     */
    private String eventDescription;

    /**
     * 问题描述
     */
    private String problemDescription;
    /**
     * 获取时间(问题提出时间)
     */
    private String getTime;
    /**
     * 是否通报
     */
    private Boolean notification;
    /**
     * 通报时间
     */
    private String notificationTime;
    /**
     * 通报途径
     */
    private String notificationWay;

    /**
     * 问题受理所属部门
     */
    private String acceptDepartment;

    /**
     * 问题受理所属模块
     */
    private String acceptModule;

    /**
     * 问题受理人
     */
    private String acceptPerson;

    /**
     * 问题跟进处理计划完成时间
     */
    private String acceptTime;

    /**
     * 意见收集完成时间
     */
    private String ideaTime;
    /**
     * 期望处理时间
     */
    private String expectDealTime;

    /**
     * 问题类型
     */
    private String problemType;
    /**
     * 主功能
     */
    private String mainFunction;

    /**
     * 关联相关模块
     */
    private String correlativeModule;


    /**
     * 所属地区
     */
    private String area;
    /**
     * 问题提出人
     */
    private String problemExhibitor;

    /**
     * 所属项目组/部门
     */
    private String projectGroup;

    /**
     * 问题责任人
     */
    private String problemDutyOfficer;

    /**
     * 问题来源
     */
    private String problemSource;


    /**
     * 优先级（系统计算分值）
     */
    private Integer systemPriority;

    /**
     * 优先级（人工编辑）
     */
    private Integer artificialPriority;

    /**
     * 非责任相关人意见数(提供接口查询详情)
     */
    private Integer responsibleNum;

    /**
     * 一线项目组意见-建议描述
     */
    private String firstProjectGroupOpinion;

    /**
     * 意见提出人
     */
    private String firstIdea;

    /**
     * 规划模块意见-建议描述
     */
    private String planOpinion;

    /**
     * 意见提出人
     */
    private String planIdea;

    /**
     * 综合素养意见-建议描述
     */
    private String literacyOpinion;

    /**
     * 意见提出人
     */
    private String literacyIdea;

    /**
     * 商务市场部意见-建议描述
     */
    private String businessOpinion;

    /**
     * 意见提出人
     */
    private String businessIdea;

    /**
     * 资金意见-建议描述
     */
    private String moneyOpinion;

    /**
     * 意见提出人
     */
    private String moneyIdea;

    /**
     * 账务意见-建议描述
     */
    private String accountOpinion;

    /**
     * 意见提出人
     */
    private String accountIdea;

    /**
     * 预算意见-建议描述
     */
    private String budgetOpinion;

    /**
     * 意见提出人
     */
    private String budgetIdea;

    /**
     * 研发部意见-建议描述
     */
    private String divisionOpinion;

    /**
     * 意见提出人
     */
    private String divisionIdea;

    /**
     * 总经办（公司宏观视角）意见-建议描述
     */
    private String generalManagerOpinion;

    /**
     * 总经办（公司宏观视角）意见提出人
     */
    private String generalManagerIdea;

    public String getInputUser() {
        return inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public String getProblemNum() {
        return problemNum;
    }

    public void setProblemNum(String problemNum) {
        this.problemNum = problemNum;
    }

    public String getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(String acceptNum) {
        this.acceptNum = acceptNum;
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

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getNotificationWay() {
        return notificationWay;
    }

    public void setNotificationWay(String notificationWay) {
        this.notificationWay = notificationWay;
    }

    public String getAcceptDepartment() {
        return acceptDepartment;
    }

    public void setAcceptDepartment(String acceptDepartment) {
        this.acceptDepartment = acceptDepartment;
    }

    public String getAcceptModule() {
        return acceptModule;
    }

    public void setAcceptModule(String acceptModule) {
        this.acceptModule = acceptModule;
    }

    public String getAcceptPerson() {
        return acceptPerson;
    }

    public void setAcceptPerson(String acceptPerson) {
        this.acceptPerson = acceptPerson;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getIdeaTime() {
        return ideaTime;
    }

    public void setIdeaTime(String ideaTime) {
        this.ideaTime = ideaTime;
    }

    public String getExpectDealTime() {
        return expectDealTime;
    }

    public void setExpectDealTime(String expectDealTime) {
        this.expectDealTime = expectDealTime;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProblemExhibitor() {
        return problemExhibitor;
    }

    public void setProblemExhibitor(String problemExhibitor) {
        this.problemExhibitor = problemExhibitor;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProblemDutyOfficer() {
        return problemDutyOfficer;
    }

    public void setProblemDutyOfficer(String problemDutyOfficer) {
        this.problemDutyOfficer = problemDutyOfficer;
    }

    public String getProblemSource() {
        return problemSource;
    }

    public void setProblemSource(String problemSource) {
        this.problemSource = problemSource;
    }

    public Integer getSystemPriority() {
        return systemPriority;
    }

    public void setSystemPriority(Integer systemPriority) {
        this.systemPriority = systemPriority;
    }

    public Integer getArtificialPriority() {
        return artificialPriority;
    }

    public void setArtificialPriority(Integer artificialPriority) {
        this.artificialPriority = artificialPriority;
    }

    public Integer getResponsibleNum() {
        return responsibleNum;
    }

    public void setResponsibleNum(Integer responsibleNum) {
        this.responsibleNum = responsibleNum;
    }

    public String getFirstProjectGroupOpinion() {
        return firstProjectGroupOpinion;
    }

    public void setFirstProjectGroupOpinion(String firstProjectGroupOpinion) {
        this.firstProjectGroupOpinion = firstProjectGroupOpinion;
    }

    public String getFirstIdea() {
        return firstIdea;
    }

    public void setFirstIdea(String firstIdea) {
        this.firstIdea = firstIdea;
    }

    public String getPlanOpinion() {
        return planOpinion;
    }

    public void setPlanOpinion(String planOpinion) {
        this.planOpinion = planOpinion;
    }

    public String getPlanIdea() {
        return planIdea;
    }

    public void setPlanIdea(String planIdea) {
        this.planIdea = planIdea;
    }

    public String getLiteracyOpinion() {
        return literacyOpinion;
    }

    public void setLiteracyOpinion(String literacyOpinion) {
        this.literacyOpinion = literacyOpinion;
    }

    public String getLiteracyIdea() {
        return literacyIdea;
    }

    public void setLiteracyIdea(String literacyIdea) {
        this.literacyIdea = literacyIdea;
    }

    public String getBusinessOpinion() {
        return businessOpinion;
    }

    public void setBusinessOpinion(String businessOpinion) {
        this.businessOpinion = businessOpinion;
    }

    public String getBusinessIdea() {
        return businessIdea;
    }

    public void setBusinessIdea(String businessIdea) {
        this.businessIdea = businessIdea;
    }

    public String getMoneyOpinion() {
        return moneyOpinion;
    }

    public void setMoneyOpinion(String moneyOpinion) {
        this.moneyOpinion = moneyOpinion;
    }

    public String getMoneyIdea() {
        return moneyIdea;
    }

    public void setMoneyIdea(String moneyIdea) {
        this.moneyIdea = moneyIdea;
    }

    public String getAccountOpinion() {
        return accountOpinion;
    }

    public void setAccountOpinion(String accountOpinion) {
        this.accountOpinion = accountOpinion;
    }

    public String getAccountIdea() {
        return accountIdea;
    }

    public void setAccountIdea(String accountIdea) {
        this.accountIdea = accountIdea;
    }

    public String getBudgetOpinion() {
        return budgetOpinion;
    }

    public void setBudgetOpinion(String budgetOpinion) {
        this.budgetOpinion = budgetOpinion;
    }

    public String getBudgetIdea() {
        return budgetIdea;
    }

    public void setBudgetIdea(String budgetIdea) {
        this.budgetIdea = budgetIdea;
    }

    public String getDivisionOpinion() {
        return divisionOpinion;
    }

    public void setDivisionOpinion(String divisionOpinion) {
        this.divisionOpinion = divisionOpinion;
    }

    public String getDivisionIdea() {
        return divisionIdea;
    }

    public void setDivisionIdea(String divisionIdea) {
        this.divisionIdea = divisionIdea;
    }

    public String getGeneralManagerOpinion() {
        return generalManagerOpinion;
    }

    public void setGeneralManagerOpinion(String generalManagerOpinion) {
        this.generalManagerOpinion = generalManagerOpinion;
    }

    public String getGeneralManagerIdea() {
        return generalManagerIdea;
    }

    public void setGeneralManagerIdea(String generalManagerIdea) {
        this.generalManagerIdea = generalManagerIdea;
    }
}