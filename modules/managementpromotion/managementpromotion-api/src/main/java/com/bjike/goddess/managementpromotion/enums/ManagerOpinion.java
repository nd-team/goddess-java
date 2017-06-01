package com.bjike.goddess.managementpromotion.enums;

/**
 * @Author: [yewenbo]
 * @Date: [2017-05-23 11:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ManagerOpinion {
    /**
     * 通过
     */
    PASS(0),
    /**
     * 不通过
     */
    NOTPASS(1);

    private int code;

    ManagerOpinion(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
