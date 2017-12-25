package com.bjike.goddess.stockholdermeeting.enums;

/**
 * @Author: [yewenbo]
 * @Date: [2017-06-07 09:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SummaryStatus {
    /**
     * 冻结
     */
    FREEZE(0),
    /**
     * 正常
     */
    NORMAL(1);

    private int code;

    SummaryStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
