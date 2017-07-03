package com.bjike.goddess.courier.type;

/**
 * @Author: [yewenbo]
 * @Date: [2017-04-28 10:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AttributeType {
    /**
     * 贵重件
     */
    PRECIOUS(0),
    /**
     * 紧急件
     */
    URGENCY(1),
    /**
     * 普通件
     */
    COMMON(2);

    private int code;

    AttributeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
