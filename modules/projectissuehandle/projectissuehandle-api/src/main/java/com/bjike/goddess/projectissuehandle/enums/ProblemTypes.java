package com.bjike.goddess.projectissuehandle.enums;

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
    PERSONNERCLASS(0),
    /**
     * 进度类
     */
    PROGRESSCLASS(1),
    /**
     * 交付类
     */
    DELIVERCLASS(2),
    /**
     * 设备类
     */
    DEVICECLASS(3),;

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
        if (code == ProblemTypes.PROGRESSCLASS.getCode()) {
            problemTypes = ProblemTypes.PROGRESSCLASS;
        }
        if (code == ProblemTypes.DELIVERCLASS.getCode()) {
            problemTypes = ProblemTypes.DELIVERCLASS;
        }
        if (code == ProblemTypes.DEVICECLASS.getCode()) {
            problemTypes = ProblemTypes.DEVICECLASS;
        }
        return problemTypes;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == ProblemTypes.PERSONNERCLASS.getCode()) {
            name = "人员类";
        }
        if (code == ProblemTypes.PROGRESSCLASS.getCode()) {
            name = "进度类";
        }
        if (code == ProblemTypes.DELIVERCLASS.getCode()) {
            name = "交付类";
        }
        if (code == ProblemTypes.DEVICECLASS.getCode()) {
            name = "设备类";
        }
        return name;
    }
}
