package com.bjike.goddess.projectprocing.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 汇总模板
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SummTableName {
    /**
     * 结算进度管理汇总
     */
    BALANCE(0),
    /**
     * 结算工作进度汇总
     */
    SCHEDULE(1),
    /**
     * 结算进度汇总模板
     */
    COLLECT(2);

    private int code;

    SummTableName(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
