package com.bjike.goddess.businessevaluate.enums;

/**
 * 问题受理难度
 *
 * @Author: [Jason]
 * @Date: [17-3-29 下午2:35]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ProblemDifficulty {

    /**
     * 初级
     */
    PRIMARY(0),
    /**
     * 中级
     */
    MIDDLE(1),
    /**
     * 紧急
     */
    URGENCY(2);

    private int code;

    ProblemDifficulty(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
