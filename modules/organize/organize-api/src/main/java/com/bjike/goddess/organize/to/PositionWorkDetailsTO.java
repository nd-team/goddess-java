package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.organize.entity.Modules;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 岗位工作明细表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:41 ]
 * @Description: [ 岗位工作明细表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionWorkDetailsTO extends BaseTO {

    /**
     * 公司目标
     */
    @NotBlank(message = "公司目标不能为空", groups = {ADD.class, EDIT.class})
    private String goals;

    /**
     * 公司
     */
    @NotBlank(message = "公司不能为空", groups = {ADD.class, EDIT.class})
    private String company;

    /**
     * 部门目标
     */
    @NotBlank(message = "部门目标不能为空", groups = {ADD.class, EDIT.class})
    private String departmentalGoals;

    /**
     * 项目组/部门
     */
    @NotBlank(message = "项目组/部门不能为空", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 岗位目标
     */
    @NotBlank(message = "岗位目标不能为空", groups = {ADD.class, EDIT.class})
    private String positionGoals;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 序号
     */
    @NotNull(message = "序号不能为空", groups = {ADD.class, EDIT.class})
    private Long serialNumber;

    /**
     * 角度
     */
    @NotBlank(message = "角度不能为空", groups = {ADD.class, EDIT.class})
    private String angle;

    /**
     * 维度
     */
    @NotBlank(message = "维度不能为空", groups = {ADD.class, EDIT.class})
    private String dimension;

    /**
     * 分类
     */
    @NotBlank(message = "分类不能为空", groups = {ADD.class, EDIT.class})
    private String classification;

    /**
     * 项目阶段
     */
    @NotBlank(message = "项目阶段不能为空", groups = {ADD.class, EDIT.class})
    private String projectStage;

    /**
     * 功能（流程）
     */
    @NotBlank(message = "功能不能为空", groups = {ADD.class, EDIT.class})
    private String function;

    /**
     * 工作频率
     */
    @NotBlank(message = "工作频率不能为空", groups = {ADD.class, EDIT.class})
    private String frequency;

    /**
     * 工作时间节点
     */
    @NotBlank(message = "工作时间节点不能为空", groups = {ADD.class, EDIT.class})
    private String timeNode;

    /**
     * 操作类型
     */
    @NotBlank(message = "操作类型不能为空", groups = {ADD.class, EDIT.class})
    private String operationType;

    /**
     * 工作内容
     */
    @NotBlank(message = "工作内容不能为空", groups = {ADD.class, EDIT.class})
    private String workContent;

    /**
     * 包含的附件（名称）
     */
    @NotBlank(message = "包含的附件不能为空", groups = {ADD.class, EDIT.class})
    private String accessories;

    /**
     * 是否有模板
     */
    @NotNull(message = "是否有模板不能为空", groups = {ADD.class, EDIT.class})
    private Boolean hasMould;

    /**
     * 模板储存位置
     */
    @NotBlank(message = "模板储存位置不能为空", groups = {ADD.class, EDIT.class})
    private String mouldStorage;

    /**
     * 工作数据存储位置
     */
    @NotBlank(message = "工作数据存储位置不能为空", groups = {ADD.class, EDIT.class})
    private String paperStorage;

    /**
     * 经验总结
     */
    @NotBlank(message = "经验总结不能为空", groups = {ADD.class, EDIT.class})
    private String summarize;

    /**
     * 通报时间节点
     */
    @NotBlank(message = "通报时间节点不能为空", groups = {ADD.class, EDIT.class})
    private String informTimeNode;

    /**
     * 通报形式
     */
    @NotBlank(message = "通报形式不能为空", groups = {ADD.class, EDIT.class})
    private String notificationForm;

    /**
     * 通报内容
     */
    @NotBlank(message = "通报内容不能为空", groups = {ADD.class, EDIT.class})
    private String notificationContent;

    /**
     * 通报对象
     */
    @NotBlank(message = "通报对象不能为空", groups = {ADD.class, EDIT.class})
    private String notificationObj;

    /**
     * 姓名（在岗人员）
     */
    @NotBlank(message = "姓名（在岗人员）不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 代理人
     */
    @NotBlank(message = "代理人不能为空", groups = {ADD.class, EDIT.class})
    private String agent;

    /**
     * 是否完成
     */
    @NotNull(message = "是否完成不能为空", groups = {ADD.class, EDIT.class})
    private Boolean isComplete;

    /**
     * 完成岗位
     */
    @NotBlank(message = "完成岗位不能为空", groups = {ADD.class, EDIT.class})
    private String completeOpition;

    /**
     * 模块
     */
//    @NotBlank(message = "模块不能为空", groups = {ADD.class, EDIT.class})
    private List<ModulesTO> modulesTOList;

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

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public List<ModulesTO> getModulesTOList() {
        return modulesTOList;
    }

    public void setModulesTOList(List<ModulesTO> modulesTOList) {
        this.modulesTOList = modulesTOList;
    }
}