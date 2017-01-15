package com.bjike.goddess.user.dto.ext;


import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.user.enums.LoginType;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-24 09:37]
 * @Description: [用户登陆传输数据]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserLoginDTO extends BaseDTO {

    private String token; //登陆令牌

    private String ip; //登陆ip

    private String account;//登陆账号 email，phone，username

    private String password;

    private boolean rememberMe;//记住我

    private String authCode; //验证码

    private LoginType loginType; //登录类型
    private String jid; //会话id

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

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }
}
