package com.bjike.goddess.businessevaluate.enums;

/**
 * 错误类型
 *
 * @Author: [Jason]
 * @Date: [17-3-29 下午2:48]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ErrorType {

    /**
     * 一般
     */
    GENERAL(0),
    /**
     * 严重
     */
    SEVERITY(1);

    private int code;

    ErrorType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
