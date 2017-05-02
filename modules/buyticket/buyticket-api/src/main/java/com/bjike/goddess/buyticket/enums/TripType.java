package com.bjike.goddess.buyticket.enums;

/**
 * 行程类型枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-25]
 * @Description: [行程类型枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum TripType {
    /**
     * 往
     */
    TO(0),
    /**
     * 返
     */
    RETURN(1),
    ;

    private int code;

    TripType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
