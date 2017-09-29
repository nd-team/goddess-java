package com.bjike.goddess.user.to;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-09-27 17:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SmsCodeTO implements Serializable{

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phoneNumber;

    /**
     * 验证码
     */
    @NotBlank(message = "手机号不能为空")
    private String code;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
