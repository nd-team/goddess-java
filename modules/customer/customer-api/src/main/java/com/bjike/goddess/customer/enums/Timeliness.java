package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 时效性枚举
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [时效性枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Timeliness {

    /**
     * 十分紧急
     */
    @ExcelValue(name = "十分紧急")
    URGENT(0),
    /**
     * 紧急
     */
    @ExcelValue(name = "紧急")
    URGENCY(1),
    /**
     * 一般
     */
    @ExcelValue(name = "一般")
    GENERAL(2),
    /**
     * 不紧急
     */
    @ExcelValue(name = "不紧急")
    NOTURGENCY(3);

    private int code;

    Timeliness(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
