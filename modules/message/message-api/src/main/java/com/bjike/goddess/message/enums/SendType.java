package com.bjike.goddess.message.enums;

/**
 * 消息类型
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-14 10:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SendType {
    /**
     * 消息
     */
    MSG(0),
    /**
     * 邮件
     */
    EMAIL(1),

    /**
     * 邮件及消息
     */
    ALL(2);

    private int code;

    SendType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
