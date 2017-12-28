package com.bjike.goddess.feedback.vo;

/**
 * 问题处理结果表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 07:02 ]
 * @Description: [ 问题处理结果表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemResultVO {

    /**
     * id
     */
    private String id;
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
     * 最终解决方案
     */
    private String finalSolution;

    /**
     * 最终解决方案确定时间
     */
    private String finalSolutionTime;

    /**
     * 问题解决时间
     */
    private String problemSolveTime;

    /**
     * 当事人是否确认处理完成
     */
    private String partyFinish;

    /**
     * 当事人确认意见
     */
    private String partyIdea;

    /**
     * 其他模块意见数
     */
    private Integer otherIdeaNum;

    /**
     * 是否需要协调
     */
    private Boolean coordinate;

    /**
     * 协调结果
     */
    private String coordinateResult;

    /**
     * 问题处理结果
     */
    private String problemResult;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getFinalSolution() {
        return finalSolution;
    }

    public void setFinalSolution(String finalSolution) {
        this.finalSolution = finalSolution;
    }

    public String getFinalSolutionTime() {
        return finalSolutionTime;
    }

    public void setFinalSolutionTime(String finalSolutionTime) {
        this.finalSolutionTime = finalSolutionTime;
    }

    public String getProblemSolveTime() {
        return problemSolveTime;
    }

    public void setProblemSolveTime(String problemSolveTime) {
        this.problemSolveTime = problemSolveTime;
    }

    public String getPartyFinish() {
        return partyFinish;
    }

    public void setPartyFinish(String partyFinish) {
        this.partyFinish = partyFinish;
    }

    public String getPartyIdea() {
        return partyIdea;
    }

    public void setPartyIdea(String partyIdea) {
        this.partyIdea = partyIdea;
    }

    public Integer getOtherIdeaNum() {
        return otherIdeaNum;
    }

    public void setOtherIdeaNum(Integer otherIdeaNum) {
        this.otherIdeaNum = otherIdeaNum;
    }

    public Boolean getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Boolean coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordinateResult() {
        return coordinateResult;
    }

    public void setCoordinateResult(String coordinateResult) {
        this.coordinateResult = coordinateResult;
    }

    public String getProblemResult() {
        return problemResult;
    }

    public void setProblemResult(String problemResult) {
        this.problemResult = problemResult;
    }
}