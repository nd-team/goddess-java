package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 拜访状态枚举
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [拜访状态枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum VisitStatus {

    /**
     * 待拜访
     */
    @ExcelValue(name = "待拜访")
    TOVISIT(0),
    /**
     * 已拜访
     */
    @ExcelValue(name = "已拜访")
    HAVEVISIT(1),
    /**
     * 未安排拜访
     */
    @ExcelValue(name = "未安排拜访")
    UNSCHEDULEDVISIT(2),
    /**
     * 未拜访
     */
    @ExcelValue(name = "未拜访")
    NOTVISIT(3);

    private int code;

    VisitStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
