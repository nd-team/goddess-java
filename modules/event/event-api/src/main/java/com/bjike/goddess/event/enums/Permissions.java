package com.bjike.goddess.event.enums;

/**
 * 权限枚举
 * @Author: [chenjunhao]
 * @Date: [2017-08-09 16:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Permissions {
    /**
     * 查看
     */
    SEE(0),
    /**
     * 制作
     */
    MAKE(1),
    /**
     * 审核
     */
    ADUIT(2),
    /**
     * 确认
     */
    CONFIRM(3),
    /**
     * 核对
     */
    CHECK(4),
    /**
     * 分析
     */
    ANALYZE(5)
    ;

    private int value;

    public int getValue() {
        return value;
    }

    Permissions(int value) {
        this.value = value;
    }
}
