package com.bjike.goddess.financeinit.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 16:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AccountingSystem {

    /**
     * 小企业会计制度
     */
    SMALLBUSSACCOUNTSYS(0),
    /**
     * 企业会计制度
     */
    BUSSACCOUNTSYS(1),
    /**
     * 小企业会计准则
     */
    ACCOUNSTANDARDS(2);

    private int code;

    AccountingSystem(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
