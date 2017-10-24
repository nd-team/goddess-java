package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 内部协助模板
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:07 ]
 * @Description: [ 内部协助模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InnerTemplateTO extends BaseTO {
    public interface SEND{}

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "项目组不能为空")
    private String depart;

    /**
     * 岗位
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "岗位不能为空")
    private String position;

    /**
     * 协助说明情况
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "协助说明情况不能为空")
    private String situation;

    /**
     * 模板名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "模板名称不能为空")
    private String name;

    /**
     * 模板内容编写
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "模板内容编写不能为空")
    private String content;

    /**
     * 邮件发送对象
     */
    @NotNull(groups = InnerTemplateTO.SEND.class,message = "邮件发送对象不能为空")
    private String[] sendObjects;


    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getSendObjects() {
        return sendObjects;
    }

    public void setSendObjects(String[] sendObjects) {
        this.sendObjects = sendObjects;
    }
}