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
public enum Kpi {
    /**
     * 不需要验收
     */
    NOACCEPTANCE(0),
    /**
     * 到货
     */
    ARRIVALGOODS(1),
    /**
     * 完成
     */
    COMPLETE(2),
    /**
     * 等待初验
     */
    WAITINGCHECK(3),
    /**
     * 等待终验
     */
    WAITINGFINALINSPECTION(4);

    private int value;

    Kpi(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
