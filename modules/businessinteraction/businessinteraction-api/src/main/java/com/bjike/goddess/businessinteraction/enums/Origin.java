package com.bjike.goddess.businessinteraction.enums;

import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 需求者来源
 * @Author: [lijuntao]
 * @Date: [2017-03-16 19:16]
 * @Description: [需求者来源]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Origin {

    /**
     * 员工
     */
    @ExcelValue(name = "员工")
    EMPLOYEES(0),
    /**
     * 通过展示
     */
    @ExcelValue(name = "通过展示")
    BYSHOW(1),;

    private int code;

    Origin(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}


