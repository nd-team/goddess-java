package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 岗位工作明细表-模块表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:58 ]
 * @Description: [ 岗位工作明细表-模块表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "organize_modules")
public class Modules extends BaseEntity {

    /**
     * 岗位明细id
     */
    @Column(name = "workDetailsId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位明细id'")
    private String workDetailsId;

    /**
     * 模块名字
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模块名字'")
    private String name;

    /**
     * 有无关联
     */
    @Column(name = "is_hasConnet", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '有无关联'", insertable = false)
    private Boolean hasConnet;

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
     * 通报内容模板
     */
    @Column(name = "notificationContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '通报内容模板'")
    private String notificationContent;

    /**
     * 协助时间节点
     */
    @Column(name = "timeNode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协助时间节点'")
    private String timeNode;

    /**
     * 协助函发送形式
     */
    @Column(name = "letterForm", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协助函发送形式'")
    private String letterForm;

    /**
     * 协助内容模板
     */
    @Column(name = "contentTemplate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协助内容模板'")
    private String contentTemplate;

    /**
     * 功能
     */
    @Column(name = "functions", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能'")
    private String functions;


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
}