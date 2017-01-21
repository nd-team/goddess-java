package com.bjike.goddess.user.session.validcorrect;

import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.enums.LoginType;

import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-07 10:20]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class Subject {
    private User user ;//登录用户
    private boolean remember; //是否记住我
    private String ip;
    private LoginType loginType; //已登录类型
    private LocalDateTime accessTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }
}
