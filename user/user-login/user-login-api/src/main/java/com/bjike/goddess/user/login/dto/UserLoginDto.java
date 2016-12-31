package com.bjike.goddess.user.login.dto;

import com.bjike.goddess.dbs.jpa.dto.BaseDto;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-24 09:37]
 * @Description: [用户登陆传输数据]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class UserLoginDto extends BaseDto{

    private String token; //登陆令牌

    private String ip; //登陆ip

    private String account;//登陆账号 email，phone，username

    private String password;

    private boolean rememberMe;//记住我

    private String authCode; //验证码

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
