package com.bjike.goddess.reportmanagement.enums;

/**
 * 公式方向
 */
public enum Form {

    /**
     * 借方
     */
    DEBIT(0),
    /**
     * 贷方
     */
    CREDIT(1);
    private int code;

    Form(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
