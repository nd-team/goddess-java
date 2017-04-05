package com.bjike.goddess.businessevaluate.enums;

/**
 * 影响部门数
 *
 * @Author: [Jason]
 * @Date: [17-3-29 下午2:32]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AffectDeptAmount {
    /**
     * 一个部门
     */
    ONE(0),
    /**
     * 二至三个部门
     */
    TWOORTHREE(1),
    /**
     * 三个部门以上
     */
    MORETHREE(2);


    private int code;

    AffectDeptAmount(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
