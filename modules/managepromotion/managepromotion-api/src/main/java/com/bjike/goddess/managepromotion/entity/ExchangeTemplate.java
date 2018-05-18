package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 各类交流沟通模板
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 11:49 ]
 * @Description: [ 各类交流沟通模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_exchangetemplate")
public class ExchangeTemplate extends BaseEntity {

    /**
     * 模板名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '模板名称'")
    private String name;

    /**
     * 所属类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '所属类型'")
    private String type;

    /**
     * 分类
     */
    @Column(name = "classify", columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String classify;

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
    @Column(name = "content", columnDefinition = "TEXT  COMMENT '邮件内容模板'")
    private String content;

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
    @Column(name = "mainObject", columnDefinition = "VARCHAR(255)   COMMENT '主送对象'")
    private String mainObject;

    /**
     * 抄送对象
     */
    @Column(name = "copyObject", columnDefinition = "VARCHAR(255)   COMMENT '抄送对象'")
    private String copyObject;

    /**
     * 途径
     */
    @Column(name = "way", columnDefinition = "VARCHAR(255)   COMMENT '途径'")
    private String way;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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