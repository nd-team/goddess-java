package com.bjike.goddess.materialtransfer.type;

/**
 * 物资来源
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-28 11:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum MaterialSource {

    /**
     * 采购
     */
    BUY(0),
    /**
     * 外借
     */
    ALLOWED(1);

    private int value;

    MaterialSource(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
