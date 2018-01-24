package com.bjike.goddess.reportmanagement.enums;

/**
 * 取数规则
 */
public enum AccessRules {

    /**
     * 余额
     */
    BALANCE(0),
    /**
     * 借方余额
     */
    DEBIT(1),
    /**
     * 贷方余额
     */
    CREDIT(2);
    private int code;

    AccessRules(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
