package com.bjike.goddess.devicerepair.type;

/**
 * 物资状态
 *
 * @Author: [sunfengtao]
 * @Date: [2017-05-03 16:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum MaterialState {

    /**
     * 待维修
     */
    WAITING_REPAIR(0),
    /**
     * 正常
     */
    NORMALITY(1),
    /**
     * 已报废
     */
    SCRAPED(2);

    private int value;

    MaterialState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
