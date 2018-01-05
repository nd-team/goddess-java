package com.bjike.goddess.customer.enums;

/**
 * 推荐信息更新频率
 * @Author: [lijuntao]
 * @Date: [2017-03-16 19:16]
 * @Description: [推荐信息更新频率]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RecommInfoUpdateFreq {

    /**
     * 每小时
     */
    EVERYHOUR(0),
    /**
     * 每天
     */
    EVERYDAY(1),
    /**
     * 每周
     */
    EVERYWEEK(2),
    /**
     * 每月
     */
    EVERYMONTH(3),;

    private int code;

    RecommInfoUpdateFreq(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}


