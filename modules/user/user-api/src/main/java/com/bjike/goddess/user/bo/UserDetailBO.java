package com.bjike.goddess.user.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.user.enums.SexType;
import com.bjike.goddess.user.enums.UserType;

/**
 * 用户详情业务数据传输
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-23 14:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserDetailBO extends BaseBO {

    /**
     * 性别
     */
    private SexType sex ;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 用户类型
     */
    private UserType userType ;
    /**
     * 所在地址
     */
    private String address;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 出生年月
     */
    private String birthday;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 部门id
     */
    private String departmentId;
    /**
     * 部门名
     */
    private String departmentName;
    /**
     * 组id
     */
    private String groupId;
    /**
     * 组名
     */
    private String groupName;
    /**
     * 职位id
     */
    private String positionId;
    /**
     * 职位名
     */
    private String positionName;

    /**
     * 生日月份
     */
    private Integer birthMonth;

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }
}
