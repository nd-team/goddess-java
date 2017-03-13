package com.bjike.goddess.user.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.user.enums.SexType;
import com.bjike.goddess.user.enums.UserType;

import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 15:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserDetailTO extends BaseTO {

    /**
     * 性别
     */
    private SexType sex = SexType.NONE;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 用户类型
     */
    private UserType userType = UserType.CUSTOMER;
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
}
