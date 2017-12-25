package com.bjike.goddess.organize.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 岗位工作明细表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 岗位工作明细表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleAndIndicatorExport1 extends BaseTO {

    /**
     * 模块
     */
    @ExcelHeader(name = "模块", notNull = true)
    private Boolean hasConnet;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "通报时间节点", notNull = true)
    private String informTimeNode;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "通报形式", notNull = true)
    private String notificationForm;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "通报内容模板", notNull = true)
    private String notificationContent;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "协助时间节点", notNull = true)
    private String timeNode;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "协助函发送形式", notNull = true)
    private String letterForm;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "协助内容模板", notNull = true)
    private String contentTemplate;

    /**
     * 功能
     */
    @ExcelHeader(name = "功能", notNull = true)
    private String functions;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "指标序号", notNull = true)
    private String number;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "指标类型", notNull = true)
    private String type;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "考核指标", notNull = true)
    private String assessment;

    /**
     * 目标值
     */
    @ExcelHeader(name = "目标值", notNull = true)
    private String targetValue;

    /**
     * 体现
     */
    @ExcelHeader(name = "体现", notNull = true)
    private String reflect;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "达到指标对赌分", notNull = true)
    private Long gambling;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "未达到指标对赌分", notNull = true)
    private Long unGambling;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "是否达到指标", notNull = true)
    private Boolean isReach;

    /**
     * 要求方
     */
    @ExcelHeader(name = "要求方", notNull = true)
    private String requestSide;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "对赌方", notNull = true)
    private String gamblingSide;

    /**
     * 规则
     */
    @ExcelHeader(name = "规则", notNull = true)
    private String rule;

    public Boolean getHasConnet() {
        return hasConnet;
    }

    public void setHasConnet(Boolean hasConnet) {
        this.hasConnet = hasConnet;
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

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }

    public String getLetterForm() {
        return letterForm;
    }

    public void setLetterForm(String letterForm) {
        this.letterForm = letterForm;
    }

    public String getContentTemplate() {
        return contentTemplate;
    }

    public void setContentTemplate(String contentTemplate) {
        this.contentTemplate = contentTemplate;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getReflect() {
        return reflect;
    }

    public void setReflect(String reflect) {
        this.reflect = reflect;
    }

    public Long getGambling() {
        return gambling;
    }

    public void setGambling(Long gambling) {
        this.gambling = gambling;
    }

    public Long getUnGambling() {
        return unGambling;
    }

    public void setUnGambling(Long unGambling) {
        this.unGambling = unGambling;
    }

    public Boolean getReach() {
        return isReach;
    }

    public void setReach(Boolean reach) {
        isReach = reach;
    }

    public String getRequestSide() {
        return requestSide;
    }

    public void setRequestSide(String requestSide) {
        this.requestSide = requestSide;
    }

    public String getGamblingSide() {
        return gamblingSide;
    }

    public void setGamblingSide(String gamblingSide) {
        this.gamblingSide = gamblingSide;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}