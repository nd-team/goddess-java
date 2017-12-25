package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 满意度枚举
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [满意度枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Satisfation {

    /**
     * 非常满意
     */
    @ExcelValue(name = "非常满意")
    VERYSATISFATION(0),
    /**
     * 一般
     */
    @ExcelValue(name = "一般")
    GENERAL(1),
    /**
     * 不满意
     */
    @ExcelValue(name = "不满意")
    NOTSATISFATION(2)
    ;

    private int code;

    Satisfation(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
