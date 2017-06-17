package com.bjike.goddess.projectissuehandle.enums;

/**
 * 问题紧急程度枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-3-27]
 * @Description: [问题紧急程度枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum ProblemEmergencyDegree {
    /**
     * 初级
     */
    PRIMARY(0),
    /**
     * 中级
     */
    INTERMEDIATE(1),
    /**
     * 紧急
     */
    EMERGENCY(2),;

    private int code;

    ProblemEmergencyDegree(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public static ProblemEmergencyDegree getEnumConvert(int code) {
        ProblemEmergencyDegree problemEmergencyDegree = ProblemEmergencyDegree.PRIMARY;
        if (code == ProblemEmergencyDegree.PRIMARY.getCode()) {
            problemEmergencyDegree = ProblemEmergencyDegree.PRIMARY;
        }
        if (code == ProblemEmergencyDegree.INTERMEDIATE.getCode()) {
            problemEmergencyDegree = ProblemEmergencyDegree.INTERMEDIATE;
        }
        if (code == ProblemEmergencyDegree.EMERGENCY.getCode()) {
            problemEmergencyDegree = ProblemEmergencyDegree.EMERGENCY;
        }
        return problemEmergencyDegree;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == ProblemEmergencyDegree.PRIMARY.getCode()) {
            name = "初级";
        }
        if (code == ProblemEmergencyDegree.INTERMEDIATE.getCode()) {
            name = "中级";
        }
        if (code == ProblemEmergencyDegree.EMERGENCY.getCode()) {
            name = "紧急";
        }
        return name;
    }
}
