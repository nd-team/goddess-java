package com.bjike.goddess.common.user.session.valid_err;

import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-12 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PwdErr {

    /**
     * 密码错误次数
     */
    private Integer count;

    /**
     * 上次连接时间
     */
    private LocalDateTime accessTime;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }
}
