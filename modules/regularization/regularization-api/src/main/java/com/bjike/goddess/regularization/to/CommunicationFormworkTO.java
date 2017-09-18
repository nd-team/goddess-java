package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.regularization.type.ModuleName;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank(message = "模板名称不能为空" , groups = {ADD.class, EDIT.class})
    private ModuleName moduleName;

    /**
     * 所属类型
     */
    @NotBlank(message = "所属类型不能为空" , groups = {ADD.class, EDIT.class})
    private String belongType;

    /**
     * 分类
     */
    @NotBlank(message = "分类不能为空" , groups = {ADD.class, EDIT.class})
    private String classifi;

    /**
     * 触发字段
     */
    @NotBlank(message = "触发字段不能为空" , groups = {ADD.class, EDIT.class})
    private String triggerField;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空" , groups = {ADD.class, EDIT.class})
    private String title;

    /**
     * 邮件内容模板
     */
    @NotBlank(message = "邮件内容模板不能为空" , groups = {ADD.class, EDIT.class})
    private String emailModule;

    /**
     * 例子
     */
    @NotBlank(message = "例子不能为空" , groups = {ADD.class, EDIT.class})
    private String example;

    /**
     * 附件
     */
    @NotBlank(message = "附件不能为空" , groups = {ADD.class, EDIT.class})
    private String attachment;

    /**
     * 主送对象
     */
    @NotBlank(message = "主送对象不能为空" , groups = {ADD.class, EDIT.class})
    private String lordSendObj;

    /**
     * 抄送对象
     */
    private String ccObj;

    /**
     * 途径
     */
    @NotBlank(message = "途径不能为空" , groups = {ADD.class, EDIT.class})
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