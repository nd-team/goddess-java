package com.bjike.goddess.taskallotment.enums;

/**
 * 完成状态
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 09:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum FinishStatus {
    /**
     * 公司员工
     */
    FINISH(0),
    /**
     * 部门
     */
    UNFINISHED(1);

    private int code;

    FinishStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
