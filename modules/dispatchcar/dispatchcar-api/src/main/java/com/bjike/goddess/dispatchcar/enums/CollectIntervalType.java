package com.bjike.goddess.dispatchcar.enums;

/**
 * 汇总条件
 *
 * @Author: [Jason]
 * @Date: [17-4-14 下午3:04]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CollectIntervalType {
    /**
     * 日
     */
    DAY(0),
    /**
     * 周
     */
    WEEK(1),
    /**
     * 月
     */
    MONTH(2),
    /**
     * 季度
     */
    QUARTER(3),
    /**
     * 年
     */
    YEAR(4);

    private int code;

    CollectIntervalType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
