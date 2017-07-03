package com.bjike.goddess.dispatchcar.enums;

/**
 * 汇总类型
 *
 * @Author: [Jason]
 * @Date: [17-4-19 下午5:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CollectType {

    /**
     * 地区
     */
    AREA(0),

    /**
     * 司机
     */
    DRIVER(1);

    private int code;

    CollectType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
