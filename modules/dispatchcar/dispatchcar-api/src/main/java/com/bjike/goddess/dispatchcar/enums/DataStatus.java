package com.bjike.goddess.dispatchcar.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-12 15:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DataStatus {

    /**
     * 解冻
     */
    @ExcelValue(name = "解冻")
    THAW(0),
    /**
     * 冻结
     */
    @ExcelValue(name = "冻结")
    CONGEAL(1),
    /**
     * 删除
     */
    @ExcelValue(name = "删除")
    DELETE(2),
    /**
     * 未激活
     */
    @ExcelValue(name = "未激活")
    NOACTIVE(3),
    /**
     * 未审核
     */
    @ExcelValue(name = "未审核")
    UNREVIEW(4)
    ;

    private int code;

    DataStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
