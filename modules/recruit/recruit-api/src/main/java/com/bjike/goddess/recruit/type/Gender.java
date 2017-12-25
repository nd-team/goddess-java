package com.bjike.goddess.recruit.type;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 性别
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 13:53]
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

    private int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
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
