package com.bjike.goddess.projectmeasure.type;

/**
 * 汇款内容
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-23 11:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RemitContent {

    /**
     * 现金
     */
    CASH(0),
    /**
     * 银行汇兑
     */
    BANK_TRANSFER(1);

    private int value;

    RemitContent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
