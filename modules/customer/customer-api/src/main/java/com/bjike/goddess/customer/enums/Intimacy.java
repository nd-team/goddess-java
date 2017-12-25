package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 亲密度枚举
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [亲密度枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Intimacy {

    /**
     * 十分亲密
     */
    @ExcelValue(name = "十分亲密")
    VERYCLOSE(0),
    /**
     * 亲密
     */
    @ExcelValue(name = "亲密")
    CLOSE(1),
    /**
     * 一般
     */
    @ExcelValue(name = "一般")
    GENERAL(2);

    private int code;

    Intimacy(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String enumToString(Intimacy intimacy) {
        String name = "";
        switch (intimacy) {
            case VERYCLOSE:
                name = "十分亲密";
                break;
            case CLOSE:
                name = "亲密";
                break;
            case GENERAL:
                name = "一般";
                break;
            default:
                name = "";
                break;

        }
        return name;
    }
}
