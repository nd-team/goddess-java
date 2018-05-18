package com.bjike.goddess.moneyside.enums;

/**
 * 是否通过
 *
 * @Author: [xiazhili]
 * @Date: [2017-06-06 10:47]
 * @Description: [是否通过]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PassStatus {
    /**
     * 是
     */
    YES(0),
    /**
     * 否
     */
    NO(1),;

    private int code;

    PassStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
