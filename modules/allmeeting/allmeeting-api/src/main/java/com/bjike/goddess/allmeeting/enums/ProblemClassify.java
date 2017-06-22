package com.bjike.goddess.allmeeting.enums;

/**
 * 会议形式
 * <p>
 * Created by ike on 17-5-31.
 */
public enum ProblemClassify {

    /**
     * 对象
     */
    OBJECT(0),

    /**
     * 通报信息
     */
    ANNUNCIATE(1),
    /**
     * 时间
     */
    TIME(2);

    private int code;

    ProblemClassify(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
