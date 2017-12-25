package com.bjike.goddess.organize.vo;

import java.io.Serializable;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-05-08 10:08]
 * @Description: [ 用户手机登录后显示用户信息 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PhoneLoginUserInfoVO implements Serializable {

    /**
     * 姓名
     */
    String userName;
    /**
     * 员工编号
     */
    String empNumer;
    /**
     * 性别
     */
    String sex;
    /**
     * 体系
     */
    String hierarchyName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNumer() {
        return empNumer;
    }

    public void setEmpNumer(String empNumer) {
        this.empNumer = empNumer;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }
}
