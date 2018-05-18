package com.bjike.goddess.communicatemeeting.enums;

/**
 * 计划参会岗位枚举类型：冻结，正常
 *
 * @Author: [chenjunhao]
 * @Date: [2017-05-26 16:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PlanJobStatus {
    /**
     * 冻结
     */
    FREEZE(0),
    /**
     * 正常
     */
    NORMAL(1);

    private int code;

    PlanJobStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
