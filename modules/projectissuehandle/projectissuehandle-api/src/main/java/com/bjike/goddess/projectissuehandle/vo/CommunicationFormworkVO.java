package com.bjike.goddess.projectissuehandle.vo;

/**
 * 各类沟通模板表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-09 10:17 ]
 * @Description: [ 各类沟通模板表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommunicationFormworkVO {

    /**
     * id
     */
    private String id;
    /**
     * 所属类型
     */
    private String belongType;

    /**
     * 分类
     */
    private String classification;

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
    private String emailModule;

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
    private String lordSendObj;

    /**
     * 抄送对象
     */
    private String ccObj;

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