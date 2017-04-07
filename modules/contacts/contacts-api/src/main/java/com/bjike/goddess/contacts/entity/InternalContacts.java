package com.bjike.goddess.contacts.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 内部通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contacts_internal_contacts")
public class InternalContacts extends BaseEntity {

    /**
     * 用户ID
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用户ID'")
    private String user_id;

    /**
     * 联系电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String email;

    /**
     * 集团号
     */
    @Column(name = "bloc", columnDefinition = "VARCHAR(255)   COMMENT '集团号'")
    private String bloc;

    /**
     * 联系电话2
     */
    @Column(name = "phoneNumber", columnDefinition = "VARCHAR(255)   COMMENT '联系电话2'")
    private String phoneNumber;

    /**
     * QQ号
     */
    @Column(name = "qq", columnDefinition = "VARCHAR(255)   COMMENT 'QQ号'")
    private String qq;

    /**
     * 微信号
     */
    @Column(name = "weChat", columnDefinition = "VARCHAR(255)   COMMENT '微信号'")
    private String weChat;

    /**
     * 紧急联系人
     */
    @Column(name = "emergency", columnDefinition = "VARCHAR(255)   COMMENT '紧急联系人'")
    private String emergency;

    /**
     * 紧急联系人电话
     */
    @Column(name = "emergencyPhone", columnDefinition = "VARCHAR(255)   COMMENT '紧急联系人电话'")
    private String emergencyPhone;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0  COMMENT '状态'")
    private Status status;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}