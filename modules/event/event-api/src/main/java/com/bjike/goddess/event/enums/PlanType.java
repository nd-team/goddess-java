package com.bjike.goddess.event.enums;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-12-27 15:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PlanType {

    /**
     * 月份
     */
    MONTH(1),
    /**
     * 周
     */
    WEEK(2),
    /**
     * 日
     */
    DAY(3),
    /**
     * 全部数据
     */
    ALL(4);

    private int value;

    public int getValue() {
        return value;
    }

    PlanType(int value) {
        this.value = value;
    }

}
