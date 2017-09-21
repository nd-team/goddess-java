package com.bjike.goddess.taskallotment.enums;

/**
 * 字段类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-14 16:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TitleType {
    /**
     * 文本
     */
    TEXT(0),
    /**
     * 整数
     */
    INT(1),
    /**
     * 非整数
     */
    DOUBLE(2),
    /**
     * 日期
     */
    DATE(3),
    /**
     * 时间日期
     */
    DATETIME(4),
    /**
     * 逻辑类型
     */
    BOOLEAN(5);

    private int code;

    TitleType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
