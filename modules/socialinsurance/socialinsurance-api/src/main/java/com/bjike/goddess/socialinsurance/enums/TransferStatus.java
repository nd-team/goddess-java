package com.bjike.goddess.socialinsurance.enums;

/**
 * 过帐状态
 * @Author: [tanghaixiang]
 * @Date: [2017-04-17 18:02]
 * @Description: [过帐状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TransferStatus {
    /**
     * 未过帐
     */
    NONE(0),
    /**
     * 已过帐
     */
    CHECK(1);

    private int code;

    TransferStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
