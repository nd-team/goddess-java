package com.bjike.goddess.dispatchcar.enums;

/**
 * 对司机的评价
 *
 * @Author: [Jason]
 * @Date: [17-5-9 下午4:21]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Evaluate {
    /**
     * 优秀
     */
    GOOD(0),

    /**
     * 良好
     */
    WELL(1),

    /**
     * 恶劣
     */
    BAD(2);

    private int code;

    Evaluate(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
