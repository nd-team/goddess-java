package com.bjike.goddess.attendance.enums;

/**
 * 打卡类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-22 15:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PunchType {
    /**
     * 上班
     */
    GO(0),
    /**
     * 下班
     */
    AFTER(1);
    private int code;

    PunchType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
