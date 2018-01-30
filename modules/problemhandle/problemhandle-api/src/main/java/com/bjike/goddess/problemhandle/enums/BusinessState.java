package com.bjike.goddess.problemhandle.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 业务状态
 *
 * @Author: [lijuntao]
 * @Date: [17-3-27]
 * @Description: [业务状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum BusinessState {
    /**
     * 立项前
     */
    @ExcelValue(name = "立项前")
    COMMENCEBEFORE(0),
    /**
     * 立项后
     */
    @ExcelValue(name = "立项后")
    COMMENCEAFTER(1),
   ;

    private int code;

    BusinessState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
