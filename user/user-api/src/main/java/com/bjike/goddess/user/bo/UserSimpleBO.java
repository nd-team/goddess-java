package com.bjike.goddess.user.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 简单的用户业务传送
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-18 15:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserSimpleBO extends BaseBO {
    /**
     * 登录手机(注册验证手机)
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickname;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
