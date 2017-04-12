package com.bjike.goddess.lendreimbursement.enums;

/**
 * 凭记字
 * @Author: [tanghaixiang]
 * @Date: [2017-04-07 20:20]
 * @Description: [凭记字]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Words {
    /**
     * 付
     */
    PAY(0),
    /**
     * 转
     */
    TURN(1),
    /**
     * 让
     */
    GIVEWAY(2),
    /**
     * 收
     */
    COLLECT(3)
    ;

    private int code;

    Words(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
