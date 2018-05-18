package com.bjike.goddess.fixedassets.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 固定资产类型
 *
 * @Author: [lijuntao]
 * @Date: [2017-08-09 16:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DepreciationWay {
    /**
     * 不计提折旧
     */
    @ExcelValue(name = "不计提折旧")
    NONDEPRECIATION(0),
    /**
     * 平均年限法
     */
    @ExcelValue(name = "平均年限法")
    AVERAGELIFE(1);

    private int value;

    public int getValue() {
        return value;
    }

    DepreciationWay(int value) {
        this.value = value;
    }
}
