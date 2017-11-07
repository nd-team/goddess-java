package com.bjike.goddess.customer.enums;


import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 接触阶段枚举
 *
 * @Author: [lijuntao]
 * @Date: [2017-03-15 16:48]
 * @Description: [接触阶段枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ContactPhace {

    /**
     * 查询
     */
    @ExcelValue(name = "查询")
    ENQUIRIES(0),
    /**
     * 了解
     */
    @ExcelValue(name = "了解")
    UNDERSTAND(1),
    /**
     * 接触
     */
    @ExcelValue(name = "接触")
    CONTACT(2),
    /**
     * 拜访
     */
    @ExcelValue(name = "拜访")
    COLLON(3),
    /**
     * 活动
     */
    @ExcelValue(name = "活动")
    ACTIVITY(4);

    private int code;

    ContactPhace(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String enumToString(ContactPhace contactPhace) {
        String name = "";
        switch (contactPhace) {
            case ENQUIRIES:
                name = "查询";
                break;
            case UNDERSTAND:
                name = "了解";
                break;
            case CONTACT:
                name = "接触";
                break;
            case COLLON:
                name = "拜访";
                break;
            case ACTIVITY:
                name = "活动";
                break;
            default:
                name = "";
                break;

        }
        return name;
    }
}
