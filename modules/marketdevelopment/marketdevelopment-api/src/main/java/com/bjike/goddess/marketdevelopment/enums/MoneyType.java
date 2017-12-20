package com.bjike.goddess.marketdevelopment.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 月份类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 05:57 ]
 * @Description: [ 月份类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public enum MoneyType {
    /**
     * 目标金额(万元)
     */
    @ExcelValue(name = "目标金额(万元)")
    AIM(0),
    /**
     * 计划金额(万元)
     */
    @ExcelValue(name = "计划金额(万元)")
    PLAN(1),
    /**
     * 实际金额(万元)
     */
    @ExcelValue(name = "实际金额(万元)")
    ATCUAL(2),
    /**
     * 差异金额(万元)
     */
    @ExcelValue(name = "差异金额(万元)")
    DIFFERENCE(3);

    private int code;

    private MoneyType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
