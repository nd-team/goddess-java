package com.bjike.goddess.market.enums;

/**
 * 汇总间隔
 * @Author: [xiazhili]
 * @Date: [2017-03-22 19:16]
 * @Description: [汇总间隔]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum MarketCollectUnit {

    /**
     * 每天
     */
    EVERYDAY(0),
    /**
     * 每周
     */
    EVERYWEEK(1),
    /**
     * 每月
     */
    EVERYMONTH(2),;

    private int code;

    MarketCollectUnit(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}


