package com.bjike.goddess.staffentry.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 获取用户名和对应性别
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [获取用户名和对应性别 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserNameSexBO extends BaseBO{
    /**
     * 姓名
     */
    private String username;

    /**
     * 性别0男1女
     */
    private Integer gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
