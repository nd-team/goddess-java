package com.bjike.goddess.feedback.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 各类沟通模板
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:31 ]
 * @Description: [ 各类沟通模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "feedback_connect")
public class Connect extends BaseEntity {

    /**
     * 模板编号
     */
    @Column(name = "templateNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模板编号'")
    private String templateNum;

    /**
     * 所属类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属类型'")
    private String type;

    /**
     * 分类
     */
    @Column(name = "sorting", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String sorting;

    /**
     * 触发字段
     */
    @Column(name = "triggerField", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '触发字段'")
    private String triggerField;

    /**
     * 标题
     */
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '标题'")
    private String title;

    /**
     * 邮件内容模板
     */
    @Column(name = "mailContentTemplate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '邮件内容模板'")
    private String mailContentTemplate;

    /**
     * 例子
     */
    @Column(name = "example", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '例子'")
    private String example;

    /**
     * 附件
     */
    @Column(name = "attachment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '附件'")
    private String attachment;

    /**
     * 主送对象
     */
    @Column(name = "mainObject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '主送对象'")
    private String mainObject;

    /**
     * 抄送对象
     */
    @Column(name = "copyObject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '抄送对象'")
    private String copyObject;

    /**
     * 途径
     */
    @Column(name = "way", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '途径'")
    private String way;


    public String getTemplateNum() {
        return templateNum;
    }

    public void setTemplateNum(String templateNum) {
        this.templateNum = templateNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
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

    public String getMailContentTemplate() {
        return mailContentTemplate;
    }

    public void setMailContentTemplate(String mailContentTemplate) {
        this.mailContentTemplate = mailContentTemplate;
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

    public String getMainObject() {
        return mainObject;
    }

    public void setMainObject(String mainObject) {
        this.mainObject = mainObject;
    }

    public String getCopyObject() {
        return copyObject;
    }

    public void setCopyObject(String copyObject) {
        this.copyObject = copyObject;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}