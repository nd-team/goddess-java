package com.bjike.goddess.task.enums;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 16:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TimeType {
    /**
     * 秒
     */
    SECOND(0),
    /**
     * 分钟
     */
    MINUTE(1),
    /**
     * 小时
     */
    HOUR(2),
    /**
     * 每天
     */
    EVERYDAY(3),;

    int code;

    TimeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
