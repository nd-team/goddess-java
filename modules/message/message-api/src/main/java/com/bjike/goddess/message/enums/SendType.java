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
     * 邮件
     */
    EMAIL(0),
    /**
     * 消息
     */
    MSG(1),
    /**
     * 邮件,消息
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
