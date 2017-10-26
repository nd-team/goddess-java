package com.bjike.goddess.oilcardmanage.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 油卡状态
 */
public enum OilCardStatus {

    /**
     * 闲置
     */
    @ExcelValue(name = "闲置")
    IDLE(0),
    /**
     * 已领用
     */
    @ExcelValue(name = "已领用")
    USE(1),
    /**
     * 冻结使用
     */
    @ExcelValue(name = "冻结使用")
    FREEZE(2);

    private int code;

    OilCardStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
