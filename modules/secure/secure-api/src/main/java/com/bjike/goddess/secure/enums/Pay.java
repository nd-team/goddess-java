package com.bjike.goddess.secure.enums;

/**
 * 挂靠金额支付方式
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-05 17:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Pay {
    /**
     * 全额自付
     */
    SELF(0),
    /**
     * 公司支付
     */
    COMPANY(1);

    private int value;

    public int getValue() {
        return value;
    }

    Pay(int value) {
        this.value = value;
    }
}
