package com.bjike.goddess.contractware.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-01 15:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum InvoiceType {
    /**
     * 增值税专用发票
     */
    APPRECIATIONDEDICATED(0),

    /**
     * 增值税普通发票
     */
    APPRECIATIONCONMON(1),

    /**
     * 增值税电子发票
     */
    APPRECIATIONELECTRONIC(2),

    /**
     * 普通发票
     */
    COMMON(3);

    private int code;

    InvoiceType(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
