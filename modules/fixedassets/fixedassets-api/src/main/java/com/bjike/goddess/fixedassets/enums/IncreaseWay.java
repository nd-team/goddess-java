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
public enum IncreaseWay {
    /**
     * 购置
     */
    @ExcelValue(name = "购置")
    PURCHASE(0),
    /**
     * 改造
     */
    @ExcelValue(name = "改造")
    REMOLDING(1),
    /**
     * 改良
     */
    @ExcelValue(name = "改良")
    AMELIORATION(2),
    /**
     * 受赠
     */
    @ExcelValue(name = "受赠")
    RECIPIENT(3),
    /**
     * 调拨
     */
    @ExcelValue(name = "调拨")
    TRANSFERS(4),
    /**
     * 划转
     */
    @ExcelValue(name = "划转")
    TRAN(5),
    /**
     * 其他
     */
    @ExcelValue(name = "其他")
    OTHER(6);

    private int value;

    public int getValue() {
        return value;
    }

    IncreaseWay(int value) {
        this.value = value;
    }
}
