package com.bjike.goddess.user.vo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 发送完短信后的回执数据
 * @Author: [tanghaixiang]
 * @Date: [2017-09-27 17:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SmsReceiveCodeVO implements Serializable{

    /**
     * 短信内容
     */
    @NotBlank(message = "手机号不能为空")
    private String content;

    /**
     * 状态码-返回OK代表请求成功,其他错误码详见错误码列表
     */
    @NotBlank(message = "手机号不能为空")
    private String code;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
