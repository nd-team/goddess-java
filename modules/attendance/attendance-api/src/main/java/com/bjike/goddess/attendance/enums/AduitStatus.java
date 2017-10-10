package com.bjike.goddess.attendance.enums;

/**
 * 审核状态
 * @Author: [chenjunhao]
 * @Date: [2017-10-07 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AduitStatus {
    /**
     * 同意
     */
    AGREE(0),
    /**
     * 不同意
     */
    REJECT(1);
    private int code;

    AduitStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
