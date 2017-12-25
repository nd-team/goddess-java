package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 职权枚举
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [职权枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum WorkRight {

    /**
     * 高
     */
    @ExcelValue(name = "高")
    HIGH(0),
    /**
     * 中
     */
    @ExcelValue(name = "中")
    MEDIUM(1),
    /**
     * 低
     */
    @ExcelValue(name = "低")
    LOW(2);

    private int code;

    WorkRight(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String enumToString(WorkRight workRight) {
        String name = "";
        switch (workRight) {
            case HIGH:
                name = "高";
                break;
            case MEDIUM:
                name = "中";
                break;
            case LOW:
                name = "低";
                break;
            default:
                name = "";
                break;

        }
        return name;
    }
}
