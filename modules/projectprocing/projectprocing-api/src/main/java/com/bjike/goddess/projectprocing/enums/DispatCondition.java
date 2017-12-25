package com.bjike.goddess.projectprocing.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 类型
 *
 * @Author: [lijuntao]
 * @Date: [17-3-9 下午4:10]
 * @Package:[ com.bjike.goddess.projectprocing.enums ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DispatCondition {
    /**
     * 已派工
     */
    @ExcelValue(name = "已派工")
    HAVESENDWORKER(0),
    /**
     * 未派工
     */
    @ExcelValue(name = "未派工")
    NOSENDWORKER(1);

    private int value;

    DispatCondition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
