package com.bjike.goddess.projectmeasure.type;

/**
 * 发票形式
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-23 11:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum InvoiceForm {

    /**
     * 增值税专用发票
     */
    VALUE_ADDED_TAX(0),
    /**
     * 普通发票
     */
    NORMAL(1);

    private int value;

    InvoiceForm(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
