package com.bjike.goddess.projectissuehandle.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 问题处理时间枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-25]
 * @Description: [问题处理时间枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum ProblemProcessingTime {

    /**
     * 4个小时之类
     */
    @ExcelValue(name = "4个小时之类")
    FOURHOURS(0),
    /**
     * 4-24小时之类
     */
    @ExcelValue(name = "4-24小时之类")
    FOURTOTWENTYFOURHOURS(1),
    /**
     * 24小时以上
     */
    @ExcelValue(name = "24小时以上")
    TWENTYFOURHOURS(2),
    ;

    private int code;

    ProblemProcessingTime(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String exportStrConvert(ProblemProcessingTime problemProcessingTime) {
        String name = "";
        if (problemProcessingTime.equals(ProblemProcessingTime.FOURHOURS)) {
            name = "4个小时之类";
        }
        if (problemProcessingTime.equals(ProblemProcessingTime.FOURTOTWENTYFOURHOURS)) {
            name = "4-24小时之类";
        }
        if (problemProcessingTime.equals(ProblemProcessingTime.TWENTYFOURHOURS)) {
            name = "24小时以上";
        }
        return name;
    }
}
