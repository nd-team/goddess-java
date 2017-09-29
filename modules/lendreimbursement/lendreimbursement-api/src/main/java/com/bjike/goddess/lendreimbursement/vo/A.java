package com.bjike.goddess.lendreimbursement.vo;

import jdk.nashorn.internal.objects.annotations.Property;

import java.io.Serializable;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-09-28 19:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class A implements Serializable {

    @Property(name = "userName" )
    private String userName;

    @Property(name = "password" )
    private String password;

    @Property(name = "qq" )
    private String qq;

    @Property(name = "wechat" )
    private String wechat;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A a = (A) o;

        if (userName != null ? !userName.equals(a.userName) : a.userName != null) return false;
        if (password != null ? !password.equals(a.password) : a.password != null) return false;
        if (qq != null ? !qq.equals(a.qq) : a.qq != null) return false;
        return wechat != null ? wechat.equals(a.wechat) : a.wechat == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (wechat != null ? wechat.hashCode() : 0);
        return result;
    }
}
