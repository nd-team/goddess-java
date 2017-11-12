package com.bjike.goddess.employeecontract.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-08 11:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ContractStatus {
    /**
     * 待签订
     */
    @ExcelValue(name = "待签订")
    WAITSIGN(0),

    /**
     * 已签订
     */
    @ExcelValue(name = "已签订")
    ALREADYSIGN(1),

    /**
     * 已到期未续签
     */
    @ExcelValue(name = "已到期未续签")
    ALREADYDEADLINENOTRENEW(2),

    /**
     * 解除
     */
    @ExcelValue(name = "解除")
    RELIEVE(3);

    private int code;

    ContractStatus(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

