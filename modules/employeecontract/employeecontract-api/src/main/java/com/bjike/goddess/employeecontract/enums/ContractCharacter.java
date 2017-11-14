package com.bjike.goddess.employeecontract.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-08 11:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ContractCharacter {
    /**
     * 内部员工
     */
    @ExcelValue(name = "内部员工")
    INTERNALSTAFF(0),

    /**
     * 外聘员工
     */
    @ExcelValue(name = "外聘员工")
    EXTERNALSTAFF(1);

    private int code;

    ContractCharacter(int code){this.code = code;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
