package com.bjike.goddess.organize.excel;

import java.io.Serializable;

/**
 * 岗位工作明细表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 岗位工作明细表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionWorkDetailsExportUtil implements Serializable {


    /**
     * 公司目标
     */
    private String goals;

    /**
     * 公司
     */
    private String company;

    /**
     * 部门目标
     */
    private String departmentalGoals;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 岗位目标
     */
    private String positionGoals;

    /**
     * 岗位
     */
    private String position;

    /**
     * 序号
     */
    private Long serialNumber;

    /**
     * 角度
     */
    private String angle;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 分类
     */
    private String classification;

    /**
     * 项目阶段
     */
    private String projectStage;

    /**
     * 项目阶段编号
     */
    private String projectStageNum;

    /**
     * 功能（流程）
     */
    private String function;

    /**
     * 功能（流程）目的
     */
    private String purpose;

    /**
     * 功能版本
     */
    private String version;

    /**
     * 工作频率
     */
    private String frequency;

    /**
     * 工作时间节点
     */
    private String timeNode;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 工作内容
     */
    private String workContent;

    /**
     * 包含的附件（名称）
     */
    private String accessories;

    /**
     * 是否有模板
     */
    private Boolean hasMould;

    /**
     * 模板储存位置
     */
    private String mouldStorage;

    /**
     * 工作数据存储位置
     */
    private String paperStorage;

    /**
     * 经验总结
     */
    private String summarize;

    /**
     * 通报时间节点
     */
    private String informTimeNode;

    /**
     * 通报形式
     */
    private String notificationForm;

    /**
     * 通报内容
     */
    private String notificationContent;

    /**
     * 通报对象
     */
    private String notificationObj;

    /**
     * 姓名（在岗人员）
     */
    private String name;

    /**
     * 代理人
     */
    private String agent;

    /**
     * 是否完成
     */
    private Boolean isComplete;

    /**
     * 完成岗位
     */
    private String completeOpition;

    /**
     * -------------33---------
     */
    /**
     * （对接）规划模块长度
     */
    private int nameLendth1;
    /**
     * （对接）规划模块名
     */
    private String name1;
    /**
     * （对接）规划模块
     */
    private Boolean hasConnet1;

    /**
     * 通报时间节点
     */
    private String informTimeNode1;

    /**
     * 通报形式
     */
    private String notificationForm1;

    /**
     * 通报内容模板
     */
    private String notificationContent1;

    /**
     * 协助时间节点
     */
    private String timeNode1;

    /**
     * 协助函发送形式
     */
    private String letterForm1;

    /**
     * 协助内容模板
     */
    private String contentTemplate1;

    /**
     * 功能
     */
    private String functions1;

    /**
     * 指标序号
     */
    private String number1;

    /**
     * 指标类型
     */
    private String type1;

    /**
     * 考核指标
     */
    private String assessment1;

    /**
     * 目标值
     */
    private String targetValue1;

    /**
     * 体现
     */
    private String reflect1;

    /**
     * 达到指标对赌分
     */
    private Long gambling1;

    /**
     * 未达到指标对赌分
     */
    private Long unGambling1;

    /**
     * 是否达到指标
     */
    private Boolean isReach1;

    /**
     * 要求方
     */
    private String requestSide1;

    /**
     * 对赌方
     */
    private String gamblingSide1;

    /**
     * 规则
     */
    private String rule1;


    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartmentalGoals() {
        return departmentalGoals;
    }

    public void setDepartmentalGoals(String departmentalGoals) {
        this.departmentalGoals = departmentalGoals;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPositionGoals() {
        return positionGoals;
    }

    public void setPositionGoals(String positionGoals) {
        this.positionGoals = positionGoals;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public String getProjectStageNum() {
        return projectStageNum;
    }

    public void setProjectStageNum(String projectStageNum) {
        this.projectStageNum = projectStageNum;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public Boolean getHasMould() {
        return hasMould;
    }

    public void setHasMould(Boolean hasMould) {
        this.hasMould = hasMould;
    }

    public String getMouldStorage() {
        return mouldStorage;
    }

    public void setMouldStorage(String mouldStorage) {
        this.mouldStorage = mouldStorage;
    }

    public String getPaperStorage() {
        return paperStorage;
    }

    public void setPaperStorage(String paperStorage) {
        this.paperStorage = paperStorage;
    }

    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public String getInformTimeNode() {
        return informTimeNode;
    }

    public void setInformTimeNode(String informTimeNode) {
        this.informTimeNode = informTimeNode;
    }

    public String getNotificationForm() {
        return notificationForm;
    }

    public void setNotificationForm(String notificationForm) {
        this.notificationForm = notificationForm;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationObj() {
        return notificationObj;
    }

    public void setNotificationObj(String notificationObj) {
        this.notificationObj = notificationObj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public String getCompleteOpition() {
        return completeOpition;
    }

    public void setCompleteOpition(String completeOpition) {
        this.completeOpition = completeOpition;
    }

    public int getNameLendth1() {
        return nameLendth1;
    }

    public void setNameLendth1(int nameLendth1) {
        this.nameLendth1 = nameLendth1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public Boolean getHasConnet1() {
        return hasConnet1;
    }

    public void setHasConnet1(Boolean hasConnet1) {
        this.hasConnet1 = hasConnet1;
    }

    public String getInformTimeNode1() {
        return informTimeNode1;
    }

    public void setInformTimeNode1(String informTimeNode1) {
        this.informTimeNode1 = informTimeNode1;
    }

    public String getNotificationForm1() {
        return notificationForm1;
    }

    public void setNotificationForm1(String notificationForm1) {
        this.notificationForm1 = notificationForm1;
    }

    public String getNotificationContent1() {
        return notificationContent1;
    }

    public void setNotificationContent1(String notificationContent1) {
        this.notificationContent1 = notificationContent1;
    }

    public String getTimeNode1() {
        return timeNode1;
    }

    public void setTimeNode1(String timeNode1) {
        this.timeNode1 = timeNode1;
    }

    public String getLetterForm1() {
        return letterForm1;
    }

    public void setLetterForm1(String letterForm1) {
        this.letterForm1 = letterForm1;
    }

    public String getContentTemplate1() {
        return contentTemplate1;
    }

    public void setContentTemplate1(String contentTemplate1) {
        this.contentTemplate1 = contentTemplate1;
    }

    public String getFunctions1() {
        return functions1;
    }

    public void setFunctions1(String functions1) {
        this.functions1 = functions1;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getAssessment1() {
        return assessment1;
    }

    public void setAssessment1(String assessment1) {
        this.assessment1 = assessment1;
    }

    public String getTargetValue1() {
        return targetValue1;
    }

    public void setTargetValue1(String targetValue1) {
        this.targetValue1 = targetValue1;
    }

    public String getReflect1() {
        return reflect1;
    }

    public void setReflect1(String reflect1) {
        this.reflect1 = reflect1;
    }

    public Long getGambling1() {
        return gambling1;
    }

    public void setGambling1(Long gambling1) {
        this.gambling1 = gambling1;
    }

    public Long getUnGambling1() {
        return unGambling1;
    }

    public void setUnGambling1(Long unGambling1) {
        this.unGambling1 = unGambling1;
    }

    public Boolean getReach1() {
        return isReach1;
    }

    public void setReach1(Boolean reach1) {
        isReach1 = reach1;
    }

    public String getRequestSide1() {
        return requestSide1;
    }

    public void setRequestSide1(String requestSide1) {
        this.requestSide1 = requestSide1;
    }

    public String getGamblingSide1() {
        return gamblingSide1;
    }

    public void setGamblingSide1(String gamblingSide1) {
        this.gamblingSide1 = gamblingSide1;
    }

    public String getRule1() {
        return rule1;
    }

    public void setRule1(String rule1) {
        this.rule1 = rule1;
    }


}