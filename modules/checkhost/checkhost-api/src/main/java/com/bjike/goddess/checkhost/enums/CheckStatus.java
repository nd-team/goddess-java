package com.bjike.goddess.checkhost.enums;

/**
 * 审核状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-07-17 17:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CheckStatus {
    /**
     * 通过
     */
    PASS(0),
    /**
     * 不通过
     */
    NOTPASS(1);
    private int code;

    CheckStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
