package com.bjike.goddess.regularization.type;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 跟进类型
 *
 * @Author: [lijuntao]
 * @Date: [2017-06-20 12:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Assess {
    /**
     * 待跟进
     */
    @ExcelValue(name="待跟进")
    TOFOLLOW(0),
    /**
     * 已跟进
     */
    @ExcelValue(name="已跟进")
    HAVEFOLLOW(1),
    /**
     * 未跟进
     */
    @ExcelValue(name="未跟进")
    NOTFOLLOW(2);

    private int code;

    Assess(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
