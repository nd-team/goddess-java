package com.bjike.goddess.user.session.validfail;

import java.time.LocalDateTime;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 密码验证定时失效辅助工具实体]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ValidErr {
    private LocalDateTime createTime = LocalDateTime.now(); //创建时间
    private Integer count=0; // 错误次数
    private Integer invalidTime; //设置独立的失效时间(分钟)


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Integer invalidTime) {
        this.invalidTime = invalidTime;
    }


}
