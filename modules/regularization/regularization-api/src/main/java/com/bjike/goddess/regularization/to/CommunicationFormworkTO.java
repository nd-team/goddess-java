package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.regularization.type.ModuleName;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 各类交流沟通模块
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 03:06 ]
 * @Description: [ 各类交流沟通模块 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommunicationFormworkTO extends BaseTO {

    /**
     * 模板名称
     */
    private ModuleName moduleName;

    /**
     * 所属类型
     */
    private String belongType;

    /**
     * 分类
     */
    private String classifi;

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


    public ModuleName getModuleName() {
        return moduleName;
    }

    public void setModuleName(ModuleName moduleName) {
        this.moduleName = moduleName;
    }

    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType;
    }

    public String getClassifi() {
        return classifi;
    }

    public void setClassifi(String classifi) {
        this.classifi = classifi;
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