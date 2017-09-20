package com.bjike.goddess.organize.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 人员状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-06 15:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum StaffStatus {
    /**
     * 在职
     */
    @ExcelValue(name="在职")
    WORKING(0),
    /**
     * 已离职
     */
    @ExcelValue(name="已离职")
    HAVELEAVE(1),
    /**
     * 待离职
     */
    @ExcelValue(name="已离职")
    LEAVING(2),
    /**
     * 请假
     */
    @ExcelValue(name="已离职")
    REST(3);
    private int code;

    StaffStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        String s = null;
        switch (code) {
            case 0:
                s = "在职";
                break;
            case 1:
                s = "已离职";
                break;
            case 2:
                s = "待离职";
                break;
            case 3:
                s = "请假";
                break;
        }
        return s;
    }
}
