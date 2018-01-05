package com.bjike.goddess.projectprocing.enums;

/**
 * 类型
 *
 * @Author: [lijuntao]
 * @Date: [17-3-9 下午4:10]
 * @Package:[ com.bjike.goddess.projectprocing.enums ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Types {
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

    private int value;

    Types(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
