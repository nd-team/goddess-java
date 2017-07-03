package com.bjike.goddess.user.vo;

import com.bjike.goddess.user.enums.LoginType;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-18 11:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserLoginLogVO {
    /**
     * id
     */
    private String id;
    /**
     * 登录时间
     */
    private String loginTime;
    /**
     * 登录地点
     */
    private String loginAddress;
    /**
     * ip地址
     */
    private String loginIp;
    /**
     * 登录方式
     */
    private LoginType loginType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
