package com.bjike.goddess.supplier.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [lijuntao]
 * @Date: [2017-06-14 11:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Status {
    /**
     * 已合作
     */
    @ExcelValue(name="已合作")
    HAVECOOPERATION(0),
    /**
     * 未合作
     */
    @ExcelValue(name="未合作")
    NOCOOPERATION(1),;


    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
