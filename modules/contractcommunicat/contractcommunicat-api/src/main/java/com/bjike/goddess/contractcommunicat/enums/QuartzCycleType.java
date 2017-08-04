package com.bjike.goddess.contractcommunicat.enums;

/**
 * 汇总间隔
 *
 * @Author: [Jason]
 * @Date: [17-3-20 上午10:01]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum QuartzCycleType {

    /**
     * 本年
     */
    YEAR(0),

    /**
     * 本季度
     */
    QUARTER(1),

    /**
     * 本月
     */
    MONTH(2),
    /**
     * 本周
     */
    WEEK(3),
    /**
     * 本日
     */
    DAY(4);



    private int code;

    QuartzCycleType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
