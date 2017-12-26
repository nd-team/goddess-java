package com.bjike.goddess.fixedassets.enums;

/**
 * 状态
 * @Author: [lijuntao]
 * @Date: [2017-08-09 18:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Status {
    /**
     * 使用中
     */
    INUSE(0),
    /**
     * 未使用
     */
    NOUSE(1),
    /**
     * 不需用
     */
    DONTNEED(2),
    /**
     * 其他
     */
    OTHER(3);

    private int value;

    public int getValue() {
        return value;
    }

    Status(int value) {
        this.value = value;
    }
}
