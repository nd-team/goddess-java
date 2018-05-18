package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 客户维护状态枚举
 *
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [客户维护状态枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DifficultyLevel {

    /**
     * 困难
     */
    @ExcelValue(name = "困难")
    DIFFICULTY(0),
    /**
     * 一般
     */
    @ExcelValue(name = "一般")
    GENERAL(1),
    /**
     * 容易
     */
    @ExcelValue(name = "容易")
    EASY(2);

    private int code;

    DifficultyLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String enumToString(DifficultyLevel difficultyLevel) {
        String name = "";
        switch (difficultyLevel) {
            case DIFFICULTY:
                name = "困难";
                break;
            case GENERAL:
                name = "一般";
                break;
            case EASY:
                name = "容易";
                break;
            default:
                name = "";
                break;

        }
        return name;
    }
}
