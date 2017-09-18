package com.bjike.goddess.organize.vo;

import java.util.List;

/**
 * 岗位工作明细表-模块表表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:58 ]
 * @Description: [ 岗位工作明细表-模块表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModulesVO {

    /**
     * id
     */
    private String id;
    /**
     * 岗位明细id
     */
    private String workDetailsId;

    /**
     * 模块名字
     */
    private String name;

    /**
     * 有无关联
     */
    private Boolean hasConnet;

    /**
     * 通报时间节点
     */
    private String informTimeNode;

    /**
     * 通报形式
     */
    private String notificationForm;

    /**
     * 通报内容模板
     */
    private String notificationContent;

    /**
     * 协助时间节点
     */
    private String timeNode;

    /**
     * 协助函发送形式
     */
    private String letterForm;

    /**
     * 协助内容模板
     */
    private String contentTemplate;

    /**
     * 功能
     */
    private String functions;

    /**
     * 指标
     */
    private List<IndicatorVO> indicatorVOList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkDetailsId() {
        return workDetailsId;
    }

    public void setWorkDetailsId(String workDetailsId) {
        this.workDetailsId = workDetailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<IndicatorVO> getIndicatorVOList() {
        return indicatorVOList;
    }

    public void setIndicatorVOList(List<IndicatorVO> indicatorVOList) {
        this.indicatorVOList = indicatorVOList;
    }
}