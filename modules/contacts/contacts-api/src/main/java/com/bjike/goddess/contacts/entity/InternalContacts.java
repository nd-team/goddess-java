package com.bjike.goddess.contacts.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.contacts.enums.ContactsStatus;
import com.bjike.goddess.contacts.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


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

//    /**
//     * 用户ID
//     */
//    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用户ID'")
//    private String userId;
    /**
     * 更新时间
     */
    @Column(name = "updateTime", columnDefinition = "DATE   COMMENT '更新时间'")
    private LocalDate updateTime;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "employeeNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String employeeNum;

    /**
     * 部门/项目组
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门/项目组'")
    private String department;

    /**
     * 职位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String position;

    /**
     * 联系电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String phone;
    /**
     * 个人邮箱
     */
    @Column(name = "personalEmail", columnDefinition = "VARCHAR(255)   COMMENT '个人邮箱'")
    private String personalEmail;
    /**
     * 工作邮箱
     */
    @Column(name = "workEmail", columnDefinition = "VARCHAR(255)   COMMENT '工作邮箱'")
    private String workEmail;
    /**
     * 原始密码
     */
    @Column(name = "primalPassword", columnDefinition = "VARCHAR(255)   COMMENT '原始密码'")
    private String primalPassword;
    /**
     * 更改密码
     */
    @Column(name = "updatePassword", columnDefinition = "VARCHAR(255)   COMMENT '更改密码'")
    private String updatePassword;
    /**
     * 工作邮箱检测是否通过
     */
    @Column(name = "is_workEmailPass", columnDefinition = "TINYINT(1)   COMMENT '工作邮箱检测是否通过'")
    private Boolean workEmailPass;

    /**
     * 集团号
     */
    @Column(name = "bloc", columnDefinition = "VARCHAR(255)   COMMENT '集团号'")
    private String bloc;

    /**
     * 联系电话1
     */
    @Column(name = "phoneNumberA", columnDefinition = "VARCHAR(255)   COMMENT '联系电话1'")
    private String phoneNumberA;
    /**
     * 联系电话2
     */
    @Column(name = "phoneNumberB", columnDefinition = "VARCHAR(255)   COMMENT '联系电话2'")
    private String phoneNumberB;

    /**
     * 联系电话3
     */
    @Column(name = "phoneNumberC", columnDefinition = "VARCHAR(255)   COMMENT '联系电话3'")
    private String phoneNumberC;

    /**
     * 联系电话4
     */
    @Column(name = "phoneNumberD", columnDefinition = "VARCHAR(255)   COMMENT '联系电话4'")
    private String phoneNumberD;


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
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0   COMMENT '状态'")
    private ContactsStatus status;

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPrimalPassword() {
        return primalPassword;
    }

    public void setPrimalPassword(String primalPassword) {
        this.primalPassword = primalPassword;
    }

    public String getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(String updatePassword) {
        this.updatePassword = updatePassword;
    }

    public Boolean getWorkEmailPass() {
        return workEmailPass;
    }

    public void setWorkEmailPass(Boolean workEmailPass) {
        this.workEmailPass = workEmailPass;
    }

    public String getPhoneNumberA() {
        return phoneNumberA;
    }

    public void setPhoneNumberA(String phoneNumberA) {
        this.phoneNumberA = phoneNumberA;
    }

    public String getPhoneNumberB() {
        return phoneNumberB;
    }

    public void setPhoneNumberB(String phoneNumberB) {
        this.phoneNumberB = phoneNumberB;
    }

    public String getPhoneNumberC() {
        return phoneNumberC;
    }

    public void setPhoneNumberC(String phoneNumberC) {
        this.phoneNumberC = phoneNumberC;
    }

    public String getPhoneNumberD() {
        return phoneNumberD;
    }

    public void setPhoneNumberD(String phoneNumberD) {
        this.phoneNumberD = phoneNumberD;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
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

    public ContactsStatus getStatus() {
        return status;
    }

    public void setStatus(ContactsStatus status) {
        this.status = status;
    }
}