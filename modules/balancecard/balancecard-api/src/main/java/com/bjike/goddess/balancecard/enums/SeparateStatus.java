package com.bjike.goddess.balancecard.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 被分解状态
 * @Author: [tanghaixiang]
 * @Date: [2017-03-15 16:47]
 * @Description: [被分解状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SeparateStatus {

    /**
     * 未分解
     */
    @ExcelValue(name = "未分解")
    NONE(0),
    /**
     * 已分解
     */
    @ExcelValue(name = "已分解")
    SEPERATE(1)
    ;

    private int code;

    SeparateStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
