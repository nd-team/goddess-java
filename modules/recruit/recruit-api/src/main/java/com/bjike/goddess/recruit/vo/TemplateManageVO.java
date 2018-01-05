package com.bjike.goddess.recruit.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.recruit.type.TemplateStatus;

/**
 * 模板管理
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 17:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TemplateManageVO {

    /**
     * id
     */
    private String id;

    /**
     * 模板名称
     */
    private String templteName;

    /**
     * 模板类型
     */
    private String templateType;

    /**
     * 分类
     */
    private String classify;
    /**
     * 触发字段
     */
    private String triggerField;
    /**
     * 标题
     */
    private String title;

    /**
     * 模板内容
     */
    private String templateContent;
    /**
     * 例子
     */
    private String example;
    /**
     * 附件
     */
    private String attachment;
    /**
     * 主送对象
     */
    private String mainObject;
    /**
     * 抄送对象
     */
    private String copyObject;
    /**
     * 途径
     */
    private String way;

    /**
     * 模板状态
     */
    private TemplateStatus templateStatus;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplteName() {
        return templteName;
    }

    public void setTemplteName(String templteName) {
        this.templteName = templteName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public TemplateStatus getTemplateStatus() {
        return templateStatus;
    }

    public void setTemplateStatus(TemplateStatus templateStatus) {
        this.templateStatus = templateStatus;
    }
}
