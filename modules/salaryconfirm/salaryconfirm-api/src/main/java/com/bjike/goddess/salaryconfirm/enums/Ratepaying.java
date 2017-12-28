package com.bjike.goddess.salaryconfirm.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 是否缴纳个人所得税
 *
 * Created by ike on 17-5-25.
 */
public enum Ratepaying {

    /**
     * 否
     */
    @ExcelValue(name = "否")
    NOT(0),
    /**
     * 是
     */
    @ExcelValue(name = "是")
    YES(1);

    private int code;

    Ratepaying(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
