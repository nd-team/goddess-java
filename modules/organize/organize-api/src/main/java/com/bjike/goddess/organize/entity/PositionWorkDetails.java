package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 岗位工作明细表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:41 ]
 * @Description: [ 岗位工作明细表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "organize_positionworkdetails")
public class PositionWorkDetails extends BaseEntity {

    /**
     * 公司目标
     */
    @Column(name = "goals", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司目标'")
    private String goals;

    /**
     * 公司
     */
    @Column(name = "company", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司'")
    private String company;

    /**
     * 部门目标
     */
    @Column(name = "departmentalGoals", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门目标'")
    private String departmentalGoals;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 岗位目标
     */
    @Column(name = "positionGoals", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位目标'")
    private String positionGoals;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 序号
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '序号'")
    private Long serialNumber;

    /**
     * 角度
     */
    @Column(name = "angle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '角度'")
    private String angle;

    /**
     * 维度
     */
    @Column(name = "dimension", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '维度'")
    private String dimension;

    /**
     * 分类
     */
    @Column(name = "classification", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String classification;

    /**
     * 项目阶段
     */
    @Column(name = "projectStage", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目阶段'")
    private String projectStage;

    /**
     * 项目阶段编号
     */
    @Column(name = "projectStageNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目阶段编号'")
    private String projectStageNum;

    /**
     * 功能（流程）
     */
    @Column(name = "function", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能（流程）'")
    private String function;

    /**
     * 功能（流程）目的
     */
    @Column(name = "purpose", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能（流程）目的'")
    private String purpose;

    /**
     * 功能版本
     */
    @Column(name = "version", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能版本'")
    private String version;

    /**
     * 工作频率
     */
    @Column(name = "frequency", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工作频率'")
    private String frequency;

    /**
     * 工作时间节点
     */
    @Column(name = "timeNode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工作时间节点'")
    private String timeNode;

    /**
     * 操作类型
     */
    @Column(name = "operationType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '操作类型'")
    private String operationType;

    /**
     * 工作内容
     */
    @Column(name = "workContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工作内容'")
    private String workContent;

    /**
     * 包含的附件（名称）
     */
    @Column(name = "accessories", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '包含的附件（名称）'")
    private String accessories;

    /**
     * 是否有模板
     */
    @Column(name = "is_hasMould", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有模板'", insertable = false)
    private Boolean hasMould;

    /**
     * 模板储存位置
     */
    @Column(name = "mouldStorage", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模板储存位置'")
    private String mouldStorage;

    /**
     * 工作数据存储位置
     */
    @Column(name = "paperStorage", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工作数据存储位置'")
    private String paperStorage;

    /**
     * 经验总结
     */
    @Column(name = "summarize", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '经验总结'")
    private String summarize;

    /**
     * 通报时间节点
     */
    @Column(name = "informTimeNode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '通报时间节点'")
    private String informTimeNode;

    /**
     * 通报形式
     */
    @Column(name = "notificationForm", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '通报形式'")
    private String notificationForm;

    /**
     * 通报内容
     */
    @Column(name = "notificationContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '通报内容'")
    private String notificationContent;

    /**
     * 通报对象
     */
    @Column(name = "notificationObj", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '通报对象'")
    private String notificationObj;

    /**
     * 姓名（在岗人员）
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名（在岗人员）'")
    private String name;

    /**
     * 代理人
     */
    @Column(name = "agent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '代理人'")
    private String agent;

    /**
     * 是否完成
     */
    @Column(name = "is_isComplete", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否完成'", insertable = false)
    private Boolean isComplete;

    /**
     * 完成岗位
     */
    @Column(name = "completeOpition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '完成岗位'")
    private String completeOpition;


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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
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

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getCompleteOpition() {
        return completeOpition;
    }

    public void setCompleteOpition(String completeOpition) {
        this.completeOpition = completeOpition;
    }

    public String getProjectStageNum() {
        return projectStageNum;
    }

    public void setProjectStageNum(String projectStageNum) {
        this.projectStageNum = projectStageNum;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
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
}