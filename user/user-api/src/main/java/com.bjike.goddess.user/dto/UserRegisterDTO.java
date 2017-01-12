package com.bjike.goddess.user.dto;


import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 用户注册数据传输]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserRegisterDTO extends BaseDTO {

    private String username; //注册用名
    private String password;//注册密码
    private String rePassword;//重复密码
    private String phone; //注册手机
    private String phoneCode;//手机验证码
    private String authCode;//验证码
    private String sid;//唯一标识

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
