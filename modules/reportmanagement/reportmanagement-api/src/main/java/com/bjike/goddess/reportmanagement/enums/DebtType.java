package com.bjike.goddess.reportmanagement.enums;

/**
 * 负债类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-22 16:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DebtType {
    /**
     * 流动负债
     */
    AFLOW(0),
    /**
     * 长期负债
     */
    BLONG(1),
    /**
     * 递延税项
     */
    CTAX(2),
    /**
     * 所有者权益(或股东权益)
     */
    DALL(3);
    private int code;

    DebtType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
