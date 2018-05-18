package com.bjike.goddess.taskallotment.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 审核类型
 * @Author: [chenjunhao]
 * @Date: [2017-09-20 15:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AduitType {
    /**
     * 通过
     */
    @ExcelValue(name = "通过")
    PASS(0),
    /**
     * 不通过
     */
    @ExcelValue(name = "不通过")
    NOTPASS(1);

    private int code;

    AduitType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
