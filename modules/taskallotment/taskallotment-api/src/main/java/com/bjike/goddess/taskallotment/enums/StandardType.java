package com.bjike.goddess.taskallotment.enums;

/**
 * 标准类型
 * @Author: [chenjunhao]
 * @Date: [2017-09-15 11:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum StandardType {
    /**
     * 日工时标准
     */
    DAYSTANDARD(0),
    /**
     * 周工时标准
     */
    WEEKSTANDARD(1),
    /**
     * 月工时标准
     */
    PERSONSTANDARD(2);

    private int code;

    StandardType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
