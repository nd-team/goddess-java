package com.bjike.goddess.assistance.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 补助状态
 *
 * @Author: [lijuntao]
 * @Date: [2017-03-20 19:57]
 * @Description: [补助状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SubsidiesStatus {

    /**
     * 在补助
     */
    @ExcelValue(name="在补助")
    INSUBSIDIES(0),
    /**
     * 未补助
     */
    @ExcelValue(name="未补助")
    NOSUBSIDIES(1);

    private int code;

    SubsidiesStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String exportStrConvert(SubsidiesStatus subsidiesStatus) {
        String name = "";
        if (subsidiesStatus.equals(SubsidiesStatus.INSUBSIDIES)) {
            name = "在补助";
        }
        if (subsidiesStatus.equals(SubsidiesStatus.NOSUBSIDIES)) {
            name = "未补助";
        }
        return name;
    }
}
