package com.bjike.goddess.materialsummary.type;

/**
 * 汇总间隔
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-16 19:16]
 * @Description: [汇总间隔]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CollectUnit {

    /**
     * 本天
     */
    EVERYDAY(0),
    /**
     * 本周
     */
    EVERYWEEK(1),
    /**
     * 本月
     */
    EVERYMONTH(2),
    /**
     * 本年
     */
    EVERYYEAR(3),;

    private int code;

    CollectUnit(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}


