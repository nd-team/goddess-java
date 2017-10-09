package com.bjike.goddess.dispatchcar.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-07 16:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CollectDateType {
    /**
     * 日
     */
    DAY(0),

    /**
     * 月
     */
    MONTH(1),

    /**
     * 年
     */
    YEAR(2);

    private int code;

    CollectDateType(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
