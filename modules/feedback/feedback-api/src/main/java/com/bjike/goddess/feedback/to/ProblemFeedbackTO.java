package com.bjike.goddess.feedback.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 问题反馈模块
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 10:38 ]
 * @Description: [ 问题反馈模块 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemFeedbackTO extends BaseTO {
    public interface TestAdd {
    }

    public interface TestEdit {
    }

    public interface TestAccept {
    }

    /**
     * 录入人
     */
    private String inputUser;

    /**
     * 问题编号(对外)
     */
    private String problemNum;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 所属项目组/部门
     */
    private String projectGroup;

    /**
     * 问题提出人
     */
    @NotBlank(message = "问题提出人不能为空", groups = {ProblemFeedbackTO.TestAdd.class})
    private String problemExhibitor;

    /**
     * 事件(背景)描述
     */
    @NotBlank(message = "事件(背景)描述不能为空", groups = {ProblemFeedbackTO.TestAdd.class})
    private String eventDescription;

    /**
     * 问题描述
     */
    @NotBlank(message = "问题描述不能为空", groups = {ProblemFeedbackTO.TestAdd.class})
    private String problemDescription;

    /**
     * 获取时间(问题提出时间)
     */
    private String getTime;

    /**
     * 期望处理时间
     */
    @NotBlank(message = "期望处理时间不能为空", groups = {ProblemFeedbackTO.TestAdd.class})
    private String expectDealTime;

    /**
     * 问题类型
     */
    @NotBlank(message = "问题类型不能为空", groups = {ProblemFeedbackTO.TestEdit.class})
    private String problemType;

    /**
     * 主功能
     */
    @NotBlank(message = "主功能不能为空", groups = {ProblemFeedbackTO.TestEdit.class})
    private String mainFunction;

    /**
     * 关联相关模块
     */
    @NotBlank(message = "关联相关模块不能为空", groups = {ProblemFeedbackTO.TestEdit.class})
    private String correlativeModule;

    /**
     * 问题责任人
     */
    @NotBlank(message = "问题责任人不能为空", groups = {ProblemFeedbackTO.TestEdit.class})
    private String problemDutyOfficer;

    /**
     * 问题来源
     */
    @NotBlank(message = "问题来源不能为空", groups = {ProblemFeedbackTO.TestEdit.class})
    private String problemSource;

    /**
     * 是否通报
     */
    private Boolean notification;

    /**
     * 通报途径
     */
    private String notificationWay;
    /**
     * 通报时间
     */
    private String notificationTime;
    /**
     * 发送对象
     */
    private String[] sendObject;

    /**
     * 问题跟进处理计划完成时间
     */
    @NotBlank(message = "问题跟进处理计划完成时间不能为空", groups = {ProblemFeedbackTO.TestAccept.class})
    private String acceptTime;

    /**
     * 意见收集完成时间
     */
    @NotBlank(message = "意见收集完成时间不能为空", groups = {ProblemFeedbackTO.TestAccept.class})
    private String ideaTime;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProblemExhibitor() {
        return problemExhibitor;
    }

    public void setProblemExhibitor(String problemExhibitor) {
        this.problemExhibitor = problemExhibitor;
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

    public String getExpectDealTime() {
        return expectDealTime;
    }

    public void setExpectDealTime(String expectDealTime) {
        this.expectDealTime = expectDealTime;
    }

    public String[] getSendObject() {
        return sendObject;
    }

    public void setSendObject(String[] sendObject) {
        this.sendObject = sendObject;
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

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getNotificationWay() {
        return notificationWay;
    }

    public void setNotificationWay(String notificationWay) {
        this.notificationWay = notificationWay;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}