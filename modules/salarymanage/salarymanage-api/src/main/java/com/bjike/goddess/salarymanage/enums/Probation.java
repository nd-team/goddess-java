package com.bjike.goddess.salarymanage.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-15 14:06]
 * @Description: [试用期时长]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Probation {
    /**
     * 一个月
     */
    ONEMONTH(0),

    /**
     * 1-3个月
     */
    ONETWOMONTH(1),

    /**
     * 3个月
     */
    THREEMONTH(2);

    private int code;

    Probation(int code){this.code = code;}
    public int getCode() {
        return code;
    }

}
