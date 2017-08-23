package com.bjike.goddess.businessproject.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [yewenbo]
 * @Date: [2017-06-08 10:00]
 * @Description: [ ]
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

