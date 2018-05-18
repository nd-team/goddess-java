package com.bjike.goddess.task.vo;

/**
 * 内部协助模板表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:07 ]
 * @Description: [ 内部协助模板表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InnerTemplateVO {

    /**
     * id
     */
    private String id;
    /**
     * 创建人
     */
    private String creater;

    /**
     * 项目组
     */
    private String depart;

    /**
     * 岗位
     */
    private String position;

    /**
     * 协助说明情况
     */
    private String situation;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板内容编写
     */
    private String content;

    /**
     * 邮件发送对象
     */
    private String sendObject;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

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

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }
}