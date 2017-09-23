package com.bjike.goddess.salarymanage.enums;

import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-16 14:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum WorkAge {

    /**
     * 0-1年
     */
    @ExcelValue(name="0-1年")
    PRIMARY(0),

    /**
     * 1-3年
     */
    @ExcelValue(name="1-3年")
    INTERMEDIATE(1),

    /**
     * 3年
     */
    @ExcelValue(name="3年")
    ADVANCED(2);

    private int code;

    WorkAge(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
