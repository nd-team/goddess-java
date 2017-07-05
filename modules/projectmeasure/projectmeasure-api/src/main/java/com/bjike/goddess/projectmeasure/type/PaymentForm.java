package com.bjike.goddess.projectmeasure.type;

/**
 * 付款形式
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-23 11:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PaymentForm {

    /**
     * 一次性付清
     */
    ONE_PAY(0),
    /**
     * 分段付清
     */
    FRAGMENT_PAY(1);

    private int value;

    PaymentForm(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
