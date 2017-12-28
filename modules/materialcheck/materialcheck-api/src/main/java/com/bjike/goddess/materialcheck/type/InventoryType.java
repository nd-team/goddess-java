package com.bjike.goddess.materialcheck.type;

/**
 * 盘点类型
 *
 * @Author: [sunfengtao]
 * @Date: [2017-05-08 17:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum InventoryType {

    /**
     * 日盘
     */
    DAILY_INVENTORY(0),
    /**
     * 周盘
     */
    WEEKLY_INVENTORY(1),
    /**
     * 年盘
     */
    ANNUAL_INVENTORY(2);

    private int value;

    InventoryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
