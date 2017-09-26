package com.bjike.goddess.taskallotment.enums;

/**
 * 用于对象
 * @Author: [chenjunhao]
 * @Date: [2017-09-15 11:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ForObject {
    /**
     * 公司员工
     */
    STAFFING(0),
    /**
     * 部门
     */
    DEPART(1),
    /**
     * 个人
     */
    PERSON(2);

    private int code;

    ForObject(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
