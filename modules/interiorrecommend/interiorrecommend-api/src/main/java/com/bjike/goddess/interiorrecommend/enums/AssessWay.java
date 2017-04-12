package com.bjike.goddess.interiorrecommend.enums;

/**
 * @Author: [Jason]
 * @Date: [17-4-9 下午3:22]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AssessWay {
    /**
     * 面试通过
     */
    INTERVIEW(0),
    /**
     * 在司工龄三个月
     */
    THREEMONTH(1);

    private int code;

    AssessWay(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
