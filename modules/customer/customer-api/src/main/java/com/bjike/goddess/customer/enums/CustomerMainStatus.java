package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 客户维护状态枚举
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [客户维护状态枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CustomerMainStatus {

    /**
     * 与项目问题有关-洽谈
     */
    @ExcelValue(name = "与项目问题有关-洽谈")
    RELATEDPROJECT_NEGOTIATION(0),
    /**
     * 与项目问题有关-招待
     */
    @ExcelValue(name = "与项目问题有关-招待")
    RELATEDPROJECT_TREAT(1),
    /**
     * 业务拓展-洽谈
     */
    @ExcelValue(name = "业务拓展-洽谈")
    BUSSEXPANSION_NEGOTIATION(2),
    /**
     * 业务拓展-招待
     */
    @ExcelValue(name = "业务拓展-招待")
    BUSSEXPANSION_TREAT(3),
    /**
     * 无
     */
    @ExcelValue(name = "无")
    nothing(4);

    private int code;

    CustomerMainStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
