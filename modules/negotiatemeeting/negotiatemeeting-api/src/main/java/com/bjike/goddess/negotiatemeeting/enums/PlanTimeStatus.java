package com.bjike.goddess.negotiatemeeting.enums;

/**
 * 计划会议时间枚举类型：冻结，正常
 * @Author: [chenjunhao]
 * @Date: [2017-05-26 16:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PlanTimeStatus {
    /**
     * 冻结
     */
    FREEZE(0),
    /**
     * 正常
     */
    NORMAL(1);

    private int code;

    PlanTimeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
