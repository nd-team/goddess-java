package com.bjike.goddess.financeinit.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 16:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ScaleShape {

    /**
     * 小规模企业
     */
    @ExcelValue(name="小规模企业")
    SMALLBUSS(0),
    /**
     * 一般纳税人企业
     */
    @ExcelValue(name="一般纳税人企业")
    GENERALTAXENTER(1);


    private int code;

    ScaleShape(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
