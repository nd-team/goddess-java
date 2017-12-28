package com.bjike.goddess.projectissuehandle.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 问题类型枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-3-27]
 * @Description: [问题类型枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum ProblemTypes {
    /**
     * 人员类
     */
    @ExcelValue(name = "人员类")
    PERSONNERCLASS(0),
    /**
     * 设备类
     */
    @ExcelValue(name = "设备类")
    DEVICECLASS(1),
    /**
     * 派工类
     */
    @ExcelValue(name = "派工类")
    DISPATCHINGCLASS(2),
    /**
     * 结算类
     */
    @ExcelValue(name = "结算类")
    SETTLEMENTCLASS(3),;

    private int code;

    ProblemTypes(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public static ProblemTypes getEnumConvert(int code) {
        ProblemTypes problemTypes = ProblemTypes.PERSONNERCLASS;
        if (code == ProblemTypes.PERSONNERCLASS.getCode()) {
            problemTypes = ProblemTypes.PERSONNERCLASS;
        }
        if (code == ProblemTypes.DEVICECLASS.getCode()) {
            problemTypes = ProblemTypes.DEVICECLASS;
        }
        if (code == ProblemTypes.DISPATCHINGCLASS.getCode()) {
            problemTypes = ProblemTypes.DISPATCHINGCLASS;
        }
        if (code == ProblemTypes.SETTLEMENTCLASS.getCode()) {
            problemTypes = ProblemTypes.SETTLEMENTCLASS;
        }

        return problemTypes;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == ProblemTypes.PERSONNERCLASS.getCode()) {
            name = "人员类";
        }
        if (code == ProblemTypes.DEVICECLASS.getCode()) {
            name = "设备类";
        }
        if (code == ProblemTypes.DISPATCHINGCLASS.getCode()) {
            name = "派工类";
        }
        if (code == ProblemTypes.SETTLEMENTCLASS.getCode()) {
            name = "结算类";
        }

        return name;
    }
    public static String exportStrConvert(ProblemTypes problemTypes) {
        String name = "";
        if (problemTypes.equals(ProblemTypes.PERSONNERCLASS)) {
            name = "人员类";
        }
        if (problemTypes.equals(ProblemTypes.DEVICECLASS)) {
            name = "设备类";
        }
        if (problemTypes.equals(ProblemTypes.DISPATCHINGCLASS)) {
            name = "派工类";
        }
        if (problemTypes.equals(ProblemTypes.SETTLEMENTCLASS)) {
            name = "结算类";
        }
        return name;
    }
}
