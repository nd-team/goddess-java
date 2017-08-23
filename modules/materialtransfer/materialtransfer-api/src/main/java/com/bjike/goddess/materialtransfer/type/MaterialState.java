package com.bjike.goddess.materialtransfer.type;

/**
 * 物资物资状态
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-28 11:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum MaterialState {

    /**
     * 完好
     */
    INTACT(0),
    /**
     * 损坏
     */
    DAMAGED(1),

    /**
     * 维修中
     */
    MAINTENANCE(2),

    /**
     * 已报废
     */
    BAD(3);

    private int value;

    MaterialState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
