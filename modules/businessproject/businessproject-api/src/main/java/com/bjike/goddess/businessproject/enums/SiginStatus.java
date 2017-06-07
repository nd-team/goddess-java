package com.bjike.goddess.businessproject.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 签订状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 19:57]
 * @Description: [签订状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SiginStatus {

    /**
     * 已签订
     */
    @ExcelValue(name = "已签订")
    HASSIGN(0),
    /**
     * 未签订
     */
    @ExcelValue(name = "未签订")
    HASNOSIGN(1);

    private int code;

    SiginStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}


