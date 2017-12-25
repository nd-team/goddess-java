package com.bjike.goddess.staffshares.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 简单的数据状态
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Type {
    /**
     * 原始股
     */
    @ExcelValue(name = "原始股")
    PRIMITIVE(0),
    /**
     * 绩效股
     */
    @ExcelValue(name = "绩效股")
    PERFORMANCE(1),
    /**
     * 流通股
     */
    @ExcelValue(name = "流通股")
    TRADABLE(2),
    ;

    private int code;

    Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
