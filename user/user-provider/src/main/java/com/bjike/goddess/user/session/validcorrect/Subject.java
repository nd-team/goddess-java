package com.bjike.goddess.user.session.validcorrect;

import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.enums.LoginType;

import java.util.HashSet;
import java.util.Set;

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
    private Set<LoginType> loginTypes = new HashSet(0); //已登录类型
    private Set<String> permissions = new HashSet<>(0);//拥有权限



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

    public Set getLoginTypes() {
        return loginTypes;
    }

    public void setLoginTypes(Set loginTypes) {
        this.loginTypes = loginTypes;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
