package com.bjike.goddess.staffmove.enums;

/**
 * 审核人类型枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-22]
 * @Description: [审核人类型枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum AuditorType {
    /**
     * 总经办
     */
    GENERALMANAGER(0),
    /**
     * 原决策层
     */
    ORIGINALPOLICYMAKERS(1),
    /**
     * 调往决策层
     */
    TRANSFERREDPOLICYMAKERS(2),
    /**
     * 规划模块
     */
    PLANMODULE(3),
    /**
     * 预算模块
     */
    BUDGETMODULE(4),
    ;

    private int code;

    AuditorType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
