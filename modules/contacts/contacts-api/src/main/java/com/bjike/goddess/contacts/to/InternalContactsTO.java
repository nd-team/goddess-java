package com.bjike.goddess.contacts.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.contacts.enums.ContactsStatus;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 内部通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InternalContactsTO extends BaseTO {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空", groups = {ADD.class, EDIT.class})
    private String employeeNum;

    /**
     * 部门/项目组
     */
    @NotBlank(message = "部门/项目组不能为空", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 职位
     */
    @NotBlank(message = "职位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 个人邮箱
     */
    @NotBlank(message = "个人邮箱不能为空", groups = {ADD.class, EDIT.class})
    private String personalEmail;
    /**
     * 工作邮箱
     */
    @NotBlank(message = "工作邮箱不能为空", groups = {ADD.class, EDIT.class})
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

    /**
     * 是否发送邮件
     */
    private boolean isSend;


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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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


    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ContactsStatus getStatus() {
        return status;
    }

    public void setStatus(ContactsStatus status) {
        this.status = status;
    }
}