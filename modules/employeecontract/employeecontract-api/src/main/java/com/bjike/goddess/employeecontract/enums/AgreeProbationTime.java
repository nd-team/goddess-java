package com.bjike.goddess.employeecontract.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-10 16:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AgreeProbationTime {
    /**
     * 1个月
     */
    ONEMONTH(0),

    /**
     * 3个月
     */
    THREEMONTH(1),

    /**
     * 1-3个月
     */
    ONETOTHREEMONTH(2),

    /**
     * 外聘，自由人无
     */
    EXTERNAL(3),

    /**
     * 其他时间
     */
    OTHERTIME(4);

    private int code;

    AgreeProbationTime(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
