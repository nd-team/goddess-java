package com.bjike.goddess.dispatchcar.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-07 16:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CollectDispatchcarType {
    /**
     * 司机
     */
    DRIVER(0),

    /**
     * 地区
     */
    AREA(1),

    /**
     * 项目
     */
    PROJECT(2);

    private int code;

    CollectDispatchcarType(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
