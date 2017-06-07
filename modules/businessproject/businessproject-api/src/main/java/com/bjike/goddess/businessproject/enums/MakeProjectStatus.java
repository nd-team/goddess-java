package com.bjike.goddess.businessproject.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 立项情况
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 19:57]
 * @Description: [立项情况]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum MakeProjectStatus {

    /**
     * 已立项
     */
    @ExcelValue(name = "已立项")
    SIGN(0),
    /**
     * 未立项
     */
    @ExcelValue(name = "未立项")
    NOSIGN(1);

    private int code;

    MakeProjectStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}


