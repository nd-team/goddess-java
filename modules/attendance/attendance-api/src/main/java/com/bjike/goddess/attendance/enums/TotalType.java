package com.bjike.goddess.attendance.enums;

/**
 * 统计类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-11-06 10:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TotalType {
    /**
     * 人数
     */
    NUMBER(0),
    /**
     * 明细
     */
    DETAIL(1);
    private int code;

    TotalType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
