package com.bjike.goddess.dispatchcar.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 查询类型
 *
 * @Author: [Jason]
 * @Date: [17-4-14 下午2:19]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum FindType {
    /**
     * 等待审核
     */
    @ExcelValue(name = "等待审核")
    WAITAUDIT(0),
    /**
     * 财务核对
     */
    @ExcelValue(name = "财务核对")
    FINANCEAUDIT(1),
    /**
     * 等待付款
     */
    @ExcelValue(name = "等待付款")
    WAITPAY(2),
    /**
     * 已付款
     */
    @ExcelValue(name = "已付款")
    PAYED(3),
    /**
     * 有误
     */
    @ExcelValue(name = "有误")
    WRONG(4);

    private int code;

    FindType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
