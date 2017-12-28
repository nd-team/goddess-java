package com.bjike.goddess.reimbursementprepare.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 付款状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-07-03 15:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PayStatus {
    /**
     * 等待付款
     */
    @ExcelValue(name = "等待付款")
    WAITPAY(0),
    /**
     * 已付款
     */
    @ExcelValue(name = "已付款")
    HAVEPAY(1);

    private int code;

    PayStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
