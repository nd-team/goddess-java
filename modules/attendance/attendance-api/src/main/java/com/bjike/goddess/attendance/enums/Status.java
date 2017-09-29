package com.bjike.goddess.attendance.enums;

/**
 * 状态
 * @Author: [chenjunhao]
 * @Date: [2017-09-22 15:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Status {
    /**
     * 启用
     */
    START(0),
    /**
     * 禁用
     */
    STOP(1);
    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
