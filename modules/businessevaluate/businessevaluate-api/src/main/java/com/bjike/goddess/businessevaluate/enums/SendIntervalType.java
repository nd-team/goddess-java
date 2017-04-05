package com.bjike.goddess.businessevaluate.enums;

/**
 * 发送间隔
 *
 * @Author: [Jason]
 * @Date: [17-3-23 上午11:13]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SendIntervalType {

    /**
     * 分
     */
    MINUTE(0),
    /**
     * 天
     */
    HOUR(1),
    /**
     * 天
     */
    DAY(2),
    /**
     * 周
     */
    WEEK(3),
    /**
     * 月
     */
    MONTH(4),
    /**
     * 季度
     */
    QUARTER(5),
    /**
     * 年
     */
    YEAR(6);

    private int code;

    SendIntervalType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}