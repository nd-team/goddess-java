package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.recruit.type.TemplateStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * 模板管理
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 10:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TemplateManageTO extends BaseTO {

    /**
     * 模板名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "模板管理不能为空")
    private String templteName;

    /**
     * 模板类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "模板类型不能为空")
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
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "模板内容不能为空")
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
    @NotNull(groups = {ADD.class, EDIT.class}, message = "模板状态不能为空")
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
