package com.bjike.goddess.attendance.enums;

/**
 * 打卡来源
 * @Author: [chenjunhao]
 * @Date: [2017-09-22 15:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PunchSource {
    /**
     * PC端
     */
    PC(0),
    /**
     * 移动端
     */
    MOBILE(1);
    private int code;

    PunchSource(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
