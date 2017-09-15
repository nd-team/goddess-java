package com.bjike.goddess.regularization.type;

/**
 * 员工状态
 *
 * @Author: [lijuntao]
 * @Date: [2017-06-20 12:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum StaffStatus {
    /**
     * 试用期
     */
    PROBATION(0),
    /**
     * 转正中
     */
    POSITIVE(1),
    /**
     * 转正不通过
     */
    NOPASS(2),
    /**
     * 已转正
     */
    BECOMEMEM(3),
    /**
     * 待转正
     */
    STAYPOSITIVE(4);



    private int code;

    StaffStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
