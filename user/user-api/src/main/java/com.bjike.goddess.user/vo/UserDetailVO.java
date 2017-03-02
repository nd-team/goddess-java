package com.bjike.goddess.user.vo;

import com.bjike.goddess.common.api.vo.BaseVO;
import com.bjike.goddess.user.enums.SexType;
import com.bjike.goddess.user.enums.UserType;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-02 09:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserDetailVO  extends BaseVO{


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
}
