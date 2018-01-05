package com.bjike.goddess.fixedassets.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 固定资产类型
 * @Author: [lijuntao]
 * @Date: [2017-08-09 16:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Unit {
    /**
     * 间
     */
    @ExcelValue(name = "间")
    BETWEEN(0),
    /**
     * 台
     */
    @ExcelValue(name = "台")
    TABLE(1),
    /**
     * 辆
     */
    @ExcelValue(name = "辆")
    CAR(2),

    ;

    private int value;

    public int getValue() {
        return value;
    }

    Unit(int value) {
        this.value = value;
    }
}
