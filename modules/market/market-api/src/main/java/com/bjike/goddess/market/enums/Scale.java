package com.bjike.goddess.market.enums;

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
    ALEVEL(0),
    /**
     * B级:31-60人
     */
    BLEVEL(1),
    /**
     * C级:61-90人
     */
    CLEVEL(2),
    /**
     * D级:91-120人
     */
    DLEVEL(3),
    /**
     * E级:121-150人
     */
    ELEVEL(4),
    /**
     * F级:151-180人
     */
    FLEVEL(5),
    /**
     * G级:181-210人
     */
    GLEVEL(6),
    ;

    private int code;

    Scale(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
