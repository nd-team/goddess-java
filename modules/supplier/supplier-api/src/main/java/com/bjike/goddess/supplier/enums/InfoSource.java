package com.bjike.goddess.supplier.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [lijuntao]
 * @Date: [2017-06-14 11:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum InfoSource {
    /**
     * 市场招待
     */
    @ExcelValue(name = "市场招待")
    MARKET(0),
    /**
     * 商务洽谈
     */
    @ExcelValue(name = "商务洽谈")
    BUSSNEGOTIATION(1),
    /**
     * 员工介绍
     */
    @ExcelValue(name = "员工介绍")
    STAFFINTR(2),
    /**
     * 其他
     */
    @ExcelValue(name = "其他")
    OTHERS(3);


    private int value;

    InfoSource(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
