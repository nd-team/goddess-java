package com.bjike.goddess.message.enums;

/**
 * 消息发送范围
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-20 09:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RangeType {
    /**
     * 公共的消息，所有人
     */
    PUB(0),

    /**
     * 指定人员
     */
    SPECIFIED(1),
    /**
     * 组
     */
    GROUP(2);

    private int code;

    RangeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
