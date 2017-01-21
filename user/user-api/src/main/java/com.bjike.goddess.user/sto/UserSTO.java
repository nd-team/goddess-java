package com.bjike.goddess.user.sto;

import com.bjike.goddess.common.api.sto.BaseSTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-18 15:25]
 * @Description: [用户业务传送对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserSTO extends BaseSTO {
    private String username;
    private String phone;//登录手机(注册验证手机)
    private String email;//登录邮箱
    private String headSculpture;//头像
    private String nickname; //昵称

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
