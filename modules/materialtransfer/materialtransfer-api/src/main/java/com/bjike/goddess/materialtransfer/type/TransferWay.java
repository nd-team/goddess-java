package com.bjike.goddess.materialtransfer.type;

/**
 * @Author: [sunfengtao]
 * @Date: [2017-04-28 11:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TransferWay {

    /**
     * 快递
     */
    EXPRESS(0),
    /**
     * 损坏
     */
    MANPOWER(1);

    private int value;

    TransferWay(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
