package com.bjike.goddess.financeinit.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 16:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AccountingPeriod {

    /**
     * 会计期间界定方式
     */
    DTWIAP(0),
    /**
     * 会计年度启用日期
     */
    TSDOTAY(1),
    /**
     * 账套会计期间启用日期
     */
    DOUDAP(2),
    /**
     * 账套会计启用日期
     */
    DOAO(3);


    private int code;

    AccountingPeriod(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
