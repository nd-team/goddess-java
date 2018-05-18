package com.bjike.goddess.financeinit.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 16:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum BalanceDirection {

    /**
     * 借
     */
    @ExcelValue(name = "借")
    BORROW(0),
    /**
     * 贷
     */
    @ExcelValue(name = "贷")
    CREDIT(1);

    private int code;

    BalanceDirection(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String enumToString(BalanceDirection balanceDirection) {
        String name = "";
        switch (balanceDirection) {
            case BORROW:
                name = "借";
                break;
            case CREDIT:
                name = "贷";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }

}
