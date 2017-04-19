package com.bjike.goddess.voucher.enums;

/**
 * 结帐状态
 * @Author: [tanghaixiang]
 * @Date: [2017-04-17 18:04]
 * @Description: [结帐状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CheckStatus {

    /**
     * 未结帐
     */
    NONE(0),
    /**
     * 已结帐
     */
    CHECK(1);

    private int code;

    CheckStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
