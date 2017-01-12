package com.bjike.goddess.user.session.phonecode;

import java.time.LocalDateTime;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 手机验证码定时失效辅助工具实体]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PhoneCode {
    private LocalDateTime createTime = LocalDateTime.now(); //创建时间
    private Integer invalidTime; //设置独立的失效时间(分钟)
    private String code; //验证码

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Integer invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
