package com.bjike.goddess.buyticket.enums;

/**
 * 汇总周期枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-27]
 * @Description: [汇总周期枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum CollectCycle {
    /**
     * 每周
     */
    WEEK(0),
    /**
     * 每月
     */
    MONTH(1),
    /**
     * 每年
     */
    YEAR(2),
    ;

    private int code;

    CollectCycle(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
