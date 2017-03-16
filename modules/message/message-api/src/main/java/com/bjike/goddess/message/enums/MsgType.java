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
public enum MsgType {
    SYS(0), //系统
    PERSONAL(1),//个人
    MULTI(2); //多人

    private int code;

    MsgType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
