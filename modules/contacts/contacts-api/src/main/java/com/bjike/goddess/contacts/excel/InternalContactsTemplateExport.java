package com.bjike.goddess.contacts.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.contacts.enums.ContactsStatus;
import com.bjike.goddess.contacts.enums.Status;

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
public class InternalContactsTemplateExport extends BaseEntity {

//    /**
//     * 用户ID
//     */
//    @ExcelHeader(name = "用户ID" , notNull = true)
//    private String userId;

    /**
     * 更新时间
     */
    @ExcelHeader(name = "更新时间")
    private LocalDate updateTime;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String name;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号", notNull = true)
    private String employeeNum;

    /**
     * 部门/项目组
     */
    @ExcelHeader(name = "部门/项目组", notNull = true)
    private String department;

    /**
     * 职位
     */
    @ExcelHeader(name = "职位", notNull = true)
    private String position;

    /**
     * 联系电话
     */
    @ExcelHeader(name = "联系电话", notNull = true)
    private String phone;

    /**
     * 个人邮箱
     */
    @ExcelHeader(name = "个人邮箱", notNull = false)
    private String personalEmail;
    /**
     * 工作邮箱
     */
    @ExcelHeader(name = "工作邮箱", notNull = false)
    private String workEmail;
    /**
     * 原始密码
     */
    @ExcelHeader(name = "原始密码", notNull = false)
    private String primalPassword;
    /**
     * 更改密码
     */
    @ExcelHeader(name = "更改密码", notNull = false)
    private String updatePassword;
    /**
     * 工作邮箱检测是否通过
     */
    @ExcelHeader(name = "工作邮箱检测是否通过", notNull = false)
    private String workEmailPass;

    /**
     * 集团号
     */
    @ExcelHeader(name = "集团号", notNull = false)
    private String bloc;

    /**
     * 联系电话1
     */
    @ExcelHeader(name = "联系电话1", notNull = false)
    private String phoneNumberA;
    /**
     * 联系电话2
     */
    @ExcelHeader(name = "联系电话2", notNull = false)
    private String phoneNumberB;

    /**
     * 联系电话3
     */
    @ExcelHeader(name = "联系电话3", notNull = false)
    private String phoneNumberC;

    /**
     * 联系电话4
     */
    @ExcelHeader(name = "联系电话4", notNull = false)
    private String phoneNumberD;
    /**
     * QQ号
     */
    @ExcelHeader(name = "QQ号", notNull = false)
    private String qq;

    /**
     * 微信号
     */
    @ExcelHeader(name = "微信号", notNull = false)
    private String weChat;

    /**
     * 紧急联系人
     */
    @ExcelHeader(name = "紧急联系人", notNull = false)
    private String emergency;

    /**
     * 紧急联系人电话
     */
    @ExcelHeader(name = "紧急联系人电话", notNull = false)
    private String emergencyPhone;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = false)
    private String remark;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态", notNull = true)
    private ContactsStatus status;

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

    public String getWorkEmailPass() {
        return workEmailPass;
    }

    public void setWorkEmailPass(String workEmailPass) {
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