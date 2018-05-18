package com.bjike.goddess.attendance.enums;

/**
 * 汇总类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-12 17:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CountType {
    /**
     * 整体汇总
     */
    WHOLE(0),
    /**
     * 个人汇总
     */
    PERSONAL(1),
    /**
     * 部门汇总
     */
    DEPART(2);
    private int code;

    CountType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
