package com.bjike.goddess.contacts.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.contacts.enums.Status;


/**
 * 内部通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InternalContactsExcel extends BaseEntity {

//    /**
//     * 用户ID
//     */
//    @ExcelHeader(name = "用户ID" , notNull = true)
//    private String userId;

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
     * 邮箱
     */
    @ExcelHeader(name = "邮箱", notNull = false)
    private String email;

    /**
     * 集团号
     */
    @ExcelHeader(name = "集团号", notNull = false)
    private String bloc;

    /**
     * 联系电话2
     */
    @ExcelHeader(name = "联系电话2", notNull = false)
    private String phoneNumber;

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
    private Status status;

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