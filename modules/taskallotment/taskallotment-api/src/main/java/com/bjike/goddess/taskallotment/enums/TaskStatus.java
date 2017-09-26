package com.bjike.goddess.taskallotment.enums;

/**
 * 任务状态
 * @Author: [chenjunhao]
 * @Date: [2017-09-16 11:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TaskStatus {
    /**
     * 已完成
     */
    FINISH(0),
    /**
     * 正在执行
     */
    DOING(1),
    /**
     * 未完成
     */
    UNFINISHED(2);

    private int code;

    TaskStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
