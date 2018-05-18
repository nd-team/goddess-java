package com.bjike.goddess.managepromotion.vo;

/**
 * 各类交流沟通模板表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 11:49 ]
 * @Description: [ 各类交流沟通模板表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExchangeTemplateVO {

    /**
     * id
     */
    private String id;
    /**
     * 模板名称
     */
    private String name;

    /**
     * 所属类型
     */
    private String type;

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
     * 邮件内容模板
     */
    private String content;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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