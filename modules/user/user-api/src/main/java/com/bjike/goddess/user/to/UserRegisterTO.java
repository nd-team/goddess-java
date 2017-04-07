package com.bjike.goddess.user.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 16:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserRegisterTO extends BaseTO {

    /**
     * 注册用名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 注册密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 重复密码
     */
    @NotBlank(message = "重复密码不能为空")
    private String rePassword;

    /**
     * 验证码
     */
    private String authCode;

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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
