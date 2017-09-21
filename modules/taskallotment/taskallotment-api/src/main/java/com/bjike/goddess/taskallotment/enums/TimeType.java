package com.bjike.goddess.taskallotment.enums;

/**
 * 时间类型
 * @Author: [chenjunhao]
 * @Date: [2017-09-15 08:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TimeType {
    /**
     * 分钟
     */
    MINUTE(0),
    /**
     * 小时
     */
    HOUR(1),
    /**
     * 天
     */
    DAY(2);

    private int code;

    TimeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
