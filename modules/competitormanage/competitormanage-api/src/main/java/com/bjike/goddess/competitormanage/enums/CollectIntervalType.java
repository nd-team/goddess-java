package com.bjike.goddess.competitormanage.enums;

/**
 * 汇总间隔
 *
 * @Author: [Jason]
 * @Date: [17-3-23 上午11:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CollectIntervalType {

    /**
     * 天
     */
    ONEDAY(0),
    /**
     * 周
     */
    ONEWEEK(1),
    /**
     * 月
     */
    ONEMONTH(2);

    private int code;

    CollectIntervalType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
