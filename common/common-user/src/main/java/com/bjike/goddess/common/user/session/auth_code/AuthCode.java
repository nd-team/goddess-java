package com.bjike.goddess.common.user.session.auth_code;

import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-12 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AuthCode {

    /**
     * 验证码
     */
    private String code;

    /**
     * 上次连接时间
     */
    private LocalDateTime accessTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }
}
