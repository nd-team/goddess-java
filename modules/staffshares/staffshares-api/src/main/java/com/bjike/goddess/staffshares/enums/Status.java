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
public enum Status {
    /**
     * 已提交待审核
     */
    @ExcelValue(name = "已提交待审核")
    SUBMIT(0),
    /**
     * 审核通过待发行
     */
    @ExcelValue(name = "审核通过待发行")
    EXAMINE(1),
    /**
     * 删除
     */
    @ExcelValue(name = "删除")
    DELETE(2),
    /**
     * 审核不通过
     */
    @ExcelValue(name = "审核不通过")
    NOEXAMINE(3),
    /**
     * 已发行
     */
    @ExcelValue(name = "已发行")
    ISSUED(4)
    ;

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
