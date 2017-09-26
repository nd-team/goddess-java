package com.bjike.goddess.taskallotment.enums;

/**
 * 任务类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-14 14:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TaskType {
    /**
     * 行政任务
     */
    ADMININSTRATION(0),
    /**
     * 工程任务
     */
    ENGINEERING(1),
    /**
     * 培训任务
     */
    TRAINING(2);

    private int code;

    TaskType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
