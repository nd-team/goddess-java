package com.bjike.goddess.reportmanagement.enums;

/**
 * 运算类型
 * @Author: [chenjunhao]
 * @Date: [2017-06-29 16:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Type {
    /**
     * 加
     */
    ADD(0),
    /**
     * 减
     */
    REMOVE(1);
    private int code;

    Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
