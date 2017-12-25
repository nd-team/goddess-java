package com.bjike.goddess.projectissuehandle.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 问题处理结果枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-3-27]
 * @Description: [问题处理结果枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum ProblemProcessingResult {
    /**
     * 完成
     */
    @ExcelValue(name = "完成")
    COMPLETE(0),
    /**
     * 未完成
     */
    @ExcelValue(name = "未完成")
    UNFINISHED(1),;

    private int code;

    ProblemProcessingResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public static ProblemProcessingResult getEnumConvert(int code) {
        ProblemProcessingResult problemProcessingResult = ProblemProcessingResult.COMPLETE;
        if (code == ProblemProcessingResult.COMPLETE.getCode()) {
            problemProcessingResult = ProblemProcessingResult.COMPLETE;
        }
        if (code == ProblemProcessingResult.UNFINISHED.getCode()) {
            problemProcessingResult = ProblemProcessingResult.UNFINISHED;
        }
        return problemProcessingResult;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == ProblemProcessingResult.COMPLETE.getCode()) {
            name = "完成";
        }
        if (code == ProblemProcessingResult.UNFINISHED.getCode()) {
            name = "未完成";
        }
        return name;
    }
    public static String exportStrConvert(ProblemProcessingResult problemProcessingResult) {
        String name = "";
        if (problemProcessingResult.equals(ProblemProcessingResult.COMPLETE)) {
            name = "完成";
        }
        if (problemProcessingResult.equals(ProblemProcessingResult.UNFINISHED)) {
            name = "4-未完成";
        }
        return name;
    }
}
