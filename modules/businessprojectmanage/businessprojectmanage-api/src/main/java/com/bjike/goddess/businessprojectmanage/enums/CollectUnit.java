package com.bjike.goddess.businessprojectmanage.enums;

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

    CollectUnit(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}


