package com.bjike.goddess.allmeeting.enums;

/**
 * 问题处理状态
 * <p>
 * Created by ike on 17-5-31.
 */
public enum ProblemStatus {

    /**
     * 未处理
     */
    NOTDEAL(0),

    /**
     * 处理中
     */
    DEALING(1),

    /**
     * 已处理
     */
    DEALED(2);

    private int code;

    ProblemStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
