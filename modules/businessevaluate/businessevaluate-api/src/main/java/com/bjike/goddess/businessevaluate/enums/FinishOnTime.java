package com.bjike.goddess.businessevaluate.enums;

/**
 * 是否按时完成
 *
 * @Author: [Jason]
 * @Date: [17-3-29 下午2:38]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum FinishOnTime {

    /**
     * 是
     */
    YES(0),
    /**
     * 否
     */
    NOT(1);

    private int code;

    FinishOnTime(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
