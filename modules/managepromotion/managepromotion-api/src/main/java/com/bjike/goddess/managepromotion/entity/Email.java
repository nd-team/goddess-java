package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 发送邮件
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 02:16 ]
 * @Description: [ 发送邮件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_email")
public class Email extends BaseEntity {

    /**
     * 发送类型
     */
    @Column(name = "sendType", columnDefinition = "VARCHAR(255)   COMMENT '发送类型'")
    private String sendType;

    /**
     * 发送内容
     */
    @Column(name = "sendContent",  columnDefinition = "VARCHAR(255)   COMMENT '发送内容'")
    private String sendContent;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 创建人
     */
    @Column(name = "founder", columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String founder;

    /**
     * 发送对象
     */
    @Column(name = "sendObject", columnDefinition = "VARCHAR(255)   COMMENT '发送对象'")
    private String sendObject;


    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }
}