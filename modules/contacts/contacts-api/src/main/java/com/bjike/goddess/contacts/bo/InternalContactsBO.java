package com.bjike.goddess.contacts.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contacts.enums.ContactsStatus;
import com.bjike.goddess.contacts.enums.Status;

/**
 * 内部通讯录业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InternalContactsBO extends BaseBO {

//    /**
//     * 用户ID
//     */
//    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 地区
     */
    private String area;

    /**
     * 姓名
     */
    private String name;

    /**
     * 员工编号
     */
    private String employeeNum;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 职位
     */
    private String position;

    /**
     * 联系电话
     */
    private String phone;
    /**
     * 个人邮箱
     */
    private String personalEmail;
    /**
     * 工作邮箱
     */
    private String workEmail;
    /**
     * 原始密码
     */
    private String primalPassword;
    /**
     * 更改密码
     */
    private String updatePassword;
    /**
     * 工作邮箱检测是否通过
     */
    private Boolean workEmailPass;

    /**
     * 集团号
     */
    private String bloc;

    /**
     * 联系电话1
     */
    private String phoneNumberA;
    /**
     * 联系电话2
     */
    private String phoneNumberB;

    /**
     * 联系电话3
     */
    private String phoneNumberC;

    /**
     * 联系电话4
     */
    private String phoneNumberD;


    /**
     * QQ号
     */
    private String qq;

    /**
     * 微信号
     */
    private String weChat;

    /**
     * 紧急联系人
     */
    private String emergency;

    /**
     * 紧急联系人电话
     */
    private String emergencyPhone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private ContactsStatus status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public ContactsStatus getStatus() {
        return status;
    }

    public void setStatus(ContactsStatus status) {
        this.status = status;
    }
}