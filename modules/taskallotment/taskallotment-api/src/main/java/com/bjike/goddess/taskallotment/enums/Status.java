package com.bjike.goddess.taskallotment.enums;

/**
 * 状态
 * @Author: [chenjunhao]
 * @Date: [2017-09-14 13:46]
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
    END(1);

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
