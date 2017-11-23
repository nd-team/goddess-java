package com.bjike.goddess.contacts.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contacts.enums.ContactsStatus;
import com.bjike.goddess.contacts.enums.Status;
import com.bjike.goddess.user.enums.SexType;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-04 09:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MobileInternalContactsBO extends BaseBO {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户头像
     */
    private String headSculpture;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户性别
     */
    private SexType sex;

    /**
     * 地区
     */
    private String area;

    /**
     * 员工编号
     */
    private String number;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 职位
     */
    private String position;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 集团号
     */
    private String bloc;

    /**
     * 电话2
     */
    private String phoneNumber;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 微信号
     */
    private String weChat;

//    /**
//     * 紧急联系人
//     */
//    private String emergency;

//    /**
//     * 紧急联系人电话
//     */
//    private String emergencyPhone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private ContactsStatus status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ContactsStatus getStatus() {
        return status;
    }

    public void setStatus(ContactsStatus status) {
        this.status = status;
    }
}
