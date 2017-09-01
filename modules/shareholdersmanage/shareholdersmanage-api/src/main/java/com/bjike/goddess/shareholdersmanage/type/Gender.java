package com.bjike.goddess.shareholdersmanage.type;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 性别
 *
 * @Author: [lijuntao]
 * @Date: [2017-03-23 11:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Gender {

    /**
     * 男
     */
    @ExcelValue(name = "男")
    MALE(0),
    /**
     * 女
     */
    @ExcelValue(name = "女")
    FEMALE(1);

    private int code;

    Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static String exportStrConvert(Gender gender) {
        String name = "";
        if (gender.equals(Gender.MALE)) {
            name = "男";
        }
        if (gender.equals(Gender.FEMALE)) {
            name = "女";
        }
        return name;
    }
}
