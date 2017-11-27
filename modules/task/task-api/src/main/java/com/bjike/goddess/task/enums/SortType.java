package com.bjike.goddess.task.enums;

/**
 * 排序类型
 * @Author: [chenjunhao]
 * @Date: [2017-11-18 16:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SortType {
    /**
     * 默认排序
     */
    ACQUIESCENCE(1),
    /**
     * 自定义排序
     */
    CUSTOM(2);

    SortType(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
