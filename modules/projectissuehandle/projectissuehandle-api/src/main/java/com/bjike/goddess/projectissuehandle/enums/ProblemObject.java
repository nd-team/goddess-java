package com.bjike.goddess.projectissuehandle.enums;

/**
 * 问题对象枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-3-27]
 * @Description: [问题对象枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum ProblemObject {
    /**
     * 运营商
     */
    OPERATOR(0),
    /**
     * 厂家
     */
    VENDER(1),
    /**
     * 集成商
     */
    INTEGRATOR(2),
    /**
     * 政府机关
     */
    GOVERNMENT(3),
    /**
     * 内部员工
     */
    INNERSTAFF(4),;

    private int code;

    ProblemObject(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public static ProblemObject getEnumConvert(int code) {
        ProblemObject problemObject = ProblemObject.OPERATOR;
        if (code == ProblemObject.OPERATOR.getCode()) {
            problemObject = ProblemObject.OPERATOR;
        }
        if (code == ProblemObject.VENDER.getCode()) {
            problemObject = ProblemObject.VENDER;
        }
        if (code == ProblemObject.INTEGRATOR.getCode()) {
            problemObject = ProblemObject.INTEGRATOR;
        }
        if (code == ProblemObject.GOVERNMENT.getCode()) {
            problemObject = ProblemObject.GOVERNMENT;
        }
        if (code == ProblemObject.INNERSTAFF.getCode()) {
            problemObject = ProblemObject.INNERSTAFF;
        }
        return problemObject;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == ProblemObject.OPERATOR.getCode()) {
            name = "运营商";
        }
        if (code == ProblemObject.VENDER.getCode()) {
            name = "厂家";
        }
        if (code == ProblemObject.INTEGRATOR.getCode()) {
            name = "集成商";
        }
        if (code == ProblemObject.GOVERNMENT.getCode()) {
            name = "政府机关";
        }
        if (code == ProblemObject.INNERSTAFF.getCode()) {
            name = "内部员工";
        }
        return name;
    }

}
