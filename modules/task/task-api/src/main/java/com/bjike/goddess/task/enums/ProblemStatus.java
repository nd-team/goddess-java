package com.bjike.goddess.task.enums;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-20 10:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ProblemStatus {
    /**
     * 待受理
     */
    WAIT(0),
    /**
     * 受理中
     */
    ACCEPTING(1),
    /**
     * 完成
     */
    FINISHED(2),
    /**
     * 关闭
     */
    CLOSE(3);

    ProblemStatus(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
