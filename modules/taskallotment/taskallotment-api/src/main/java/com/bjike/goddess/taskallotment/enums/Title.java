package com.bjike.goddess.taskallotment.enums;

/**
 * 字段名
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-14 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Title {
    /**
     * 执行人
     */
    EXECUTE(0),
    /**
     * 负责人
     */
    CHARGE(1);

    private int code;

    Title(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
