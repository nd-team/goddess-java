package com.bjike.goddess.employeecontract.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-10 09:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ChangeWay {
    /**
     * 添加协议
     */
    @ExcelValue(name = "添加协议")
    ADDAGREEMENT(0),

    /**
     * 删除协议
     */
    @ExcelValue(name = "删除协议")
    DELETEAGREEMENT(1),

    /**
     * 协议内容
     */
    @ExcelValue(name = "协议内容")
    AGREEMENTCONTEN(2);

    private int code;

    ChangeWay(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
