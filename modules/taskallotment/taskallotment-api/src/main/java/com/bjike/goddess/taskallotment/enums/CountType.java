package com.bjike.goddess.taskallotment.enums;

/**
 * 汇总类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 10:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CountType {
    /**
     * 整体汇总
     */
    WHOLE(0),
    /**
     * 项目组/部门汇总
     */
    DEPART(1),
    /**
     * 个人汇总
     */
    PERSON(2);

    private int code;

    CountType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
