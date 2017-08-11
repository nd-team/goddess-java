package com.bjike.goddess.feedback.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 各类沟通模板
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:31 ]
 * @Description: [ 各类沟通模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ConnectTO extends BaseTO {
    public interface TestEdit{}

    /**
     * 模板编号
     */
    private String templateNum;

    /**
     * 所属类型
     */
    private String type;

    /**
     * 分类
     */
    private String sorting;

    /**
     * 触发字段
     */
    private String triggerField;

    /**
     * 标题
     */
    private String title;

    /**
     * 邮件内容模板
     */
    private String mailContentTemplate;

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
    @NotBlank(message = "主送对象不能为空",groups = {ConnectTO.TestEdit.class})
    private String mainObject;

    /**
     * 抄送对象
     */
    @NotBlank(message = "抄送对象不能为空",groups = {ConnectTO.TestEdit.class})
    private String copyObject;

    /**
     * 途径
     */
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