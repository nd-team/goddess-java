package com.bjike.goddess.market.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 规模枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-25]
 * @Description: [规模枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum Scale {

    /**
     * A级:1-30人
     */
    @ExcelValue(name = "A级:1-30人")
    ALEVEL(0),
    /**
     * B级:31-60人
     */
    @ExcelValue(name = "B级:31-60人")
    BLEVEL(1),
    /**
     * C级:61-90人
     */
    @ExcelValue(name = "C级:61-90人")
    CLEVEL(2),
    /**
     * D级:91-120人
     */
    @ExcelValue(name = "D级:91-120人")
    DLEVEL(3),
    /**
     * E级:121-150人
     */
    @ExcelValue(name = "E级:121-150人")
    ELEVEL(4),
    /**
     * F级:151-180人
     */
    @ExcelValue(name = "F级:151-180人")
    FLEVEL(5),
    /**
     * G级:181-210人
     */
    @ExcelValue(name = "G级:181-210人")
    GLEVEL(6),
    ;

    private int code;

    Scale(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public static String exportStrConvert(Scale scale) {
        String name = "";
        if (scale.equals(Scale.ALEVEL)) {
            name = "A级:1-30人";
        }
        if (scale.equals(Scale.BLEVEL)) {
            name = "B级:31-60人";
        }
        if (scale.equals(Scale.CLEVEL)) {
            name = "C级:61-90人";
        }
        if (scale.equals(Scale.DLEVEL)) {
            name = "D级:91-120人";
        }
        if (scale.equals(Scale.ELEVEL)) {
            name = "E级:121-150人";
        }
        if (scale.equals(Scale.FLEVEL)) {
            name = "F级:151-180人";
        }
        if (scale.equals(Scale.GLEVEL)) {
            name = "G级:181-210人";
        }

        return name;
    }
}
