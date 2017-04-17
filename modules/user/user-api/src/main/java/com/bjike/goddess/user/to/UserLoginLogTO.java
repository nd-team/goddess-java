package com.bjike.goddess.user.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.enums.LoginType;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 17:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserLoginLogTO extends BaseTO {

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
    /**
     * 登录方式
     */
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
