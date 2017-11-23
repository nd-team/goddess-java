package com.bjike.goddess.contacts.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 简单的数据状态
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ContactsStatus {
    /**
     * 在职
     */
    @ExcelValue(name = "在职")
    ONJOB(0),
    /**
     * 离职
     */
    @ExcelValue(name = "离职")
    LEAVEJOB(1),
    /**
     * 待离职
     */
    @ExcelValue(name = "待离职")
    WAITJOB(2),;

    private int code;

    ContactsStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
