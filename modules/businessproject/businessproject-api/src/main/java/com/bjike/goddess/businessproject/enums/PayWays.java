package com.bjike.goddess.businessproject.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 支付方式
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 20:35]
 * @Description: [支付方式]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PayWays {
    /**
     * 现金
     */
    @ExcelValue(name = "现金")
    CASH(0),
    /**
     * 银行汇兑
     */
    @ExcelValue(name = "银行汇兑")
    BANK(1),
    /**
     * 电汇
     */
    @ExcelValue(name = "电汇")
    ELECTRIC(2),
    /**
     * 转账
     */
    @ExcelValue(name = "转账")
    TRANSFERACCOUNTS(3);

    private int code;

    PayWays(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static PayWays getEnumConvert(int code) {
        PayWays payWays = PayWays.CASH;
        if (code == PayWays.CASH.getCode()) {
            payWays = PayWays.CASH;
        }
        if (code == PayWays.BANK.getCode()) {
            payWays = PayWays.BANK;
        }
        if (code == PayWays.ELECTRIC.getCode()) {
            payWays = PayWays.ELECTRIC;
        }
        if (code == PayWays.TRANSFERACCOUNTS.getCode()) {
            payWays = PayWays.TRANSFERACCOUNTS;
        }
        return payWays;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == PayWays.CASH.getCode()) {
            name = "现金";
        }
        if (code == PayWays.BANK.getCode()) {
            name = "银行汇兑";
        }
        if (code == PayWays.ELECTRIC.getCode()) {
            name = "电汇";
        }
        if (code == PayWays.TRANSFERACCOUNTS.getCode()) {
            name = "转账";
        }
        return name;
    }
}
