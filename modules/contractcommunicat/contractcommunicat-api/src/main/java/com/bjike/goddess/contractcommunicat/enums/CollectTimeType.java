package com.bjike.goddess.contractcommunicat.enums;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-02 16:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CollectTimeType {

    /**
     * 日汇总
     */
    DAY(1),

    /**
     * 周汇总
     */
    WEEK(2),

    /**
     * 月汇总
     */
    MONTH(3),

    /**
     * 季度汇总
     */
    QUART(4),

    /**
     * 年汇总
     */
    YEAR(5),

    /**
     * 累计汇总
     */
    TOTAL(6);

    private int code;


    CollectTimeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
