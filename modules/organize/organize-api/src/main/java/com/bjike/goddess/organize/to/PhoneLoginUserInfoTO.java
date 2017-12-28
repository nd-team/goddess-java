package com.bjike.goddess.organize.to;

import java.io.Serializable;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-05-08 10:08]
 * @Description: [ 用户手机登录后显示用户信息 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PhoneLoginUserInfoTO implements Serializable {

    /**
     * 姓名
     */
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
