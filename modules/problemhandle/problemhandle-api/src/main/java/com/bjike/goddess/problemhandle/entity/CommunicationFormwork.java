package com.bjike.goddess.problemhandle.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 各类沟通模板
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-09 10:17 ]
 * @Description: [ 各类沟通模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectissuehandle_communicationformwork")
public class CommunicationFormwork extends BaseEntity {

    /**
     * 所属类型
     */
    @Column(name = "belongType", columnDefinition = "VARCHAR(255)   COMMENT '所属类型'")
    private String belongType;

    /**
     * 分类
     */
    @Column(name = "classification", nullable = false,unique = true, columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String classification;

    /**
     * 触发字段
     */
    @Column(name = "triggerField", columnDefinition = "VARCHAR(255)   COMMENT '触发字段'")
    private String triggerField;

    /**
     * 标题
     */
    @Column(name = "title", columnDefinition = "VARCHAR(255)   COMMENT '标题'")
    private String title;

    /**
     * 邮件内容模板
     */
    @Column(name = "emailModule", nullable = false, columnDefinition = "TEXT   COMMENT '邮件内容模板'")
    private String emailModule;

    /**
     * 例子
     */
    @Column(name = "example", columnDefinition = "TEXT   COMMENT '例子'")
    private String example;

    /**
     * 附件
     */
    @Column(name = "attachment", columnDefinition = "VARCHAR(255)   COMMENT '附件'")
    private String attachment;

    /**
     * 主送对象
     */
    @Column(name = "lordSendObj", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '主送对象'")
    private String lordSendObj;

    /**
     * 抄送对象
     */
    @Column(name = "ccObj", columnDefinition = "TEXT   COMMENT '抄送对象'")
    private String ccObj;

    /**
     * 途径
     */
    @Column(name = "way", columnDefinition = "VARCHAR(255)   COMMENT '途径'")
    private String way;


    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getTriggerField() {
        return triggerField;
    }

    public void setTriggerField(String triggerField) {
        this.triggerField = triggerField;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmailModule() {
        return emailModule;
    }

    public void setEmailModule(String emailModule) {
        this.emailModule = emailModule;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getLordSendObj() {
        return lordSendObj;
    }

    public void setLordSendObj(String lordSendObj) {
        this.lordSendObj = lordSendObj;
    }

    public String getCcObj() {
        return ccObj;
    }

    public void setCcObj(String ccObj) {
        this.ccObj = ccObj;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}