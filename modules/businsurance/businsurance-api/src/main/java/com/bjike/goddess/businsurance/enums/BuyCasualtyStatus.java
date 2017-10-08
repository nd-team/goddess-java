package com.bjike.goddess.businsurance.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 购买意外险状态
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum BuyCasualtyStatus {
    /**
     * 待增员
     */
    @ExcelValue(name = "待增员")
    TOINCREASE(0),
    /**
     * 待减员
     */
    @ExcelValue(name="待减员")
    WORKERSTOSTAY(1),
    /**
     * 未购买
     */
    @ExcelValue(name="未购买")
    NOTTOBUY(2),
    /**
     * 已购买
     */
    @ExcelValue(name="已购买")
    HAVETOBUY(3),
    /**
     * 离职减员
     */
    @ExcelValue(name="离职减员")
    WORKERSLEAVE(4),
    /**
     * 离职未购买
     */
    @ExcelValue(name="离职未购买")
    UNPURCHASED(5);

    private int code;

    BuyCasualtyStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
