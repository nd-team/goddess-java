package com.bjike.goddess.business.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 状态
 */
public enum Status {

    /**
     * 开业
     */
    @ExcelValue(name = "开业")
    OPING(0),
    /**
     * 歇业
     */
    @ExcelValue(name = "歇业")
    BUSSGOOUT(1),
    /**
     * 结业
     */
    @ExcelValue(name = "结业")
    GRADUATION(2),
    ;
    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
